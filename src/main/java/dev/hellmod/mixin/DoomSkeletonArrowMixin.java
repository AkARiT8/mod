package dev.hellmod.mixin;

import dev.hellmod.effects.ModEffects;
import dev.hellmod.entity.DoomSkeletonEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
public class DoomSkeletonArrowMixin {

    @Inject(method = "onHit", at = @At("TAIL"))
    private void hellmod$contamination(LivingEntity target, CallbackInfo ci) {
        ArrowEntity arrow = (ArrowEntity) (Object) this;

        if (arrow.getOwner() instanceof DoomSkeletonEntity) {
            var contamination = Registries.STATUS_EFFECT
                    .getEntry(ModEffects.CONTAMINATION);

            target.addStatusEffect(
                    new StatusEffectInstance(
                            contamination,
                            20 * 45,
                            0
                    )
            );
        }
    }
}