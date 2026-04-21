package dev.hellmod.stage.modifier.impl;

import com.google.gson.JsonObject;
import dev.hellmod.stage.modifier.StageModifierManager;
import dev.hellmod.util.SpawnController;

public class PassiveSpawnModifier {

    public static boolean shouldDisable() {

        int stage = SpawnController.CURRENT_STAGE;

        JsonObject json = StageModifierManager.get(stage);

        if (json == null) return false;

        if (json.has("disable_passive_spawns")) {
            return json.get("disable_passive_spawns").getAsBoolean();
        }

        return false;
    }
}