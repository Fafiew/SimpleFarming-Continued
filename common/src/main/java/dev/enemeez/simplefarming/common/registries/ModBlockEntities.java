package dev.enemeez.simplefarming.common.registries;

import dev.enemeez.simplefarming.common.block.entity.FermenterBlockEntity;
import dev.enemeez.simplefarming.platform.ModRegistry;
import dev.enemeez.simplefarming.platform.Registration;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final ModRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = new ModRegistry<>(Registries.BLOCK_ENTITY_TYPE);

    public static final Registration<BlockEntityType<FermenterBlockEntity>> FERMENTER = BLOCK_ENTITY_TYPES.register("fermenter",
        () -> BlockEntityType.Builder.of(FermenterBlockEntity::new, ModBlocks.FERMENTER.get()).build(null)
    );
}
