package dev.hellmod.stage.modifier.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.hellmod.stage.modifier.EntityModifier;
import dev.hellmod.stage.modifier.LootMemory;
import net.minecraft.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class LootModifier implements EntityModifier {

    public static final String KEY = "hellmod:extra_loot";

    @Override
    public void apply(LivingEntity entity, JsonObject data) {

        if (!data.has("loot")) return;

        JsonObject loot = data.getAsJsonObject("loot");

        if (!loot.has("extra_drops")) return;

        JsonArray drops = loot.getAsJsonArray("extra_drops");

        LootMemory.set(entity, drops);
    }
}