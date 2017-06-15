package zipkintrace.serviceb.grpc.servicec;

import brave.grpc.GrpcTracing;
import io.grpc.CallOptions;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zipkintrace.serviceb.grpc.servicec.generated.ServiceCGrpc;
import zipkintrace.serviceb.grpc.servicec.generated.Servicec;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class GrpcServiceCClient {
    @Autowired
    private GrpcTracing grpcTracing;

    @Value("${serviceC.host}")
    private String serviceCHost;

    @Value("${serviceC.grpcPort}")
    private Integer serviceCGrpcPort;

    @Value("${serviceC.grpcDeadlineTimeoutInSeconds}")
    private Integer grpcDeadlineTimeoutInSeconds;

    private ManagedChannel channel;

    public GrpcServiceCClient() {
    }

    @PostConstruct
    public void postConstruct() {
        log.info("Service C at: {}:{}", serviceCHost, serviceCGrpcPort);
        channel = ManagedChannelBuilder.forAddress(serviceCHost, serviceCGrpcPort)
                .intercept(grpcTracing.newClientInterceptor())
                .usePlaintext(true)
                .build();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public Integer callC() {
        val blockingStub = initializeBlockingStub();
        val result = blockingStub.callC(Servicec.Empty.newBuilder().build());
        return result.getValue();
    }

    private ServiceCGrpc.ServiceCBlockingStub initializeBlockingStub() {
        return ServiceCGrpc.newBlockingStub(channel)
                .withOption(CallOptions.Key.of("withWaitForReady", false), true)
                .withDeadlineAfter(grpcDeadlineTimeoutInSeconds, TimeUnit.SECONDS);
    }
}
