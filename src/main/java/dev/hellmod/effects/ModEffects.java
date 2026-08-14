package dev.hellmod.effects;

import dev.hellmod.HellMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final StatusEffect STICKY = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier.of(HellMod.MOD_ID, "sticky"),
            new StickyEffect()
    );

    public static void registerEffects() {
        HellMod.LOGGER.info("Registering Effects for " + HellMod.MOD_ID);
    }
}