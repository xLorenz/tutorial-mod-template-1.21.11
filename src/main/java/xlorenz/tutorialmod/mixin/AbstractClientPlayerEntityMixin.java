package xlorenz.tutorialmod.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xlorenz.tutorialmod.items.ModItems;

@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerEntityMixin  {

    @Inject(method = "getFovMultiplier(ZF)F",
            at = @At(value = "TAIL"),
            cancellable = true)
    private void tutorial_mod$getFovMultiplierMixin(
            boolean firstPerson,
            float fovEffectScale,
            CallbackInfoReturnable<Float> cir) {

        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)(Object)this;

        ItemStack itemStack = player.getActiveItem();

        if(player.isUsingItem() && itemStack.isOf(ModItems.KAUPEN_BOW)) {

            float progress = Math.min(player.getItemUseTime() / 20.0F, 1.0F);

            float modified = 1.0F - MathHelper.square(progress) * 0.15F;

            float result = MathHelper.lerp(fovEffectScale, 1.0F, modified);

            cir.setReturnValue(result);

        }

    }
}
