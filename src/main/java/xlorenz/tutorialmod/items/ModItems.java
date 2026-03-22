package xlorenz.tutorialmod.items;

import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import xlorenz.tutorialmod.TutorialMod;
import xlorenz.tutorialmod.items.custom.BoostStaffItem;
import xlorenz.tutorialmod.items.custom.ChiselItem;

import java.util.function.Function;

public  class ModItems {

    public static final Item ADAMANTITE_INGOT = register("adamantite_ingot", Item::new, new Item.Settings());
    public static final Item PINK_GARNET = register("pink_garnet", Item::new, new Item.Settings());
    public static final Item RAW_PINK_GARNET = register("raw_pink_garnet", Item::new, new Item.Settings());

    public static final Item CHISEL = register("chisel", ChiselItem::new,
            new Item.Settings().maxDamage(32));

    public static final Item BOOST_STAFF = register("boost_staff", BoostStaffItem::new,
            new Item.Settings().maxDamage(16));

    public static final Item CAULIFLOWER = register("cauliflower", Item::new,
            new Item.Settings().food(ModFoodComponents.CAULIFLOWER, ModFoodComponents.CAULIFLOWER_EFFECTS));

    public static  final Item STAR_LIGHT_ASHES = register("star_light_ashes", Item::new, new Item.Settings());



    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);

        return  item;
    }


    public static  void initialize() {
        TutorialMod.LOGGER.info("Registering items for " + TutorialMod.MOD_ID);

    }

}