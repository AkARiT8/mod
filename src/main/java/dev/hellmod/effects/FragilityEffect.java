package dev.hellmod.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FragilityEffect extends StatusEffect {

    public FragilityEffect() {
        super(
                StatusEffectCategory.HARMFUL,
                0xB8B8B8
        );
    }
}