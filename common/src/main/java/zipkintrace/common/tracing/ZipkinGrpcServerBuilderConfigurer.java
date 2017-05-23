package zipkintrace.common.tracing;

import io.grpc.ServerBuilder;
import org.lognet.springboot.grpc.GRpcServerBuilderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class ZipkinGrpcServerBuilderConfigurer extends GRpcServerBuilderConfigurer {

    @Autowired
    private Executor executor;

    @Override
    public void configure(ServerBuilder<?> serverBuilder) {
        serverBuilder.executor(executor);
    }
}