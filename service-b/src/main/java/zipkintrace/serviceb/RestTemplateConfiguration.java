package zipkintrace.serviceb;

import brave.http.HttpTracing;
import brave.spring.web.TracingClientHttpRequestInterceptor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Autowired
    public HttpTracing httpTracing;

    @Bean
    public RestTemplate restTemplate() {
        val restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(TracingClientHttpRequestInterceptor.create(httpTracing));
        return restTemplate;
    }
}
