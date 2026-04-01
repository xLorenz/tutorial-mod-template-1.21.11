package xlorenz.tutorialmod.trim;

import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import xlorenz.tutorialmod.TutorialMod;

public class ModTrimMaterials {

    public static final RegistryKey<ArmorTrimMaterial> PINK_GARNET = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(TutorialMod.MOD_ID, "pink_garnet"));

    public static void bootstrap(Registerable<ArmorTrimMaterial> registry) {
        register(registry, PINK_GARNET, Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()), ModArmorTrimAssets.PINK_GARNET);
    }

    private static void register(Registerable<ArmorTrimMaterial> registry, RegistryKey<ArmorTrimMaterial> key, Style style, ArmorTrimAssets assets) {
        Text text = Text.translatable(Util.createTranslationKey("trim_material", key.getValue())).fillStyle(style);
        ArmorTrimMaterial mat = new ArmorTrimMaterial(assets, text);
        registry.register(key, mat);
    }

}
