package xlorenz.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import xlorenz.tutorialmod.items.ModItems;

public class HoneyBerryBushBlock extends SweetBerryBushBlock {

    public HoneyBerryBushBlock(Settings settings) {
        super(settings);
    }


    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(ModItems.HONEY_BERRIES);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if ((Integer)state.get(AGE) > 1) {

            boolean bl = (Integer)state.get(AGE) == 3;

            if (world instanceof ServerWorld serverWorld) {
                int j = 1 + world.random.nextInt(2);

                dropStack(world, pos, new ItemStack(ModItems.HONEY_BERRIES, j + (bl ? 1 : 0)));

                serverWorld.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + serverWorld.random.nextFloat() * 0.4F);
                BlockState blockState = state.with(AGE, 1);
                serverWorld.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
                serverWorld.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            }

            return ActionResult.SUCCESS;
        } else {
            return super.onUse(state, world, pos, player, hit);
        }
    }
}
