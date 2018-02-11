package zipkintrace.servicea;

import brave.Tracing;

import java.util.concurrent.Callable;

public class ZipkinContextCallable<K> implements Callable<K> {

    private final Callable<K> realCallable;

    ZipkinContextCallable(Callable<K> actual) {
        realCallable = Tracing.current().currentTraceContext().wrap(actual);
    }

    @Override
    public K call() throws Exception {
        return realCallable.call();
    }
}