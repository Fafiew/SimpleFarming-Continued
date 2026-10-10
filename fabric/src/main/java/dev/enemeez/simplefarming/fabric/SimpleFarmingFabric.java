package dev.enemeez.simplefarming.fabric;

import dev.enemeez.simplefarming.common.SimpleFarming;
import net.fabricmc.api.ModInitializer;

/**
 * Fabric entrypoint. The block and item registries are already frozen by the time the loader
 * invokes this entrypoint, so the actual registration happens in {@link FabricRegistration}, which
 * the {@code RegistryFreezeMixin} runs just before the freeze. The remaining setup is safe here.
 */
public class SimpleFarmingFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        // Fallback for any content the freeze hook could not cover; a no-op once it has run.
        FabricRegistration.register();
        SimpleFarming.commonSetup();
        SimpleFarming.LOGGER.info("Simple Farming: Continued (Fabric) setup complete");
    }
}
