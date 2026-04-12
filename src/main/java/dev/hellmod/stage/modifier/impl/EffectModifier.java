package dev.hellmod.stage.modifier.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.hellmod.stage.modifier.EntityModifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class EffectModifier implements EntityModifier {

    @Override
    public void apply(LivingEntity entity, JsonObject data) {

        if (!data.has("effects")) return;

        JsonArray effects = data.getAsJsonArray("effects");

        for (JsonElement e : effects) {
            JsonObject obj = e.getAsJsonObject();

            Identifier id = new Identifier(obj.get("id").getAsString());

            var effect = Registries.STATUS_EFFECT
                    .getEntry(id)
                    .orElseThrow();

            int amp = obj.get("amplifier").getAsInt();

            entity.addStatusEffect(
                    new StatusEffectInstance(effect, 999999, amp)
            );
        }
    }
}