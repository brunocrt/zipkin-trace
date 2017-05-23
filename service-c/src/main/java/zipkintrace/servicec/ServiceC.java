package zipkintrace.servicec;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceC {

    @SneakyThrows
    public void process() {
        log.info("Processing in Service C");
        Thread.sleep(240);
    }
}
