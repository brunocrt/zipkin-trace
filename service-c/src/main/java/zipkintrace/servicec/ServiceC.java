package zipkintrace.servicec;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceC {

    @SneakyThrows
    public void process() {
        log.info("Running 'process' on service C");
        Thread.sleep(240);
    }

    @SneakyThrows
    public void callC() {
        log.info("Running 'callC' on service C");
        Thread.sleep(130);
    }
}
