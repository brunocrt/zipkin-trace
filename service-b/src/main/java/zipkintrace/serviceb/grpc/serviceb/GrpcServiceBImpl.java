package zipkintrace.serviceb.grpc.serviceb;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.grpc.BraveGrpcServerInterceptor;
import io.grpc.stub.StreamObserver;
import lombok.val;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import zipkintrace.common.tracing.TracingHelper;
import zipkintrace.serviceb.ServiceB;
import zipkintrace.serviceb.grpc.serviceb.generated.ServiceBGrpc;
import zipkintrace.serviceb.grpc.serviceb.generated.Serviceb;

@GRpcService(interceptors = BraveGrpcServerInterceptor.class)
public class GrpcServiceBImpl extends ServiceBGrpc.ServiceBImplBase {

    @Autowired
    private ServiceB serviceB;

    @Autowired
    private Brave brave;

    @Override
    public void shortRunning(Serviceb.Empty request, StreamObserver<Serviceb.Result> responseObserver) {
        TracingHelper.trace(() -> {
            val value = serviceB.shortRunning();
            responseObserver.onNext(Serviceb.Result.newBuilder().setValue(value).build());
            responseObserver.onCompleted();
        }, brave.serverSpanThreadBinder().getCurrentServerSpan().getSpan());
    }

    @Override
    public void longRunning(Serviceb.Empty request, StreamObserver<Serviceb.Result> responseObserver) {
        TracingHelper.trace(() -> {
            val value = serviceB.longRunning();
            responseObserver.onNext(Serviceb.Result.newBuilder().setValue(value).build());
            responseObserver.onCompleted();
        }, brave.serverSpanThreadBinder().getCurrentServerSpan().getSpan());
    }
}
