package dev.hellmod.items.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {

    public static final Potion PANIC_POTION = Registry.register(
            Registries.POTION,
            new Identifier("hellmod", "panic_potion"),
            new Potion(
                    new StatusEffectInstance(StatusEffects.LEVITATION, 40, 50),
                     new StatusEffectInstance(StatusEffects.SLOW_FALLING, 1200, 0),
                    new StatusEffectInstance(StatusEffects.REGENERATION, 400, 0)
            )
    );

    public static void registerPotions() {
    }
}