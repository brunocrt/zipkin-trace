package zipkintrace.servicea.web;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zipkintrace.servicea.ServiceA;
import zipkintrace.servicea.web.resources.AResource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/v1")
public class AController {

    @Autowired
    private ServiceA serviceA;

    @RequestMapping(path = "/a", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public HttpEntity<AResource> getA() {
        log.info("A:HTTP -> B:gRPC -> C:HTTP");
        serviceA.processA();
        val aResource = new AResource("test call A");
        return new ResponseEntity<>(aResource, HttpStatus.OK);
    }

    @RequestMapping(path = "/b", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public HttpEntity<AResource> getB() {
        log.info("A:HTTP -> B:gRPC -> C:gRPC");
        serviceA.processB();
        val aResource = new AResource("test call B");
        return new ResponseEntity<>(aResource, HttpStatus.OK);
    }

    @RequestMapping(path = "/c", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public HttpEntity<AResource> getC() {
        log.info("A:HTTP -> C:HTTP");
        serviceA.processC();
        val aResource = new AResource("test call C");
        return new ResponseEntity<>(aResource, HttpStatus.OK);
    }
}
