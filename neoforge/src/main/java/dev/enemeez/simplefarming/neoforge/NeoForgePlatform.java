package dev.enemeez.simplefarming.neoforge;

import dev.enemeez.simplefarming.platform.Platform;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

/**
 * NeoForge implementation of the loader services. Registry registration lives in the entrypoint
 * because NeoForge's {@code DeferredRegister} takes the mod event bus directly.
 */
public class NeoForgePlatform implements Platform {

    @Override
    public Path configDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !net.neoforged.fml.loading.FMLLoader.isProduction();
    }
}
