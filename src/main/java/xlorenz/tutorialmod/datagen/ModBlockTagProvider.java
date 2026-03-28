package xlorenz.tutorialmod.datagen;

import com.jcraft.jorbis.Block;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import xlorenz.tutorialmod.block.ModBlocks;
import xlorenz.tutorialmod.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(Registries.BLOCK.getId(ModBlocks.MAGIC_BLOCK))
                .add(Registries.BLOCK.getId(ModBlocks.RAW_PINK_GARNET_BLOCK))
                .add(Registries.BLOCK.getId(ModBlocks.PINK_GARNET_BLOCK))
                .add(Registries.BLOCK.getId(ModBlocks.PINK_GARNET_ORE))
                .add(Registries.BLOCK.getId(ModBlocks.PINK_GARNET_DEEPSLATE_ORE));

        getTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(Registries.BLOCK.getId(ModBlocks.RAW_PINK_GARNET_BLOCK))
                .add(Registries.BLOCK.getId(ModBlocks.PINK_GARNET_BLOCK))
                .add(Registries.BLOCK.getId(ModBlocks.PINK_GARNET_ORE))
                .add(Registries.BLOCK.getId(ModBlocks.PINK_GARNET_DEEPSLATE_ORE));

        getTagBuilder(BlockTags.FENCES).add(Registries.BLOCK.getId(ModBlocks.PINK_GARNET_FENCE));
        getTagBuilder(BlockTags.FENCE_GATES).add(Registries.BLOCK.getId(ModBlocks.PINK_GARNET_FENCE_GATE));

        getTagBuilder(BlockTags.WALLS).add(Registries.BLOCK.getId(ModBlocks.PINK_GARNET_WALL));

        getTagBuilder(ModTags.Blocks.NEED_PINK_GARNET_TOOL)
                .add(Registries.BLOCK.getId(ModBlocks.MAGIC_BLOCK))
                .addTag(Identifier.of("minecraft:needs_iron_tool"));
    }
}
