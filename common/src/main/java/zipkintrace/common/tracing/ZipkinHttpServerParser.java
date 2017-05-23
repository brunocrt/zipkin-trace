package zipkintrace.common.tracing;

import brave.SpanCustomizer;
import brave.http.HttpAdapter;
import brave.http.HttpServerParser;

public class ZipkinHttpServerParser extends HttpServerParser {
    @Override
    public <Req> void request(HttpAdapter<Req, ?> adapter, Req req, SpanCustomizer customizer) {
        customizer.name(adapter.method(req).toLowerCase() + " " + adapter.path(req));
    }
}
