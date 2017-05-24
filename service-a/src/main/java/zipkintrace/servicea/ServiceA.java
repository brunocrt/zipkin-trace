package zipkintrace.servicea;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zipkintrace.servicea.services.ServiceB;

@Slf4j
@Component
public class ServiceA {

    @Autowired
    private ServiceB serviceB;

    public void process() {
        log.info("Running process in service A");
        serviceB.shortRunning();
        //serviceB.longRunning();
    }
}
