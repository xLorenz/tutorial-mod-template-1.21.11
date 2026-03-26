package xlorenz.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import xlorenz.tutorialmod.TutorialMod;
import xlorenz.tutorialmod.block.custom.MagicBlock;

import java.util.function.Function;


public class ModBlocks {

    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            );
    public static final Block RAW_PINK_GARNET_BLOCK = registerBlock("raw_pink_garnet_block",
            AbstractBlock.Settings.create()
                    .strength(3f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
    );

    public static final Block PINK_GARNET_ORE = registerExperienceDroppingBlock("pink_garnet_ore", UniformIntProvider.create(2,5),
            AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
    );
    public static final Block PINK_GARNET_DEEPSLATE_ORE = registerExperienceDroppingBlock("pink_garnet_deepslate_ore", UniformIntProvider.create(2,5),
                    AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
    );

    public  static  final Block MAGIC_BLOCK = register("magic_block", MagicBlock::new, AbstractBlock.Settings.create()
            .strength(1f)
            .requiresTool()
    );

    public static final Block PINK_GARNET_STAIRS = register("pink_garnet_stairs", new StairsBlock(ModBlocks.PINK_GARNET_BLOCK.getDefaultState(), AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
    ));
    public static final Block PINK_GARNET_SLAB = register("pink_garnet_slab", new SlabBlock(AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
    ));
    public static final Block PINK_GARNET_BUTTON = register("pink_garnet_button", new ButtonBlock(BlockSetType.IRON, 2, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .noCollision()
    ));
    public static final Block PINK_GARNET_PRESSURE_PLATE = register("pink_garnet_pressure_plate", new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
    ));
    public static final Block PINK_GARNET_FENCE = register("pink_garnet_fence", new FenceBlock(AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
    ));

    public static final Block PINK_GARNET_FENCE_GATE = register("pink_garnet_fence_gate", new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
    ));
    public static final Block PINK_GARNET_WALL = register("pink_garnet_wall", new WallBlock(AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
    ));
    public static final Block PINK_GARNET_DOOR = register("pink_garnet_door", new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .nonOpaque()
    ));
    public static final Block PINK_GARNET_TRAPDOOR = register("pink_garnet_trapdoor", new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .nonOpaque()
    ));



    private static Block registerBlock(String name, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
        Block block = new Block(settings.registryKey(key));

        registerBlockItem(name, block);

        return Registry.register(Registries.BLOCK, key, block);

    }

    private static Block registerExperienceDroppingBlock(String name, UniformIntProvider values, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));

        ExperienceDroppingBlock block = new ExperienceDroppingBlock(values, settings.registryKey(key));

        registerBlockItem(name, block);

        return Registry.register(Registries.BLOCK, key, block);
    }

    public static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));

        Block block = blockFactory.apply(settings.registryKey(blockKey));

        Registry.register(Registries.BLOCK, blockKey, block);
        registerBlockItem(name, block);

        return  block;
    }
    public static Block register(String name, Block block) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));

        block.getSettings().registryKey((blockKey));

        Registry.register(Registries.BLOCK, blockKey, block);
        registerBlockItem(name, block);

        return  block;
    }


    private  static  void registerBlockItem(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));

        Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), new BlockItem(block, new Item.Settings().registryKey(key).useBlockPrefixedTranslationKey()));
    }

    public static void initialize(){
        TutorialMod.LOGGER.info("Registering blocks for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(PINK_GARNET_BLOCK);
            fabricItemGroupEntries.add(RAW_PINK_GARNET_BLOCK);
        });
    }
}
