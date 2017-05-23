package zipkintrace.common.tracing;

import brave.http.HttpTracing;
import brave.spring.webmvc.TracingHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ZipkinWebConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    AsyncHandlerInterceptor tracingInterceptor(HttpTracing httpTracing) {
        return TracingHandlerInterceptor.create(httpTracing);
    }

    @Autowired
    private AsyncHandlerInterceptor tracingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tracingInterceptor);
    }
}
