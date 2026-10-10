package dev.enemeez.simplefarming.common.registries;

import dev.enemeez.simplefarming.common.block.entity.FermenterBlockEntity;
import dev.enemeez.simplefarming.common.block.menu.FermenterMenu;
import dev.enemeez.simplefarming.platform.ModRegistry;
import dev.enemeez.simplefarming.platform.Registration;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {

    public static final ModRegistry<MenuType<?>> MENU_TYPES = new ModRegistry<>(Registries.MENU);

    // The menu is opened with ServerPlayer#openMenu (vanilla overload), which sends a
    // ClientboundOpenScreenPacket. On the client the vanilla menu factory receives a single-arg
    // supplier and builds the menu from the block-entity synced inventory, exactly like the furnace.
    public static final Registration<MenuType<FermenterMenu>> FERMENTER_MENU = MENU_TYPES.register("fermenter_menu",
        () -> new MenuType<>(FermenterMenu::new, net.minecraft.world.flag.FeatureFlags.DEFAULT_FLAGS)
    );

    /** Convenience alias used by the client-side menu factory. */
    public static MenuType<FermenterMenu> menuType() {
        return FERMENTER_MENU.get();
    }

    static FermenterMenu createMenu(int id, Inventory inventory) {
        return new FermenterMenu(id, inventory);
    }
}
