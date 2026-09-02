package dev.hellmod.mixin;

import dev.hellmod.items.ModItems;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.entity.projectile.ProjectileUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProjectileUtil.class)
public class ProjectileUtilMixin {

    @Inject(
            method = "getHandPossiblyHolding",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void hellmod$recognizeCustomBow(
            net.minecraft.entity.LivingEntity entity,
            net.minecraft.item.Item item,
            CallbackInfoReturnable<Hand> cir
    ) {
        if (entity instanceof AbstractSkeletonEntity skeleton
                && item == Items.BOW) {

            ItemStack mainHand = skeleton.getMainHandStack();

            if (mainHand.isOf(ModItems.TRUE_AMETHYST_BOW)) {
                cir.setReturnValue(Hand.MAIN_HAND);
            }
        }
    }
}