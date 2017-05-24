package zipkintrace.serviceb.grpc.serviceb;

import brave.Tracing;
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
//@GRpcService(interceptors = GrpcServerInterceptorWrapper.class)
public class GrpcServiceBImpl extends ServiceBGrpc.ServiceBImplBase {

    @Autowired
    private Tracing tracing;

    @Autowired
    private ServiceB serviceB;

    @Override
    public void shortRunning(Serviceb.Empty request, StreamObserver<Serviceb.Result> responseObserver) {

        log.info("TEST B called with shortRunning");
        val value = serviceB.shortRunning();
        responseObserver.onNext(Serviceb.Result.newBuilder().setValue(value).build());
        responseObserver.onCompleted();
    }

    @Override
    public void longRunning(Serviceb.Empty request, StreamObserver<Serviceb.Result> responseObserver) {
        log.info("TEST B called with longRunning");
        val value = serviceB.longRunning();
        responseObserver.onNext(Serviceb.Result.newBuilder().setValue(value).build());
        responseObserver.onCompleted();
    }
}
