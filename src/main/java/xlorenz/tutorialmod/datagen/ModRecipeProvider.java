package xlorenz.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryWrapper;
import xlorenz.tutorialmod.block.ModBlocks;
import xlorenz.tutorialmod.items.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }



    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                List<ItemConvertible> PINK_GARNET_SMELTABLES = List.of(
                        ModItems.RAW_PINK_GARNET,
                        ModBlocks.PINK_GARNET_ORE,
                        ModBlocks.PINK_GARNET_DEEPSLATE_ORE);

                offerSmelting(PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 200, "pink_garnet");
                offerBlasting(PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 100, "pink_garnet");

                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.PINK_GARNET, RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_PINK_GARNET, RecipeCategory.DECORATIONS, ModBlocks.RAW_PINK_GARNET_BLOCK);


                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.MISC, ModBlocks.MAGIC_BLOCK)
                        .pattern("SGS")
                        .pattern("GBG")
                        .pattern("SGS")
                        .input('G', ModItems.PINK_GARNET)
                        .input('S', ModItems.STAR_LIGHT_ASHES)
                        .input('B', ModBlocks.PINK_GARNET_BLOCK)
                        .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                        .criterion(hasItem(ModItems.STAR_LIGHT_ASHES), conditionsFromItem(ModItems.STAR_LIGHT_ASHES))
                        .offerTo(exporter);

                createButtonRecipe(ModBlocks.PINK_GARNET_BUTTON, Ingredient.ofItem(ModBlocks.PINK_GARNET_BLOCK));
                createDoorRecipe(ModBlocks.PINK_GARNET_DOOR, Ingredient.ofItem(ModBlocks.PINK_GARNET_BLOCK));
                createFenceRecipe(ModBlocks.PINK_GARNET_FENCE, Ingredient.ofItem(ModBlocks.PINK_GARNET_BLOCK));
                createFenceGateRecipe(ModBlocks.PINK_GARNET_FENCE_GATE, Ingredient.ofItem(ModBlocks.PINK_GARNET_BLOCK));
                createPressurePlateRecipe(RecipeCategory.REDSTONE, ModBlocks.PINK_GARNET_PRESSURE_PLATE, Ingredient.ofItem(ModBlocks.PINK_GARNET_BLOCK));
                createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PINK_GARNET_SLAB, Ingredient.ofItem(ModBlocks.PINK_GARNET_BLOCK));
                createStairsRecipe(ModBlocks.PINK_GARNET_STAIRS, Ingredient.ofItem(ModBlocks.PINK_GARNET_BLOCK));
                createTrapdoorRecipe(ModBlocks.PINK_GARNET_TRAPDOOR, Ingredient.ofItem(ModBlocks.PINK_GARNET_BLOCK));

                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.COMBAT, ModItems.PINK_GARNET_SWORD)
                        .pattern("G")
                        .pattern("G")
                        .pattern("S")
                        .input('G', ModItems.PINK_GARNET)
                        .input('S', Items.STICK)
                        .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PINK_GARNET_PICKAXE)
                        .pattern("GGG")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('G', ModItems.PINK_GARNET)
                        .input('S', Items.STICK)
                        .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PINK_GARNET_AXE)
                        .pattern(" GG")
                        .pattern(" SG")
                        .pattern(" S ")
                        .input('G', ModItems.PINK_GARNET)
                        .input('S', Items.STICK)
                        .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PINK_GARNET_SHOVEL)
                        .pattern("G")
                        .pattern("S")
                        .pattern("S")
                        .input('G', ModItems.PINK_GARNET)
                        .input('S', Items.STICK)
                        .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.TOOLS, ModItems.PINK_GARNET_HOE)
                        .pattern("GG")
                        .pattern("S ")
                        .pattern("S ")
                        .input('G', ModItems.PINK_GARNET)
                        .input('S', Items.STICK)
                        .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "TutorialModRecipeProvider";
    }
}
