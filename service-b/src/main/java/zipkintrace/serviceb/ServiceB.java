package zipkintrace.serviceb;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zipkintrace.serviceb.services.ServiceC;

@Slf4j
@Component
public class ServiceB {

    @Autowired
    private ServiceC serviceC;

    public ServiceB() {
        log.info("Creating service B");
    }

    @SneakyThrows
    public int shortRunning() {
        log.info("Calling '/c' on service C via HTTP in 'shortRunning'");
        serviceC.callViaHttp();
        Thread.sleep(100);
        return 100;
    }

    @SneakyThrows
    public int longRunning() {
        log.info("Calling '/c' on service C via HTTP in 'shortRunning'");
        serviceC.callViaHttp();
        Thread.sleep(300);
        return 300;
    }

    @SneakyThrows
    public int viaC() {
        log.info("Calling 'callC' on service C via gRPC' in 'viaC'");
        serviceC.callViaGrpc();
        return 500;
    }
}
