package xlorenz.tutorialmod.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import xlorenz.tutorialmod.TutorialMod;

import java.util.function.Function;

public  class ModItems {

    public static  Item ADAMANTITE_INGOT = register("adamantite_ingot", Item::new, new Item.Settings());

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