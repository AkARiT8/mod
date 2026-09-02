package dev.hellmod.mixin;

import dev.hellmod.effects.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ContaminationEffectMixin {

    @Inject(
            method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void hellmod$preventContaminationStack(
            StatusEffectInstance effect,
            CallbackInfoReturnable<Boolean> cir
    ) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (effect.getEffectType().value() != ModEffects.CONTAMINATION) {
            return;
        }

        if (entity.getStatusEffects().stream().anyMatch(
                existing -> existing.getEffectType().value() == ModEffects.CONTAMINATION
        )) {
            cir.setReturnValue(false);
        }
    }
}