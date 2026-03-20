package xlorenz.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
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

	}
}