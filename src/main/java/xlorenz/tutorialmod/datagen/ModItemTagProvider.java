package xlorenz.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import xlorenz.tutorialmod.items.ModItems;
import xlorenz.tutorialmod.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(Registries.ITEM.getId(ModItems.PINK_GARNET));

        getTagBuilder(ModTags.Items.REPAIRS_PINK_GARNET_TOOLS)
                .add(Registries.ITEM.getId(ModItems.PINK_GARNET));

        getTagBuilder(ItemTags.SWORDS).add(Registries.ITEM.getId(ModItems.PINK_GARNET_SWORD));
        getTagBuilder(ItemTags.PICKAXES).add(Registries.ITEM.getId(ModItems.PINK_GARNET_PICKAXE));
        getTagBuilder(ItemTags.AXES).add(Registries.ITEM.getId(ModItems.PINK_GARNET_AXE));
        getTagBuilder(ItemTags.SHOVELS).add(Registries.ITEM.getId(ModItems.PINK_GARNET_SHOVEL));
        getTagBuilder(ItemTags.HOES).add(Registries.ITEM.getId(ModItems.PINK_GARNET_HOE));
    }
}
