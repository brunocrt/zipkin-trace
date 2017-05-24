package zipkintrace.serviceb.grpc.serviceb;

import brave.grpc.GrpcTracing;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zipkintrace.serviceb.grpc.serviceb.generated.ServiceBGrpc;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.Executor;

@Slf4j
@Component
public class ServiceBServer {

    @Autowired
    private Executor executor;

    @Autowired
    private GrpcTracing grpcTracing;

    @Autowired
    private GrpcServiceBImpl grpcServiceB;

    private Server server;

    @PostConstruct
    public void postConstruct() throws InterruptedException, IOException {
        start();
        blockUntilShutdown();
    }

    public void start() throws IOException {
            /* The port on which the server should run */
        int port = 6600;
        server = ServerBuilder.forPort(port)
                .addService(ServerInterceptors.intercept(grpcServiceB, grpcTracing.newServerInterceptor()))
                .executor(executor)
                .build()
                .start();
        log.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                ServiceBServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
