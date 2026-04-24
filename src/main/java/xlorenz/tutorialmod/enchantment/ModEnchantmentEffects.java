package xlorenz.tutorialmod.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xlorenz.tutorialmod.TutorialMod;
import xlorenz.tutorialmod.enchantment.custom.LightningStrikerEnchantmentEffect;

public class ModEnchantmentEffects {

    public static final MapCodec<? extends  EnchantmentEntityEffect> LIGHTNING_STRIKER = registerEntityEffect("lightning_striker", LightningStrikerEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(TutorialMod.MOD_ID, name), codec);
    }


    public static void initialize() {
        TutorialMod.LOGGER.info("wadbnawufbcuiabviufwa");
    }
}
