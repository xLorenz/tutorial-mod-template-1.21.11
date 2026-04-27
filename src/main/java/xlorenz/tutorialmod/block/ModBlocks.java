package xlorenz.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
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
import xlorenz.tutorialmod.block.custom.CauliflowerCropBlock;
import xlorenz.tutorialmod.block.custom.HoneyBerryBushBlock;
import xlorenz.tutorialmod.block.custom.MagicBlock;
import xlorenz.tutorialmod.block.custom.PinkGarnetLampBlock;
import xlorenz.tutorialmod.sound.ModSounds;

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
            .sounds(ModSounds.MAGIC_BLOCK_SOUNDS)
    );

    public static final Block PINK_GARNET_STAIRS = register("pink_garnet_stairs", new StairsBlock(ModBlocks.PINK_GARNET_BLOCK.getDefaultState(), AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .registryKey(getRegistryKey("pink_garnet_stairs"))
    ));
    public static final Block PINK_GARNET_SLAB = register("pink_garnet_slab", new SlabBlock(AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .registryKey(getRegistryKey("pink_garnet_slab"))
    ));
    public static final Block PINK_GARNET_BUTTON = register("pink_garnet_button", new ButtonBlock(BlockSetType.IRON, 2, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .noCollision()
            .registryKey(getRegistryKey("pink_garnet_button"))
    ));
    public static final Block PINK_GARNET_PRESSURE_PLATE = register("pink_garnet_pressure_plate", new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .registryKey(getRegistryKey("pink_garnet_pressure_plate"))
    ));
    public static final Block PINK_GARNET_FENCE = register("pink_garnet_fence", new FenceBlock(AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .registryKey(getRegistryKey("pink_garnet_fence"))
    ));

    public static final Block PINK_GARNET_FENCE_GATE = register("pink_garnet_fence_gate", new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .registryKey(getRegistryKey("pink_garnet_fence_gate"))
    ));
    public static final Block PINK_GARNET_WALL = register("pink_garnet_wall", new WallBlock(AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .registryKey(getRegistryKey("pink_garnet_wall"))
    ));
    public static final Block PINK_GARNET_DOOR = register("pink_garnet_door", new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .nonOpaque()
            .registryKey(getRegistryKey("pink_garnet_door"))
    ));
    public static final Block PINK_GARNET_TRAPDOOR = register("pink_garnet_trapdoor", new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()
            .nonOpaque()
            .registryKey(getRegistryKey("pink_garnet_trapdoor"))
    ));

    public static final Block PINK_GARNET_LAMP = register("pink_garnet_lamp", new PinkGarnetLampBlock(AbstractBlock.Settings.create()
            .strength(1f)
            .requiresTool().
            luminance(state -> state.get(PinkGarnetLampBlock.CLICKED) ? 15 : 0)
            .registryKey(getRegistryKey("pink_garnet_lamp"))
    ));

    public static final Block CAULIFLOWER_CROP = registerWithoutItem("cauliflower_crop", CauliflowerCropBlock::new, AbstractBlock.Settings.create()
            .noCollision().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN));

    public static final Block HONEY_BERRY_BUSH = registerWithoutItem("honey_berry_bush", HoneyBerryBushBlock::new, AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH));

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

    public static RegistryKey<Block> getRegistryKey(String name) {
        return  RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
    }

    public static Block registerWithoutItem(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));

        Block block = blockFactory.apply(settings.registryKey(blockKey));

        Registry.register(Registries.BLOCK, blockKey, block);

        return  block;
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
