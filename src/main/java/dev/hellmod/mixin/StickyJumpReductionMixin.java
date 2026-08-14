package dev.hellmod.mixin;

import dev.hellmod.effects.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class StickyJumpReductionMixin {

    @Inject(method = "jump", at = @At("TAIL"))
    private void stickyReduceDurationOnJump(CallbackInfo ci) {

        LivingEntity entity = (LivingEntity)(Object)this;

        if (entity.getWorld().isClient()) {
            return;
        }

        var stickyEntry = Registries.STATUS_EFFECT
                .getEntry(ModEffects.STICKY);

        StatusEffectInstance effect =
                entity.getStatusEffect(stickyEntry);

        if (effect == null) {
            return;
        }

        int newDuration = effect.getDuration() - 40;

        if (newDuration <= 0) {
            entity.removeStatusEffect(stickyEntry);
            return;
        }

        entity.addStatusEffect(
                new StatusEffectInstance(
                        stickyEntry,
                        newDuration,
                        effect.getAmplifier(),
                        effect.isAmbient(),
                        effect.shouldShowParticles(),
                        effect.shouldShowIcon()
                )
        );
    }
}