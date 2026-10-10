package dev.enemeez.simplefarming.platform;

import java.util.function.Supplier;

/**
 * A single deferred object registration. The same instance doubles as the public handle used by the
 * shared code, so callers can look the value up once registration has run.
 */
public interface Registration<T> extends Supplier<T> {
    String id();

    Supplier<T> factory();

    /** Called by the loader with the registered value (or {@code null} if none was produced). */
    void onRegistered(T value);

    /** The registered value, or {@code null} before registration completes. */
    @Override
    T get();
}
