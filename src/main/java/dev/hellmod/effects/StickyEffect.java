package dev.hellmod.effects;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class StickyEffect extends StatusEffect {

    public StickyEffect() {
        super(StatusEffectCategory.HARMFUL, 0x4CAF50);

        addAttributeModifier(
                EntityAttributes.GENERIC_MOVEMENT_SPEED,
                "d3b6e5c0-7a4d-4c3d-bf4d-123456789abc",
                -0.8D,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
}