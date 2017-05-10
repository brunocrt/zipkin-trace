package zipkintrace.common.tracing;

import com.github.kristofa.brave.http.HttpRequest;
import com.github.kristofa.brave.http.SpanNameProvider;
import org.springframework.stereotype.Component;

@Component
public class HttpSpanNameProvider implements SpanNameProvider {
    @Override
    public String spanName(HttpRequest request) {
        return request.getUri().getPath();
    }
}