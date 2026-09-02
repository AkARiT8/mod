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
public class ContaminationMixin {

    @Inject(
            method = "tickStatusEffects",
            at = @At("HEAD")
    )
    private void hellmod$updateContamination(CallbackInfo ci) {

        LivingEntity entity = (LivingEntity) (Object) this;

        StatusEffectInstance contamination =
                entity.getStatusEffect(
                        Registries.STATUS_EFFECT.getEntry(
                                ModEffects.CONTAMINATION
                        )
                );

        if (contamination == null) {
            return;
        }
        if (contamination.getDuration() == 1) {

            int currentLevel = contamination.getAmplifier();

            entity.addStatusEffect(
                    new StatusEffectInstance(
                            Registries.STATUS_EFFECT.getEntry(
                                    ModEffects.CONTAMINATION
                            ),
                            getDurationForLevel(currentLevel + 1),
                            currentLevel + 1,
                            false,
                            true,
                            true
                    )
            );
        }
    }

    private static int getDurationForLevel(int level) {
        return 20 * 20;
    }
}