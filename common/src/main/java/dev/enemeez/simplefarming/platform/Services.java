package dev.enemeez.simplefarming.platform;

import java.util.ServiceLoader;

/**
 * Resolves the loader-specific {@link Platform} through {@link ServiceLoader}. Each loader's source
 * set ships a {@code META-INF/services} entry pointing at its implementation.
 */
public final class Services {
    private static volatile Platform platform;

    private Services() {
    }

    public static Platform platform() {
        Platform result = platform;
        if (result == null) {
            synchronized (Services.class) {
                result = platform;
                if (result == null) {
                    result = ServiceLoader.load(Platform.class).findFirst()
                        .orElseThrow(() -> new IllegalStateException("No " + Platform.class.getName() + " implementation found"));
                    platform = result;
                }
            }
        }
        return result;
    }
}
