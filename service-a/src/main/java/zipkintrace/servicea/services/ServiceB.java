package zipkintrace.servicea.services;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zipkintrace.servicea.grpc.serviceb.GrpcServiceBClient;

@Slf4j
@Component
public class ServiceB {

    @Autowired
    private GrpcServiceBClient grpcServiceBClient;

    public void shortRunning() {
        log.info("Calling 'shortRunning' on Service B via gRPC");
        val result = grpcServiceBClient.shortRunning();
        log.info("Result of 'shortRunning' on Service B: {}", result);
    }

    public void longRunning() {
        log.info("Calling 'longRunning' on Service B via gRPC");
        val result = grpcServiceBClient.longRunning();
        log.info("Result of 'longRunning' on Service B: {}", result);
    }

    public void viaC() {
        log.info("Calling 'viaC' on Service B via gRPC");
        val result = grpcServiceBClient.viaC();
        log.info("Result of 'viaC' on Service B: {}", result);
    }
}
