package dev.hellmod.stage.modifier;

import com.google.gson.JsonObject;
import dev.hellmod.util.VariantHolder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;

public class StageModifierApplier {

    public static void apply(LivingEntity entity, JsonObject entityData) {

        if (entity instanceof CreeperEntity creeper) {

            if (entityData.has("explosion_power")) {
                float power = entityData.get("explosion_power").getAsFloat();

                ((VariantHolder) creeper).hellmod$setExplosionPower(power);

                System.out.println("SET POWER: " + power);
            }
        }

        for (var entry : entityData.entrySet()) {

            String key = entry.getKey();

            var modifier = EntityModifierRegistry.get(key);

            if (modifier != null) {

                JsonObject wrapper = new JsonObject();
                wrapper.add(key, entry.getValue());

                modifier.apply(entity, wrapper);
            }
        }
    }
}