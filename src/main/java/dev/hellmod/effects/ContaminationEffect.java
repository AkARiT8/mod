package dev.hellmod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;

public class ContaminationEffect extends StatusEffect {

    public ContaminationEffect() {
        super(
                StatusEffectCategory.HARMFUL,
                0x4A6B3A
        );
    }

    @Override
    public boolean applyUpdateEffect(
            LivingEntity entity,
            int amplifier
    ) {

        int level = amplifier + 1;

        if (level == 2) {

            entity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.SLOWNESS,
                            20 * 10,
                            0,
                            false,
                            true,
                            true
                    )
            );
        } else if (level == 3) {
            entity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.SLOWNESS,
                            20 * 10,
                            0,
                            false,
                            true,
                            true
                    )
            );
            entity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.WEAKNESS,
                            20 * 10,
                            0,
                            false,
                            true,
                            true
                    )
            );
        }else if (level == 4){
            entity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.SLOWNESS,
                            20 * 10,
                            1,
                            false,
                            true,
                            true
                    )
            );
            entity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.WEAKNESS,
                            20 * 10,
                            0,
                            false,
                            true,
                            true
                    )
            );
            entity.addStatusEffect(
                    new StatusEffectInstance(
                            Registries.STATUS_EFFECT.getEntry(
                                    ModEffects.FRAGILITY
                            ),
                            20 * 10,
                            0,
                            false,
                            true,
                            true
                    )
            );
        } else if (level == 5) {
            entity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.SLOWNESS,
                            20 * 10,
                            1,
                            false,
                            true,
                            true
                    )
            );
            entity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.WEAKNESS,
                            20 * 10,
                            1,
                            false,
                            true,
                            true
                    )
            );
            entity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.WITHER,
                            20 * 10,
                            0,
                            false,
                            true,
                            true
                    )
            );
            entity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.BLINDNESS,
                            20 * 10,
                            0,
                            false,
                            true,
                            true
                    )
            );
            entity.addStatusEffect(
                    new StatusEffectInstance(
                            Registries.STATUS_EFFECT.getEntry(
                                    ModEffects.FRAGILITY
                            ),
                            20 * 10,
                            1,
                            false,
                            true,
                            true
                    )
            );
        }else if (level == 6) {
            entity.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.INSTANT_DAMAGE,
                            20 * 10,
                            50,
                            false,
                            true,
                            true
                    )
            );
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(
            int duration,
            int amplifier
    ) {

        return duration % (20 * 5) == 0;
    }
}