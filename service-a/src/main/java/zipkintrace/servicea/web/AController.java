package zipkintrace.servicea.web;

import com.github.kristofa.brave.Brave;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zipkintrace.common.tracing.TracingHelper;
import zipkintrace.servicea.web.resources.AResource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/v1/a")
public class AController {

    @Autowired
    private Brave brave;

    @RequestMapping(path="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public HttpEntity<AResource> getA() {
        return TracingHelper.trace(() -> {
            val aResource = new AResource("test call A");
            log.debug("Created AResource....................................................................................");
            return new ResponseEntity<AResource>(aResource, HttpStatus.OK);
        }, brave.serverSpanThreadBinder().getCurrentServerSpan().getSpan());
    }
}
