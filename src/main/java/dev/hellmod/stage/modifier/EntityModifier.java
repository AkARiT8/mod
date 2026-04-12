package dev.hellmod.stage.modifier;

import com.google.gson.JsonObject;
import net.minecraft.entity.LivingEntity;

public interface EntityModifier {
    void apply(LivingEntity entity, JsonObject data);
}