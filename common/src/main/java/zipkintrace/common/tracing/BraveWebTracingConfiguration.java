package zipkintrace.common.tracing;

import com.github.kristofa.brave.spring.ServletHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Import(ServletHandlerInterceptor.class)
public class BraveWebTracingConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ServletHandlerInterceptor servletHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(servletHandlerInterceptor);
    }
}
