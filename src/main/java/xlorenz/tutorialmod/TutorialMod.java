package xlorenz.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xlorenz.tutorialmod.block.ModBlocks;
import xlorenz.tutorialmod.items.ModItemGroups;
import xlorenz.tutorialmod.items.ModItems;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorial_mod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlocks.initialize();
		ModItemGroups.initialize();

		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.STAR_LIGHT_ASHES, 30 * 20);
		});

		ItemTooltipCallback.EVENT.register(((itemStack, tooltipContext, tooltipType, list) -> {
			if(itemStack.isOf(ModItems.PINK_GARNET)){
				list.add(Text.translatable(ModItems.PINK_GARNET.getTranslationKey() + ".tooltip0"));
				list.add(Text.translatable(ModItems.PINK_GARNET.getTranslationKey() + ".tooltip1"));
			}
			if(itemStack.isOf(ModBlocks.MAGIC_BLOCK.asItem())){
				list.add(Text.translatable(ModBlocks.MAGIC_BLOCK.getTranslationKey() + ".tooltip0"));
			}
		}));

	}
}