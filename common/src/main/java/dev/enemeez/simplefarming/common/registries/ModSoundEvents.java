package dev.enemeez.simplefarming.common.registries;

import dev.enemeez.simplefarming.common.SimpleFarming;
import dev.enemeez.simplefarming.platform.ModRegistry;
import dev.enemeez.simplefarming.platform.Registration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {
    public static final ModRegistry<SoundEvent> SOUND_EVENTS = new ModRegistry<>(Registries.SOUND_EVENT);

    public static final Registration<SoundEvent> MUSIC_DISC_NOURISH = SOUND_EVENTS.register("music_disc.nourish",
        () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SimpleFarming.MOD_ID, "music_disc.nourish"))
    );
}
