package zipkintrace.serviceb.grpc.serviceb;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zipkintrace.common.tracing.GrpcServerInterceptorWrapper;
import zipkintrace.serviceb.ServiceB;
import zipkintrace.serviceb.grpc.serviceb.generated.ServiceBGrpc;
import zipkintrace.serviceb.grpc.serviceb.generated.Serviceb;

@Slf4j
@Component
@GRpcService(interceptors = GrpcServerInterceptorWrapper.class)
public class GrpcServiceBImpl extends ServiceBGrpc.ServiceBImplBase {

    @Autowired
    private ServiceB serviceB;

    @Override
    public void shortRunning(Serviceb.Empty request, StreamObserver<Serviceb.Result> responseObserver) {
        log.info("Service B called with 'shortRunning' via gRPC");
        val value = serviceB.shortRunning();
        responseObserver.onNext(Serviceb.Result.newBuilder().setValue(value).build());
        responseObserver.onCompleted();
    }

    @Override
    public void longRunning(Serviceb.Empty request, StreamObserver<Serviceb.Result> responseObserver) {
        log.info("Service B called with 'longRunning' via gRPC");
        val value = serviceB.longRunning();
        responseObserver.onNext(Serviceb.Result.newBuilder().setValue(value).build());
        responseObserver.onCompleted();
    }

    @Override
    public void viaC(Serviceb.Empty request, StreamObserver<Serviceb.Result> responseObserver) {
        log.info("Service B called with 'viaC' via gRPC");
        val value = serviceB.viaC();
        responseObserver.onNext(Serviceb.Result.newBuilder().setValue(value).build());
        responseObserver.onCompleted();
    }
}
