package dev.hellmod.mixin;

import dev.hellmod.entity.AirshipEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(
            method = "handleFallDamage",
            at = @At("HEAD"),
            cancellable = true
    )
    private void airshipFallDamage(
            float fallDistance,
            float damageMultiplier,
            DamageSource damageSource,
            CallbackInfoReturnable<Boolean> cir
    ) {

        PlayerEntity player = (PlayerEntity)(Object)this;

        if (player.getVehicle() instanceof AirshipEntity && player.getVehicle().fallDistance > 15) {

            player.damage(
                    player.getDamageSources().fall(),
                    4.0f
            );

            cir.setReturnValue(true);
        }else {
            cir.setReturnValue(true);
        }
    }
}