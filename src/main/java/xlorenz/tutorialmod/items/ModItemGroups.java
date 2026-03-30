package xlorenz.tutorialmod.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xlorenz.tutorialmod.TutorialMod;
import xlorenz.tutorialmod.block.ModBlocks;


public class ModItemGroups {

    public static  final  ItemGroup GARNET_ORES_TAB = register("garnet_ores_group", ModItems.PINK_GARNET, ((displayContext, entries) -> {
        entries.add(ModItems.RAW_PINK_GARNET);
        entries.add(ModItems.PINK_GARNET);
        entries.add(ModItems.ADAMANTITE_INGOT);
        entries.add(ModItems.CAULIFLOWER);
        entries.add(ModItems.STAR_LIGHT_ASHES);
    }));

    public static  final  ItemGroup GARNET_BLOCK_TAB = register("garnet_blocks_group", ModBlocks.PINK_GARNET_BLOCK, ((displayContext, entries) -> {
        entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
        entries.add(ModBlocks.PINK_GARNET_BLOCK);
        entries.add(ModBlocks.PINK_GARNET_ORE);
        entries.add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE);
        entries.add(ModBlocks.MAGIC_BLOCK);

        entries.add(ModBlocks.PINK_GARNET_SLAB);
        entries.add(ModBlocks.PINK_GARNET_DOOR);
        entries.add(ModBlocks.PINK_GARNET_TRAPDOOR);
        entries.add(ModBlocks.PINK_GARNET_BUTTON);
        entries.add(ModBlocks.PINK_GARNET_PRESSURE_PLATE);
        entries.add(ModBlocks.PINK_GARNET_STAIRS);
        entries.add(ModBlocks.PINK_GARNET_FENCE);
        entries.add(ModBlocks.PINK_GARNET_FENCE_GATE);
        entries.add(ModBlocks.PINK_GARNET_WALL);

        entries.add(ModBlocks.PINK_GARNET_LAMP);
    }));

    public static  final ItemGroup GARNET_TOOLS_TAB = register("garnet_tools_group", ModItems.CHISEL, (((displayContext, entries) -> {
        entries.add(ModItems.CHISEL);
        entries.add(ModItems.BOOST_STAFF);
        entries.add(ModItems.PINK_GARNET_SWORD);
        entries.add(ModItems.PINK_GARNET_PICKAXE);
        entries.add(ModItems.PINK_GARNET_AXE);
        entries.add(ModItems.PINK_GARNET_SHOVEL);
        entries.add(ModItems.PINK_GARNET_HOE);
        entries.add(ModItems.PINK_GARNET_HAMMER);

        entries.add(ModItems.PINK_GARNET_HELMET);
        entries.add(ModItems.PINK_GARNET_CHESTPLATE);
        entries.add(ModItems.PINK_GARNET_LEGGINGS);
        entries.add(ModItems.PINK_GARNET_BOOTS);
    })));


    public static ItemGroup register(String name, net.minecraft.item.ItemConvertible icon, ItemGroup.EntryCollector entries) {
        RegistryKey<ItemGroup> groupKey = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(TutorialMod.MOD_ID, name));

        ItemGroup group = FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.tutorial_mod." + name))
                .icon(() -> new ItemStack(icon))
                .entries(entries)
                .build();

        Registry.register(Registries.ITEM_GROUP, groupKey, group);

        return  group;
    }
    public static ItemGroup register(String name, Item icon) {
        RegistryKey<ItemGroup> groupKey = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(TutorialMod.MOD_ID, name));

        ItemGroup group = FabricItemGroup.builder()
                .displayName(Text.translatable(name))
                .icon(() -> new ItemStack(icon))
                .build();

        Registry.register(Registries.ITEM_GROUP, groupKey, group);

        return  group;
    }


    public static void initialize() {
        TutorialMod.LOGGER.info("Registering item groups for " + TutorialMod.MOD_ID);
    }
}
