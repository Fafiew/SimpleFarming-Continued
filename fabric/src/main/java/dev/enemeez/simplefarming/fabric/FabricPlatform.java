package dev.enemeez.simplefarming.fabric;

import dev.enemeez.simplefarming.platform.Platform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

/** Fabric implementation of the loader services. */
public class FabricPlatform implements Platform {

    @Override
    public Path configDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
