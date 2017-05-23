package zipkintrace.servicea.grpc.serviceb;

import brave.grpc.GrpcTracing;
import io.grpc.CallOptions;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zipkintrace.servicea.grpc.serviceb.generated.ServiceBGrpc;
import zipkintrace.servicea.grpc.serviceb.generated.Serviceb;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class GrpcServiceBClient {
    @Autowired
    private GrpcTracing grpcTracing;

    @Value("${serviceB.host}")
    private String serviceBHost;

    @Value("${serviceB.grpcPort}")
    private Integer serviceBGrpcPort;

    @Value("${serviceB.grpcDeadlineTimeoutInSeconds}")
    private Integer grpcDeadlineTimeoutInSeconds;

    private ManagedChannel channel;

    public GrpcServiceBClient() {
    }

    @PostConstruct
    public void postConstruct() {
        log.info("Service B at: {}:{}", serviceBHost, serviceBGrpcPort);
        channel = ManagedChannelBuilder.forAddress(serviceBHost, serviceBGrpcPort)
                .intercept(grpcTracing.newClientInterceptor())
                .usePlaintext(true).build();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public Integer shortRunning() {
        val blockingStub = initializeBlockingStub();
        val result = blockingStub.shortRunning(Serviceb.Empty.newBuilder().build());
        return result.getValue();
    }

    public Integer longRunning() {
        val blockingStub = initializeBlockingStub();
        val result = blockingStub.longRunning(Serviceb.Empty.newBuilder().build());
        return result.getValue();
    }

    private ServiceBGrpc.ServiceBBlockingStub initializeBlockingStub() {
        return ServiceBGrpc.newBlockingStub(channel)
                .withOption(CallOptions.Key.of("withWaitForReady", false), true)
                .withDeadlineAfter(grpcDeadlineTimeoutInSeconds, TimeUnit.SECONDS)
                .withInterceptors(grpcTracing.newClientInterceptor());
    }
}
