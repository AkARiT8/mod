package dev.hellmod.mixin;

import dev.hellmod.effects.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class StickyJumpMixin {

    @Inject(method = "jump", at = @At("TAIL"))
    private void hellmod$reduceJump(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;

        if (entity.hasStatusEffect(
                Registries.STATUS_EFFECT.getEntry(ModEffects.STICKY)
        )) {

            Vec3d vel = entity.getVelocity();

            entity.setVelocity(
                    vel.x * 0.25,
                    vel.y * 0.4,
                    vel.z * 0.25
            );
        }
    }
}
