package xlorenz.tutorialmod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import xlorenz.tutorialmod.TutorialMod;
import xlorenz.tutorialmod.block.ModBlocks;
import xlorenz.tutorialmod.items.ModItems;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerBlockAndItem(blockStateModelGenerator, ModBlocks.MAGIC_BLOCK);
        registerBlockAndItem(blockStateModelGenerator, ModBlocks.PINK_GARNET_BLOCK);
        registerBlockAndItem(blockStateModelGenerator, ModBlocks.RAW_PINK_GARNET_BLOCK);
        registerBlockAndItem(blockStateModelGenerator, ModBlocks.PINK_GARNET_DEEPSLATE_ORE);
        registerBlockAndItem(blockStateModelGenerator, ModBlocks.PINK_GARNET_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ADAMANTITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOST_STAFF, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAULIFLOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.STAR_LIGHT_ASHES, Models.GENERATED);
    }

    private void registerBlockAndItem(BlockStateModelGenerator gen, Block block) {

        gen.registerSimpleCubeAll(block);
        gen.registerItemModel(block.asItem(), Identifier.of(TutorialMod.MOD_ID, "block/" + Registries.BLOCK.getId(block).getPath()));

    }
}