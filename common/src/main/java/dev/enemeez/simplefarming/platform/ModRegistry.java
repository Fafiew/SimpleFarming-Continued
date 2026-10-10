package dev.enemeez.simplefarming.platform;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Collects {@link Registration}s for a single registry and hands them to the loader at the right
 * time. Keeps the shared registry code free of loader APIs: only the registry key is stored, which
 * every loader's deferred-register implementation understands.
 */
public final class ModRegistry<T> {
    private final ResourceKey<? extends Registry<T>> registryKey;
    private final List<Registration<T>> registrations = new ArrayList<>();

    public ModRegistry(ResourceKey<? extends Registry<T>> registryKey) {
        this.registryKey = registryKey;
    }

    public <V extends T> Registration<V> register(String name, Supplier<V> factory) {
        SimpleRegistration<V> registration = new SimpleRegistration<>(name, factory);
        registrations.add((Registration<T>) registration);
        return registration;
    }

    public ResourceKey<? extends Registry<T>> registryKey() {
        return registryKey;
    }

    /** The registry's own id (for example {@code minecraft:block}). */
    public ResourceLocation registryId() {
        return registryKey.location();
    }

    public List<Registration<T>> registrations() {
        return registrations;
    }

    public static ResourceLocation id(String path) {
        return dev.enemeez.simplefarming.common.SimpleFarming.id(path);
    }

    /**
     * Loader-side sink that the mod uses to add items to its creative tab. Implemented per loader
     * because the entry point differs (event, hook or direct mutation).
     */
    @FunctionalInterface
    public interface CreativeTabSink {
        void accept(CreativeModeTab tab, List<ItemStack> items);
    }
}
