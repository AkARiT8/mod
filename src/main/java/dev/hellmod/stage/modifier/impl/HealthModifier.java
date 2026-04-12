package dev.hellmod.stage.modifier.impl;

import com.google.gson.JsonObject;
import dev.hellmod.stage.modifier.EntityModifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;

public class HealthModifier implements EntityModifier {

    @Override
    public void apply(LivingEntity entity, JsonObject data) {

        if (!data.has("health")) return;

        JsonObject health = data.getAsJsonObject("health");

        if (!health.has("max")) return;

        double maxHealth = health.get("max").getAsDouble();

        var attr = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

        if (attr == null) return;

        attr.setBaseValue(maxHealth);

        // 🔥 MUY IMPORTANTE: actualizar vida actual
        entity.setHealth((float) maxHealth);
    }
}