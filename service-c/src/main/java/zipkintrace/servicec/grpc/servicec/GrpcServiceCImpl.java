package zipkintrace.servicec.grpc.servicec;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zipkintrace.common.tracing.GrpcServerInterceptorWrapper;
import zipkintrace.servicec.ServiceC;
import zipkintrace.servicec.grpc.servicec.generated.ServiceCGrpc;
import zipkintrace.servicec.grpc.servicec.generated.Servicec;

@Slf4j
@Component
@GRpcService(interceptors = GrpcServerInterceptorWrapper.class)
public class GrpcServiceCImpl extends ServiceCGrpc.ServiceCImplBase {

    @Autowired
    private ServiceC serviceC;

    @Override
    public void callC(Servicec.Empty request, StreamObserver<Servicec.Result> responseObserver) {
        log.info("Service C called with callC");
        serviceC.callC();
        responseObserver.onNext(Servicec.Result.newBuilder().setValue(789).build());
        responseObserver.onCompleted();
    }
}
