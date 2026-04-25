package xlorenz.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.impl.client.rendering.BlockRenderLayerMapImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.text.Text;
import xlorenz.tutorialmod.block.ModBlocks;
import xlorenz.tutorialmod.items.ModItems;

public class TutorialModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        BlockRenderLayerMapImpl.putBlock(ModBlocks.PINK_GARNET_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMapImpl.putBlock(ModBlocks.PINK_GARNET_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMapImpl.putBlock(ModBlocks.CAULIFLOWER_CROP, BlockRenderLayer.CUTOUT);


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