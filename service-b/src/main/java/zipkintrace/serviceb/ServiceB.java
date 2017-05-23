package zipkintrace.serviceb;

import brave.Tracing;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zipkintrace.serviceb.services.ServiceC;

@Slf4j
@Component
public class ServiceB {

    @Autowired
    private Tracing tracing;

    @Autowired
    private ServiceC serviceC;

    @SneakyThrows
    public int shortRunning() {
        log.info("shortRunning was called: ");
        Thread.sleep(100);
        return 100;
    }

    @SneakyThrows
    public int longRunning() {
        log.info("longRunning was called: ");
        serviceC.callC();
        Thread.sleep(300);
        return 300;
    }
}
