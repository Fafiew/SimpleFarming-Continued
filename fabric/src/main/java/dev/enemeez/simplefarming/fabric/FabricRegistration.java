package dev.enemeez.simplefarming.fabric;

import dev.enemeez.simplefarming.common.SimpleFarming;
import dev.enemeez.simplefarming.platform.ModRegistry;
import dev.enemeez.simplefarming.platform.Registration;
import dev.enemeez.simplefarming.platform.SimpleRegistration;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

/**
 * Registers the mod's built-in content into the vanilla registries.
 *
 * <p>The registration must run while the block and item registries are still open, because building
 * a vanilla {@code Block} asks the block registry for an intrusive holder. On Fabric that window
 * closes inside {@code Bootstrap.bootStrap()}, before the loader invokes the mod entrypoint, so the
 * {@code RegistryFreezeMixin} calls {@link #register()} first. The entrypoint calls it again, which
 * is a no-op once the work is done.</p>
 */
public final class FabricRegistration {

    private static boolean registered;

    private FabricRegistration() {
    }

    /** Idempotent: safe to call from both the freeze hook and the entrypoint. */
    public static synchronized void register() {
        if (registered) {
            return;
        }
        registered = true;
        SimpleFarming.register(FabricRegistration::register);
        SimpleFarming.LOGGER.info("Simple Farming: Continued (Fabric) content registered");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T> void register(ModRegistry<T> registry) {
        // Dynamic registries (placed/configured features) are datapack-loaded, not built-in, and
        // the mod registers nothing into them at this stage.
        if (registry.registrations().isEmpty()) {
            return;
        }
        Registry<T> target = (Registry<T>) registryFor(registry.registryKey());
        for (Registration<T> registration : registry.registrations()) {
            T value = Registry.register(target, SimpleFarming.id(registration.id()), registration.factory().get());
            if (registration instanceof SimpleRegistration<T> simple) {
                simple.onRegistered(value);
            }
        }
    }

    /**
     * Maps a registry key to its built-in registry. The root registry cannot be used here: this
     * runs before {@code BuiltInRegistries.freeze()} has frozen the root, so the root's holders do
     * not have bound values yet and {@code REGISTRY.get(key)} throws. The typed static fields are
     * already assigned by {@code createContents} at this point, so they are read directly.
     */
    private static Registry<?> registryFor(ResourceKey<? extends Registry<?>> key) {
        if (key == Registries.BLOCK) return BuiltInRegistries.BLOCK;
        if (key == Registries.ITEM) return BuiltInRegistries.ITEM;
        if (key == Registries.FOLIAGE_PLACER_TYPE) return BuiltInRegistries.FOLIAGE_PLACER_TYPE;
        if (key == Registries.SOUND_EVENT) return BuiltInRegistries.SOUND_EVENT;
        if (key == Registries.BLOCK_ENTITY_TYPE) return BuiltInRegistries.BLOCK_ENTITY_TYPE;
        if (key == Registries.MENU) return BuiltInRegistries.MENU;
        if (key == Registries.RECIPE_SERIALIZER) return BuiltInRegistries.RECIPE_SERIALIZER;
        if (key == Registries.CREATIVE_MODE_TAB) return BuiltInRegistries.CREATIVE_MODE_TAB;
        throw new IllegalStateException("Simple Farming has no registry binding for " + key);
    }
}
