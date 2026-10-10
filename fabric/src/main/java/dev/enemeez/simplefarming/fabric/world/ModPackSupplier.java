package dev.enemeez.simplefarming.fabric.world;

import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;

/** Supplies {@link ModPackResources} instances for a repository pack. */
record ModPackSupplier(PackType type) implements Pack.ResourcesSupplier {

    @Override
    public PackResources openPrimary(PackLocationInfo location) {
        return new ModPackResources(location, type);
    }

    @Override
    public PackResources openFull(PackLocationInfo location, Pack.Metadata metadata) {
        return new ModPackResources(location, type);
    }
}
