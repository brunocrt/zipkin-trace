package zipkintrace.common.tracing;

import brave.Tracing;
import brave.context.slf4j.MDCCurrentTraceContext;
import brave.grpc.GrpcTracing;
import brave.http.HttpTracing;
import brave.propagation.CurrentTraceContext;
import io.grpc.ClientInterceptor;
import io.grpc.ServerInterceptor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.Sender;
import zipkin.reporter.urlconnection.URLConnectionSender;

import java.util.concurrent.*;

@Configuration
public class ZipkinBraveConfiguration {

    @Value("${zipkin.host}")
    private String zipkinHost;

    @Value("${zipkin.port}")
    private Integer zipkinPort;

    @Value("${zipkin.enabled}")
    private boolean zipkinEnabled;

    @Value("${zipkin.serviceName}")
    private String serviceName;

    @Bean
    public Sender sender() {
        val zipkinUrl = "http://" + zipkinHost + ":" + zipkinPort + "/api/v1/spans";

        return URLConnectionSender.builder()
                .endpoint(zipkinUrl)
                .build();
    }

    @Bean
    @DependsOn("sender")
    public Reporter reporter() {
        return AsyncReporter.builder(sender()).build();
    }

    @Bean
    public Tracing tracing() {
        val tracing = Tracing.newBuilder()
                .localServiceName(serviceName)
                .currentTraceContext(MDCCurrentTraceContext.create());

        if (zipkinEnabled) {
            tracing.reporter(reporter());
        }
        return tracing.build();
    }

    @Bean
    @DependsOn("tracing")
    public GrpcTracing grpcTracing() {
        return GrpcTracing.create(tracing());
    }

    @Bean(name = "GrpcServerInterceptor")
    public ServerInterceptor serviceInterceptor() {
        return GrpcTracing.create(tracing()).newServerInterceptor();
    }

    @Bean(name = "GrpcClientInterceptor")
    public ClientInterceptor clientInterceptor() {
        return GrpcTracing.create(tracing()).newClientInterceptor();
    }

    @Bean
    @DependsOn("tracing")
    public HttpTracing httpTracing() {
        return HttpTracing.newBuilder(tracing())
                .clientParser(new ZipkinHttpClientParser())
                .serverParser(new ZipkinHttpServerParser())
                .build();
    }

    @Bean
    public Executor executor() {
        val queue = new LinkedBlockingQueue<Runnable>() {
            @Override
            public boolean offer(Runnable e) {
                // queue that always rejects tasks
                return false;
            }
        };

        val threadPoolExecutor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                // Do not use the queue to prevent threads waiting on enqueued tasks.
                queue,
                // If all the threads are working, then the caller thread
                // should execute the code in its own thread. (serially)
                new ThreadPoolExecutor.CallerRunsPolicy());

        return new CurrentTraceContext.Default().executor(threadPoolExecutor);
    }
}
