package dev.hellmod.mixin;

import dev.hellmod.items.ModItems;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowAttackGoal.class)
public abstract class BowAttackGoalMixin<T extends HostileEntity & RangedAttackMob> {

    @Shadow
    private T actor;

    @Inject(
            method = "isHoldingBow",
            at = @At("HEAD"),
            cancellable = true
    )
    private void hellmod$isHoldingCustomBow(
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (actor.getMainHandStack().isOf(ModItems.TRUE_AMETHYST_BOW)
                || actor.getOffHandStack().isOf(ModItems.TRUE_AMETHYST_BOW)) {

            cir.setReturnValue(true);
        }
    }
}