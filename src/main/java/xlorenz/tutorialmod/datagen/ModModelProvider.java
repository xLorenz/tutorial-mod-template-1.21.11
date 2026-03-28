package xlorenz.tutorialmod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import xlorenz.tutorialmod.TutorialMod;
import xlorenz.tutorialmod.block.ModBlocks;
import xlorenz.tutorialmod.block.custom.PinkGarnetLampBlock;
import xlorenz.tutorialmod.items.ModItems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static net.minecraft.client.data.BlockStateModelGenerator.createBooleanModelMap;
import static net.minecraft.client.data.BlockStateModelGenerator.createWeightedVariant;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        registerBlockAndItem(blockStateModelGenerator, ModBlocks.MAGIC_BLOCK);
        //registerBlockAndItem(blockStateModelGenerator, ModBlocks.PINK_GARNET_BLOCK);
        registerBlockAndItem(blockStateModelGenerator, ModBlocks.RAW_PINK_GARNET_BLOCK);
        registerBlockAndItem(blockStateModelGenerator, ModBlocks.PINK_GARNET_DEEPSLATE_ORE);
        registerBlockAndItem(blockStateModelGenerator, ModBlocks.PINK_GARNET_ORE);

        BlockStateModelGenerator.BlockTexturePool pinkGarnetPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PINK_GARNET_BLOCK);
        pinkGarnetPool.stairs(ModBlocks.PINK_GARNET_STAIRS);
        pinkGarnetPool.slab(ModBlocks.PINK_GARNET_SLAB);
        pinkGarnetPool.button(ModBlocks.PINK_GARNET_BUTTON);
        pinkGarnetPool.pressurePlate(ModBlocks.PINK_GARNET_PRESSURE_PLATE);
        pinkGarnetPool.fence(ModBlocks.PINK_GARNET_FENCE);
        pinkGarnetPool.fenceGate(ModBlocks.PINK_GARNET_FENCE_GATE);
        pinkGarnetPool.wall(ModBlocks.PINK_GARNET_WALL);

        blockStateModelGenerator.registerTrapdoor(ModBlocks.PINK_GARNET_TRAPDOOR);

        blockStateModelGenerator.registerDoor(ModBlocks.PINK_GARNET_DOOR);


        WeightedVariant weightedVariant = createWeightedVariant(TexturedModel.CUBE_ALL.upload(ModBlocks.PINK_GARNET_LAMP, blockStateModelGenerator.modelCollector));
        WeightedVariant weightedVariant2 = createWeightedVariant(blockStateModelGenerator.createSubModel(ModBlocks.PINK_GARNET_LAMP, "_on", Models.CUBE_ALL, TextureMap::all));
        blockStateModelGenerator.blockStateCollector
                .accept(VariantsBlockModelDefinitionCreator.of(ModBlocks.PINK_GARNET_LAMP).with(createBooleanModelMap(PinkGarnetLampBlock.CLICKED, weightedVariant2, weightedVariant)));    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ADAMANTITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOST_STAFF, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAULIFLOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.STAR_LIGHT_ASHES, Models.GENERATED);

        itemModelGenerator.register(ModItems.PINK_GARNET_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_HAMMER, Models.HANDHELD_MACE);
    }

    private void registerBlockAndItem(BlockStateModelGenerator gen, Block block) {

        gen.registerSimpleCubeAll(block);
        gen.registerItemModel(block.asItem(), Identifier.of(TutorialMod.MOD_ID, "block/" + Registries.BLOCK.getId(block).getPath()));

    }
    private void registerItem(BlockStateModelGenerator gen, Block block) {

        gen.registerItemModel(block.asItem(), Identifier.of(TutorialMod.MOD_ID, "block/" + Registries.BLOCK.getId(block).getPath()));

    }

    private void registerItems(BlockStateModelGenerator gen, List<Block> blocks) {
        for(Block block : blocks) {
            registerItem(gen, block);
        }
    }
}