package dev.hellmod.stage.modifier;

import com.google.gson.JsonArray;
import net.minecraft.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LootMemory {

    private static final Map<UUID, JsonArray> LOOT = new HashMap<>();

    public static void set(LivingEntity entity, JsonArray data) {
        LOOT.put(entity.getUuid(), data);
    }

    public static JsonArray get(LivingEntity entity) {
        return LOOT.get(entity.getUuid());
    }

    public static void remove(LivingEntity entity) {
        LOOT.remove(entity.getUuid());
    }
}