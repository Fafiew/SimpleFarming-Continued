package dev.enemeez.simplefarming.forge;

import dev.enemeez.simplefarming.platform.Platform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

/**
 * Forge implementation of the loader services. Registry registration lives in the entrypoint
 * because Forge's {@code DeferredRegister} takes the mod event bus directly.
 */
public class ForgePlatform implements Platform {

    @Override
    public Path configDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !net.minecraftforge.fml.loading.FMLLoader.isProduction();
    }
}
