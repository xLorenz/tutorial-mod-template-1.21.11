package xlorenz.tutorialmod.util;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;
import xlorenz.tutorialmod.items.custom.HammerItem;

import java.util.HashSet;
import java.util.Set;

public class HammerUsageEvent implements PlayerBlockBreakEvents.Before {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity playerEntity, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity) {

        ItemStack mainHandItem = playerEntity.getMainHandStack();

        if(mainHandItem.getItem() instanceof HammerItem hammer && playerEntity instanceof ServerPlayerEntity serverPlayerEntity) {
            if(HARVESTED_BLOCKS.contains(blockPos)) {
                return true;
            }
            if(hammer.isCorrectForDrops(mainHandItem, world.getBlockState(blockPos))) {
                for(BlockPos position : HammerItem.getBlockPosToBeDestroyed(1,blockPos,serverPlayerEntity)) {
                    if(blockPos == position || !hammer.isCorrectForDrops(mainHandItem,world.getBlockState(position))) {
                        continue;
                    }

                    HARVESTED_BLOCKS.add(position);
                    serverPlayerEntity.interactionManager.tryBreakBlock(position); // "recursive"
                    HARVESTED_BLOCKS.remove(position);
                }
            } else {
                HARVESTED_BLOCKS.add(blockPos);
                serverPlayerEntity.interactionManager.tryBreakBlock(blockPos);
                HARVESTED_BLOCKS.remove(blockPos);
            }
        }

        return true;
    }
}
