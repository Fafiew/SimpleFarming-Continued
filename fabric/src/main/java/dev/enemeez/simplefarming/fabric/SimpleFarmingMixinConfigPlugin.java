package dev.enemeez.simplefarming.fabric;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * Keeps the mod's own creative-tab paging and Fabric API's from fighting over the creative screen.
 *
 * <p>The mod ships a hand-rolled paging implementation (the {@code client.Creative*Mixins} and
 * {@code SimpleFarmingCreativeTabs}) so that the Fabric build needs no Fabric API. Fabric API's
 * item-group module implements the same paging, and its mixin and ours target the same vanilla
 * classes and inject at the same points. When both apply, they clash and the creative screen breaks.
 * This plugin detects the item-group module and skips our paging mixins when it is present, letting
 * Fabric API own the paging. The tab registration and {@code suggests} metadata already treat Fabric
 * API as optional.</p>
 */
public final class SimpleFarmingMixinConfigPlugin implements IMixinConfigPlugin {

    /**
     * Detect the specific module rather than the aggregate {@code fabric-api} id, so a stripped
     * distribution that only ships this module is recognised too.
     */
    private static final String ITEM_GROUP_MODULE = "fabric-item-group-api-v1";

    private static final Set<String> FABRIC_API_PAGING_MIXINS = Set.of(
        "CreativeModeTabAccessor",
        "CreativeModeTabsMixin",
        "CreativeTabPagingMixin",
        "CreativeTabSelectionMixin"
    );

    private boolean fabricApiPresent;

    @Override
    public void onLoad(String mixinPackage) {
        fabricApiPresent = FabricLoader.getInstance().isModLoaded(ITEM_GROUP_MODULE);
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (FABRIC_API_PAGING_MIXINS.contains(simpleName(mixinClassName))) {
            return !fabricApiPresent;
        }
        return true;
    }

    private static String simpleName(String mixinClassName) {
        return mixinClassName.substring(mixinClassName.lastIndexOf('.') + 1);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
