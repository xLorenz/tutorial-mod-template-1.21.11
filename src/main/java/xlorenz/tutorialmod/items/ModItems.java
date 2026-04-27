package xlorenz.tutorialmod.items;

import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.dedicated.management.RpcDiscover;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import xlorenz.tutorialmod.TutorialMod;
import xlorenz.tutorialmod.block.ModBlocks;
import xlorenz.tutorialmod.items.custom.BoostStaffItem;
import xlorenz.tutorialmod.items.custom.ChiselItem;
import xlorenz.tutorialmod.items.custom.HammerItem;
import xlorenz.tutorialmod.items.custom.ModArmorItem;
import xlorenz.tutorialmod.sound.ModSounds;
import xlorenz.tutorialmod.trim.ModTrimMaterials;

import java.util.function.Function;

public  class ModItems {

    public static final Item ADAMANTITE_INGOT = register("adamantite_ingot", Item::new, new Item.Settings());
    public static final Item PINK_GARNET = register("pink_garnet", Item::new, new Item.Settings().trimMaterial(ModTrimMaterials.PINK_GARNET));
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


    public static final Item PINK_GARNET_HELMET = register("pink_garnet_helmet", new ModArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, EquipmentType.HELMET,
                    new Item.Settings().registryKey(getKey("pink_garnet_helmet"))));
    public static final Item PINK_GARNET_CHESTPLATE = register("pink_garnet_chestplate", new ModArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, EquipmentType.CHESTPLATE,
                    new Item.Settings().registryKey(getKey("pink_garnet_chestplate"))));
    public static final Item PINK_GARNET_LEGGINGS = register("pink_garnet_leggings", new ModArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, EquipmentType.LEGGINGS,
                    new Item.Settings().registryKey(getKey("pink_garnet_leggings"))));
    public static final Item PINK_GARNET_BOOTS = register("pink_garnet_boots", new ModArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, EquipmentType.BOOTS,
                    new Item.Settings().registryKey(getKey("pink_garnet_boots"))));


    public static final Item PINK_GARNET_HORSE_ARMOR = register("pink_garnet_horse_armor", Item::new,
            new Item.Settings().horseArmor(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL));


    public static final Item KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE = register("kaupen_armor_smithing_template", SmithingTemplateItem::of, new Item.Settings().rarity(Rarity.RARE));

    public static final Item KAUPEN_BOW = register("kaupen_bow", BowItem::new, new Item.Settings().maxDamage(200));

    public static final Item BAR_BRAWL_MUSIC_DISC = register("bar_brawl_music_disc", Item::new, new Item.Settings().maxCount(1).jukeboxPlayable(ModSounds.BAR_BRAWL_KEY));

    public static final Item CAULIFLOWER_SEEDS = register("cauliflower_seeds", new BlockItem(ModBlocks.CAULIFLOWER_CROP, new Item.Settings()
            .registryKey(getKey("cauliflower_seeds")).useItemPrefixedTranslationKey()));

    public static final Item HONEY_BERRIES = register("honey_berries", new BlockItem(ModBlocks.HONEY_BERRY_BUSH, new Item.Settings()
            .registryKey(getKey("honey_berries")).useItemPrefixedTranslationKey().food(ModFoodComponents.HONEY_BERRIES)));

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        Item item = itemFactory.apply(settings.registryKey(getKey(name)));

        return register(name, item);
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