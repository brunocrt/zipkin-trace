package zipkintrace.common.tracing;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GrpcServerInterceptorWrapper implements ServerInterceptor {

    @Autowired
    @Qualifier("GrpcServerInterceptor")
    private ServerInterceptor serverInterceptor;

    public GrpcServerInterceptorWrapper() {
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        return serverInterceptor.interceptCall(call, headers, next);
    }
}
