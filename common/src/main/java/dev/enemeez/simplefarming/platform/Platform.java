package dev.enemeez.simplefarming.platform;

import java.nio.file.Path;

/**
 * Loader-specific services that the shared code needs. Each loader implements this and publishes it
 * via {@link Services} using {@link java.util.ServiceLoader}.
 */
public interface Platform {

    /** Directory where the mod's config file lives (e.g. {@code config/}). */
    Path configDir();

    boolean isDevelopmentEnvironment();
}
