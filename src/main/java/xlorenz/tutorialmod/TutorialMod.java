package xlorenz.tutorialmod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xlorenz.tutorialmod.block.ModBlocks;
import xlorenz.tutorialmod.items.ModItems;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorial_mod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlocks.initialize();
	}
}