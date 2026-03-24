package xlorenz.tutorialmod.datagen;

import com.jcraft.jorbis.Block;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import xlorenz.tutorialmod.block.ModBlocks;

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
    }
}
