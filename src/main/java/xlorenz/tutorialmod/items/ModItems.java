package xlorenz.tutorialmod.items;

import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.dedicated.management.RpcDiscover;
import net.minecraft.util.Identifier;
import xlorenz.tutorialmod.TutorialMod;
import xlorenz.tutorialmod.items.custom.BoostStaffItem;
import xlorenz.tutorialmod.items.custom.ChiselItem;
import xlorenz.tutorialmod.items.custom.HammerItem;

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

    public static final Item PINK_GARNET_SWORD = register("pink_garnet_sword", Item::new,
            new Item.Settings().sword(ModToolMaterials.PINK_GARNET_TOOL_MATERIAL, 3f,-2.4f)
    );
    public static final Item PINK_GARNET_PICKAXE = register("pink_garnet_pickaxe", Item::new,
            new Item.Settings().pickaxe(ModToolMaterials.PINK_GARNET_TOOL_MATERIAL, 1f,-2.8f)
    );
    public static final Item PINK_GARNET_AXE = register("pink_garnet_axe", new AxeItem(ModToolMaterials.PINK_GARNET_TOOL_MATERIAL, 6f,-3.2f,
            new Item.Settings().registryKey(getKey("pink_garnet_axe")))
    );
    public static final Item PINK_GARNET_SHOVEL = register("pink_garnet_shovel", new ShovelItem(ModToolMaterials.PINK_GARNET_TOOL_MATERIAL, 1.5f,-3.0f,
            new Item.Settings().registryKey(getKey("pink_garnet_shovel")))
    );
    public static final Item PINK_GARNET_HOE = register("pink_garnet_hoe", new HoeItem(ModToolMaterials.PINK_GARNET_TOOL_MATERIAL, 0f,-1f,
            new Item.Settings().registryKey(getKey("pink_garnet_hoe")))
    );


    public static final Item PINK_GARNET_HAMMER = register("pink_garnet_hammer",
            new HammerItem(new Item.Settings().registryKey(getKey("pink_garnet_hammer"))));



    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        Item item = itemFactory.apply(settings.registryKey(getKey(name)));

        Registry.register(Registries.ITEM, getKey(name), item);

        return  item;
    }

    public static RegistryKey<Item> getKey(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));
    }

    public static Item register(String name, Item item) {

        Registry.register(Registries.ITEM, getKey(name), item);

        return  item;
    }



    public static  void initialize() {
        TutorialMod.LOGGER.info("Registering items for " + TutorialMod.MOD_ID);

    }

}