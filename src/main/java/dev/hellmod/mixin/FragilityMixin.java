package dev.hellmod.mixin;

import dev.hellmod.effects.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class FragilityMixin {

    @ModifyVariable(
            method = "damage",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private float hellmod$applyFragility(float amount) {

        LivingEntity entity = (LivingEntity) (Object) this;

        StatusEffectInstance fragility =
                entity.getStatusEffect(
                        Registries.STATUS_EFFECT.getEntry(
                                ModEffects.FRAGILITY
                        )
                );

        if (fragility == null) {
            return amount;
        }

        int level = fragility.getAmplifier() + 1;

        float multiplier = 1.0F + (0.5F * level);

        return amount * multiplier;
    }
}