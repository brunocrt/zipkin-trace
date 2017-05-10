package zipkintrace.common.tracing;

import com.twitter.zipkin.gen.Span;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Slf4j
public abstract class TracingHelper {

    public interface GrpcCall {
        void apply();
    }

    public interface HttpCall<RespT> {
        RespT apply();
    }

    public static void trace(GrpcCall call, Span currentSpan) {
        if (currentSpan != null) {
            MDC.put("span_id", String.format("%016x", currentSpan.getId()));
            MDC.put("trace_id", String.format("%016x", currentSpan.getTrace_id()));
            log.debug("Trace gRPC: " + currentSpan.getName());
        }

        try {
            call.apply();
        } finally {
            MDC.remove("span_id");
            MDC.remove("trace_id");
        }
    }

    public static <RespT> RespT trace(HttpCall<RespT> call, Span currentSpan) {
        if (currentSpan != null) {
            MDC.put("span_id", String.format("%016x", currentSpan.getId()));
            MDC.put("trace_id", String.format("%016x", currentSpan.getTrace_id()));
            log.debug("Trace HTTP: " + currentSpan.getName());
        }

        try {
            return call.apply();
        } finally {
            MDC.remove("span_id");
            MDC.remove("trace_id");
        }
    }
}

