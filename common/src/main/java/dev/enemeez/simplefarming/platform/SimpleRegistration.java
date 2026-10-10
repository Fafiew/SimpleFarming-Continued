package dev.enemeez.simplefarming.platform;

import java.util.function.Supplier;

/**
 * A default {@link Registration} that either remembers the value it is handed or, on loaders that
 * expose a live registry handle, delegates to that handle.
 */
public class SimpleRegistration<T> implements Registration<T> {
    private final String id;
    private final Supplier<T> factory;
    private volatile T value;
    private volatile Supplier<T> delegate;

    public SimpleRegistration(String id, Supplier<T> factory) {
        this.id = id;
        this.factory = factory;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Supplier<T> factory() {
        return factory;
    }

    @Override
    public void onRegistered(T value) {
        this.value = value;
    }

    /** Points the handle at the loader's own registry object, if it has one. */
    public void setDelegate(Supplier<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public T get() {
        Supplier<T> current = delegate;
        return current != null ? current.get() : value;
    }
}
