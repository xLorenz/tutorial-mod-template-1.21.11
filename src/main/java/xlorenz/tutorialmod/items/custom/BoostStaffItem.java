package xlorenz.tutorialmod.items.custom;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class BoostStaffItem extends Item {
    public static final float BOOST_AMPLIFIER = 10;

    public BoostStaffItem(Settings settings){super(settings);}

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        if(player == null) {return ActionResult.FAIL;}

            float lookingAngleVertical = player.getPitch();
            if(lookingAngleVertical>20f) {
                BlockPos playerPos = player.getBlockPos();
                BlockPos pointedBlockPos = context.getBlockPos();

                BlockPos difference = playerPos.subtract(pointedBlockPos);

                double length = Math.sqrt(difference.getSquaredDistance(0,0,0));

                if(player.isOnGround()){
                    if (!world.isClient()) {
                        context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                                item -> player.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                        //todo: implement ts

                        world.playSound(null, playerPos, SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST.value(), SoundCategory.PLAYERS);
                    }

                    return ActionResult.SUCCESS;
                }
            }

        return ActionResult.PASS;
    }

}
