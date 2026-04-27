package xlorenz.tutorialmod.items;

import jdk.jshell.Snippet;
import net.minecraft.component.type.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

import java.util.List;

public class ModFoodComponents {
    public static final FoodComponent CAULIFLOWER = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .build();

    public static final FoodComponent HONEY_BERRIES = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.15f)
            .build();

    public static final ConsumableComponent CAULIFLOWER_EFFECTS = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(List.of(
                    new StatusEffectInstance(StatusEffects.DARKNESS, 20, 1),
                    new StatusEffectInstance(StatusEffects.ABSORPTION, 1200, 100)
                    ),
                    0.5f
                )
            )
            .build();
}
