package zipkintrace.common.tracing;

import brave.SpanCustomizer;
import brave.http.HttpAdapter;
import brave.http.HttpClientParser;

public class ZipkinHttpClientParser extends HttpClientParser {
    @Override
    public <Req> void request(HttpAdapter<Req, ?> adapter, Req req, SpanCustomizer customizer) {
        customizer.name(adapter.method(req).toLowerCase() + " " + adapter.path(req));
    }
}
