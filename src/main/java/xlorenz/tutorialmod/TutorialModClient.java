package xlorenz.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import xlorenz.tutorialmod.block.ModBlocks;
import xlorenz.tutorialmod.items.ModItems;

public class TutorialModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
            if (stack.isOf(ModItems.STAR_LIGHT_ASHES)) {
                if (hasShiftDown()) {
                    lines.add(Text.translatable(ModItems.STAR_LIGHT_ASHES.getTranslationKey() + ".tooltip.shift"));
                } else {
                    lines.add(Text.translatable(ModItems.STAR_LIGHT_ASHES.getTranslationKey() + ".tooltip"));
                }
                return;
            }
            if (stack.isOf(ModBlocks.PINK_GARNET_BLOCK.asItem())) {
                if (hasShiftDown()) {
                    lines.add(Text.translatable(ModBlocks.PINK_GARNET_BLOCK.getTranslationKey() + ".tooltip.shift"));
                } else {
                    //lines.add(Text.translatable(ModBlocks.PINK_GARNET_BLOCK.getTranslationKey() + ".tooltip"));
                }
                return;
            }
        });
    }
    private static boolean hasShiftDown() {
        return org.lwjgl.glfw.GLFW.glfwGetKey(
                MinecraftClient.getInstance().getWindow().getHandle(),
                org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT
        ) == 1
                || org.lwjgl.glfw.GLFW.glfwGetKey(
                MinecraftClient.getInstance().getWindow().getHandle(),
                org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_SHIFT
        ) == 1;
    }

}