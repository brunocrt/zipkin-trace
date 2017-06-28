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
        log.info("Calling 'shortRunning' on service B");
        serviceB.shortRunning();
        log.info("Calling 'longRunning' on service B");
        serviceB.longRunning();
    }

    public void processB() {
        log.info("Calling 'viaC' on service B");
        serviceB.viaC();
    }

    public void processC() {
        log.info("Calling 'callC' on service C");
        serviceC.callC();
    }
}
