package zipkintrace.serviceb.services;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import zipkintrace.serviceb.grpc.servicec.GrpcServiceCClient;

@Slf4j
@Component
public class ServiceC {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GrpcServiceCClient grpcServiceCClient;

    public void callViaHttp() {
        val cResult = restTemplate.getForEntity("http://localhost:8100/v1/c", CResource.class);
        log.info("Got HTTP response from service C: {}", cResult.getBody().getText());
    }

    public void callViaGrpc() {
        val cResult = grpcServiceCClient.callC();
        log.info("Got gRPC response from service C: {}", cResult);
    }
}
