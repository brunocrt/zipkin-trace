package zipkintrace.servicea;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zipkintrace.servicea.services.ServiceB;
import zipkintrace.servicea.services.ServiceC;

@Slf4j
@Component
public class ServiceA {

    @Autowired
    private ServiceB serviceB;

    @Autowired
    private ServiceC serviceC;

    public void processA() {
        log.info("Running process A in service A");
        serviceB.shortRunning();
        serviceB.longRunning();
    }

    public void processB() {
        log.info("Running process B in service A");
        serviceB.viaC();
    }

    public void processC() {
        log.info("Running process C in service A");
        serviceC.callC();
    }
}
