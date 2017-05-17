package zipkintrace.serviceb;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceB {

    @SneakyThrows
    public int shortRunning() {
        log.info("shortRunning was called");
        Thread.sleep(100);
        return 100;
    }

    @SneakyThrows
    public int longRunning() {
        log.info("shortRunning was called");
        Thread.sleep(1000);
        return 1000;
    }
}
