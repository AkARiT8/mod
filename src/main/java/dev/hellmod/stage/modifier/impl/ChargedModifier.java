package dev.hellmod.stage.modifier.impl;

import com.google.gson.JsonObject;
import dev.hellmod.stage.modifier.EntityModifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;

public class ChargedModifier implements EntityModifier {

    @Override
    public void apply(LivingEntity entity, JsonObject data) {

        if (!(entity instanceof CreeperEntity creeper)) return;

        if (data.get("charged").getAsBoolean()) {
            creeper.onStruckByLightning((ServerWorld) creeper.getWorld(), null);
        }
    }
}