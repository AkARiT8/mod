package dev.hellmod.mixin;

import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnGroup.class)
public class SpawnGroupMixin {

    @Inject(method = "getCapacity", at = @At("RETURN"), cancellable = true)
    private void modifyCapacity(CallbackInfoReturnable<Integer> cir) {

        SpawnGroup self = (SpawnGroup)(Object)this;

        if (self == SpawnGroup.MONSTER && dev.hellmod.util.SpawnController.STAGE_3_ACTIVE) {

            int original = cir.getReturnValue();

            int newCap = original * 2;

            cir.setReturnValue(newCap);
        }
    }
}