package zipkintrace.servicec.web;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zipkintrace.servicec.ServiceC;
import zipkintrace.servicec.web.resources.CResource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/v1/c")
public class CController {

    @Autowired
    private ServiceC serviceC;

    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public HttpEntity<CResource> getC() {
        log.info("Received /c request via HTTP");
        serviceC.process();
        val cResource = new CResource("test call C");
        return new ResponseEntity<>(cResource, HttpStatus.OK);
    }
}
