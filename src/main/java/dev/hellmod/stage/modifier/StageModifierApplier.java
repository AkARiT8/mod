package dev.hellmod.stage.modifier;

import com.google.gson.JsonObject;
import net.minecraft.entity.LivingEntity;

public class StageModifierApplier {

    public static void apply(LivingEntity entity, JsonObject entityData) {

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