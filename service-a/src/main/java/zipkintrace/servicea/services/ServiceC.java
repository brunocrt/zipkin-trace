package zipkintrace.servicea.services;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ServiceC {

    @Autowired
    private RestTemplate restTemplate;

    public void callC() {
        log.info("Calling 'callC' on Service C via HTTP");
        val cResult = restTemplate.getForEntity("http://localhost:8100/v1/c", CResource.class);
        log.info("Result of 'callC' on Service C: {}", cResult.getBody().getText());
    }
}
