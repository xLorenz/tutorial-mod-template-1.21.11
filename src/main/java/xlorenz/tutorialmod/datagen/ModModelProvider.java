package xlorenz.tutorialmod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.SelectItemModel;
import net.minecraft.client.render.item.property.bool.HasComponentProperty;
import net.minecraft.client.render.item.property.select.TrimMaterialProperty;
import net.minecraft.client.render.item.tint.DyeTintSource;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.item.equipment.trim.ArmorTrimMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import xlorenz.tutorialmod.TutorialMod;
import xlorenz.tutorialmod.block.ModBlocks;
import xlorenz.tutorialmod.block.custom.CauliflowerCropBlock;
import xlorenz.tutorialmod.block.custom.PinkGarnetLampBlock;
import xlorenz.tutorialmod.components.ModDataComponentTypes;
import xlorenz.tutorialmod.items.ModArmorMaterials;
import xlorenz.tutorialmod.items.ModItems;
import xlorenz.tutorialmod.trim.ModArmorTrimAssets;
import xlorenz.tutorialmod.trim.ModTrimMaterials;

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
                .accept(VariantsBlockModelDefinitionCreator.of(ModBlocks.PINK_GARNET_LAMP).with(createBooleanModelMap(PinkGarnetLampBlock.CLICKED, weightedVariant2, weightedVariant)));

        blockStateModelGenerator.registerCrop(ModBlocks.CAULIFLOWER_CROP, CauliflowerCropBlock.AGE, 0,1,2,3,4,5,6);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ADAMANTITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PINK_GARNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOST_STAFF, Models.GENERATED);
        itemModelGenerator.register(ModItems.CAULIFLOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.STAR_LIGHT_ASHES, Models.GENERATED);

        itemModelGenerator.register(ModItems.PINK_GARNET_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINK_GARNET_HAMMER, Models.HANDHELD_MACE);

        itemModelGenerator.register(ModItems.PINK_GARNET_HORSE_ARMOR, Models.GENERATED);

        itemModelGenerator.register(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);

        registerArmorTrim(itemModelGenerator,ModArmorTrimAssets.PINK_GARNET,ModTrimMaterials.PINK_GARNET);

        //itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        ItemModel.Unbaked chiselModel = ItemModels.basic(ModelIds.getItemModelId(ModItems.CHISEL));
        ItemModel.Unbaked chiselUsedModel = ItemModels.basic(itemModelGenerator.registerSubModel(ModItems.CHISEL, "_used", Models.HANDHELD));

        itemModelGenerator.upload(ModItems.CHISEL, Models.HANDHELD);
        itemModelGenerator.output.accept(
                ModItems.CHISEL,
                ItemModels.condition(
                        new HasComponentProperty(ModDataComponentTypes.COORDINATES, false),
                        chiselUsedModel,
                        chiselModel
                    )
                );
        itemModelGenerator.upload(ModItems.KAUPEN_BOW, Models.HANDHELD);
        itemModelGenerator.registerBow(ModItems.KAUPEN_BOW);

        itemModelGenerator.register(ModItems.BAR_BRAWL_MUSIC_DISC, Models.GENERATED);
    }

    private void registerBlockAndItem(BlockStateModelGenerator gen, Block block) {

        gen.registerSimpleCubeAll(block);
        gen.registerItemModel(block.asItem(), Identifier.of(TutorialMod.MOD_ID, "block/" + Registries.BLOCK.getId(block).getPath()));

    }
    private void registerItem(BlockStateModelGenerator gen, Block block) {

        gen.registerItemModel(block.asItem(), Identifier.of(TutorialMod.MOD_ID, "block/" + Registries.BLOCK.getId(block).getPath()));

    }

    public void registerArmorTrim(ItemModelGenerator gen, ArmorTrimAssets trimAsset, RegistryKey<ArmorTrimMaterial> trimMaterial) {

        List<ItemModelGenerator.TrimMaterial> materials = new ArrayList<>();

        materials.addAll(ItemModelGenerator.TRIM_MATERIALS);
        materials.add(new ItemModelGenerator.TrimMaterial(trimAsset, trimMaterial));


        registerArmor(gen,materials, Items.TURTLE_HELMET, EquipmentAssetKeys.TURTLE_SCUTE, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.LEATHER_HELMET, EquipmentAssetKeys.LEATHER, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, true);
        registerArmor(gen,materials, Items.LEATHER_CHESTPLATE, EquipmentAssetKeys.LEATHER, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, true);
        registerArmor(gen,materials, Items.LEATHER_LEGGINGS, EquipmentAssetKeys.LEATHER, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, true);
        registerArmor(gen,materials, Items.LEATHER_BOOTS, EquipmentAssetKeys.LEATHER, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, true);
        registerArmor(gen,materials, Items.COPPER_HELMET, EquipmentAssetKeys.COPPER, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.COPPER_CHESTPLATE, EquipmentAssetKeys.COPPER, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.COPPER_LEGGINGS, EquipmentAssetKeys.COPPER, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.COPPER_BOOTS, EquipmentAssetKeys.COPPER, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.CHAINMAIL_HELMET, EquipmentAssetKeys.CHAINMAIL, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.CHAINMAIL_CHESTPLATE, EquipmentAssetKeys.CHAINMAIL, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.CHAINMAIL_LEGGINGS, EquipmentAssetKeys.CHAINMAIL, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.CHAINMAIL_BOOTS, EquipmentAssetKeys.CHAINMAIL, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.IRON_HELMET, EquipmentAssetKeys.IRON, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.IRON_CHESTPLATE, EquipmentAssetKeys.IRON, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.IRON_LEGGINGS, EquipmentAssetKeys.IRON, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.IRON_BOOTS, EquipmentAssetKeys.IRON, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.DIAMOND_HELMET, EquipmentAssetKeys.DIAMOND, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.DIAMOND_CHESTPLATE, EquipmentAssetKeys.DIAMOND, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.DIAMOND_LEGGINGS, EquipmentAssetKeys.DIAMOND, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.DIAMOND_BOOTS, EquipmentAssetKeys.DIAMOND, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.GOLDEN_HELMET, EquipmentAssetKeys.GOLD, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.GOLDEN_CHESTPLATE, EquipmentAssetKeys.GOLD, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.GOLDEN_LEGGINGS, EquipmentAssetKeys.GOLD, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.GOLDEN_BOOTS, EquipmentAssetKeys.GOLD, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.NETHERITE_HELMET, EquipmentAssetKeys.NETHERITE, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.NETHERITE_CHESTPLATE, EquipmentAssetKeys.NETHERITE, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.NETHERITE_LEGGINGS, EquipmentAssetKeys.NETHERITE, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, Items.NETHERITE_BOOTS, EquipmentAssetKeys.NETHERITE, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);


        registerArmor(gen,materials, ModItems.PINK_GARNET_HELMET, ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL_KEY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, ModItems.PINK_GARNET_CHESTPLATE, ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL_KEY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, ModItems.PINK_GARNET_LEGGINGS, ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL_KEY, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        registerArmor(gen,materials, ModItems.PINK_GARNET_BOOTS, ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL_KEY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);



    }

    public SelectItemModel.SwitchCase<RegistryKey<ArmorTrimMaterial>> registerArmor(ItemModelGenerator gen, ArmorTrimAssets trimAsset, RegistryKey<ArmorTrimMaterial> trimMaterial, Item item, RegistryKey<EquipmentAsset> equipmentKey, Identifier trimIdPrefix, boolean dyeable) {
        Identifier identifier = ModelIds.getItemModelId(item);
        Identifier identifier2 = TextureMap.getId(item);
        Identifier identifier3 = TextureMap.getSubId(item, "_overlay");

        ItemModelGenerator.TrimMaterial material = new ItemModelGenerator.TrimMaterial(trimAsset, trimMaterial);

        Identifier identifier4 = identifier.withSuffixedPath("_" + material.assets().base().suffix() + "_trim");
        Identifier identifier5 = trimIdPrefix.withSuffixedPath("_" + material.assets().getAssetId(equipmentKey).suffix());
        ItemModel.Unbaked unbaked;
        if (dyeable) {
            gen.uploadArmor(identifier4, identifier2, identifier3, identifier5);
            unbaked = ItemModels.tinted(identifier4, new DyeTintSource(-6265536));
        } else {
            gen.uploadArmor(identifier4, identifier2, identifier5);
            unbaked = ItemModels.basic(identifier4);
        }

        return ItemModels.switchCase(trimMaterial, unbaked);
    }

    public void registerArmor(
            ItemModelGenerator gen,
            List<ItemModelGenerator.TrimMaterial> trimMaterials,
            Item item,
            RegistryKey<EquipmentAsset> equipmentKey,
            Identifier trimIdPrefix,
            boolean dyeable
    ) {
        Identifier baseId = ModelIds.getItemModelId(item);
        Identifier layer0 = TextureMap.getId(item);
        Identifier overlay = TextureMap.getSubId(item, "_overlay");

        List<SelectItemModel.SwitchCase<RegistryKey<ArmorTrimMaterial>>> cases = new ArrayList<>();

        for (ItemModelGenerator.TrimMaterial material : trimMaterials) {
            Identifier modelId = baseId.withSuffixedPath(
                    "_" + material.assets().base().suffix() + "_trim"
            );
            Identifier identifier5 = trimIdPrefix.withSuffixedPath("_" + material.assets().getAssetId(equipmentKey).suffix());

            ItemModel.Unbaked unbaked;

            if (dyeable) {
                gen.uploadArmor(modelId, layer0, overlay, identifier5);
                unbaked = ItemModels.tinted(modelId, new DyeTintSource(-6265536));
            } else {
                gen.uploadArmor(modelId, layer0, identifier5);
                unbaked = ItemModels.basic(modelId);
            }

            cases.add(ItemModels.switchCase(material.materialKey(), unbaked));
        }

        // Base (fallback) model
        ItemModel.Unbaked fallback;

        if (dyeable) {
            Models.GENERATED_TWO_LAYERS.upload(baseId,
                    TextureMap.layered(layer0, overlay),
                    gen.modelCollector);
            fallback = ItemModels.tinted(baseId, new DyeTintSource(-6265536));
        } else {
            Models.GENERATED.upload(baseId,
                    TextureMap.layer0(layer0),
                    gen.modelCollector);
            fallback = ItemModels.basic(baseId);
        }

        // 🔥 THIS generates the items/<armor>.json file
        gen.output.accept(
                item,
                ItemModels.select(new TrimMaterialProperty(), fallback, cases)
        );
        // thanks chatgpt
    }

    private void registerItems(BlockStateModelGenerator gen, List<Block> blocks) {
        for(Block block : blocks) {
            registerItem(gen, block);
        }
    }
}