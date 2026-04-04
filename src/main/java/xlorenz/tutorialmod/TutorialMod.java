package xlorenz.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xlorenz.tutorialmod.block.ModBlocks;
import xlorenz.tutorialmod.components.ModDataComponentTypes;
import xlorenz.tutorialmod.items.ModItemGroups;
import xlorenz.tutorialmod.items.ModItems;
import xlorenz.tutorialmod.sound.ModSounds;
import xlorenz.tutorialmod.util.HammerUsageEvent;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorial_mod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlocks.initialize();
		ModItemGroups.initialize();
		ModDataComponentTypes.initialize();
		ModSounds.initialize();

		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.STAR_LIGHT_ASHES, 30 * 20);
		});

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		ItemTooltipCallback.EVENT.register(((itemStack, tooltipContext, tooltipType, list) -> {
			if(itemStack.isOf(ModItems.PINK_GARNET)){
				list.add(Text.translatable(ModItems.PINK_GARNET.getTranslationKey() + ".tooltip0"));
				list.add(Text.translatable(ModItems.PINK_GARNET.getTranslationKey() + ".tooltip1"));
			}
			if(itemStack.isOf(ModBlocks.MAGIC_BLOCK.asItem())){
				list.add(Text.translatable(ModBlocks.MAGIC_BLOCK.getTranslationKey() + ".tooltip0"));
			}
			if(itemStack.isOf(ModItems.CHISEL)){
				if(itemStack.get(ModDataComponentTypes.COORDINATES) != null) {
					list.add(Text.literal("Last Block Changed: " + itemStack.get(ModDataComponentTypes.COORDINATES)));
				}
			}
		}));

		AttackEntityCallback.EVENT.register(((playerEntity, world, hand, entity,entityHitResult) -> {

			if (entity instanceof SheepEntity sheepEntity && !world.isClient()) {
				if (playerEntity.getMainHandStack().getItem() == ModItems.PINK_GARNET) {
					playerEntity.sendMessage(Text.literal("Gracias, adiós"),false);
					playerEntity.getMainHandStack().decrement(1);
					sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 20*120, 1, false,false));
					return ActionResult.SUCCESS;
				}
			}
			return ActionResult.PASS;
		}));

	}
}