package xlorenz.tutorialmod.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import xlorenz.tutorialmod.TutorialMod;
import xlorenz.tutorialmod.effect.ModEffects;

public class ModPotions {

    public static final RegistryEntry<Potion> SLIMY_POTION = registerPotion("slimy",
            new Potion("slimy", new StatusEffectInstance(ModEffects.SLIMY, 1200, 0)));

    private static RegistryEntry<Potion> registerPotion (String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(TutorialMod.MOD_ID, name), potion);
    }
    public static void initialize() {
        TutorialMod.LOGGER.info("Initializing mod potions for " + TutorialMod.MOD_ID);
    }
}
