package xlorenz.tutorialmod.items;

import net.minecraft.item.equipment.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import xlorenz.tutorialmod.TutorialMod;
import xlorenz.tutorialmod.datagen.ModItemTagProvider;
import xlorenz.tutorialmod.util.ModTags;

import java.util.EnumMap;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static RegistryKey<EquipmentAsset> PINK_GARNET_ARMOR_MATERIAL_KEY = registerEquipmentAsset("pink_garnet");
    public static ArmorMaterial PINK_GARNET_ARMOR_MATERIAL = new ArmorMaterial(
            1000,
            ArmorMaterials.createDefenseMap(2,4,6,2,4),
            20,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            10f,
            2f,
            ModTags.Items.REPAIRS_PINK_GARNET_TOOLS,
            PINK_GARNET_ARMOR_MATERIAL_KEY
            );



    static RegistryKey<EquipmentAsset> registerEquipmentAsset(String name) {
        return RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(TutorialMod.MOD_ID, name));
    }
}
