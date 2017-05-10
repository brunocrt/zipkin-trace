package zipkintrace.common.tracing;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.grpc.BraveGrpcClientInterceptor;
import com.github.kristofa.brave.grpc.BraveGrpcServerInterceptor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.Sender;
import zipkin.reporter.urlconnection.URLConnectionSender;

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
    @DependsOn("reporter")
    public Brave brave() {
        val braveBuilder = new Brave.Builder(serviceName);
        if (zipkinEnabled) {
            braveBuilder.reporter(reporter());
        }
        return braveBuilder.build();
    }

    @Bean
    @DependsOn("brave")
    public BraveGrpcClientInterceptor braveGrpcClientInterceptor() {
        return BraveGrpcClientInterceptor.create(brave());
    }

    @Bean
    @DependsOn("brave")
    public BraveGrpcServerInterceptor braveGrpcServerInterceptor() {
        return BraveGrpcServerInterceptor.create(brave());
    }
}
