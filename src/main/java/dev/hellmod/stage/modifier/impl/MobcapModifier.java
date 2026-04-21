package dev.hellmod.stage.modifier.impl;

import com.google.gson.JsonObject;
import dev.hellmod.stage.modifier.StageModifierManager;
import dev.hellmod.util.SpawnController;

public class MobcapModifier {

    public static float getMobcapModifier() {

        int stage = SpawnController.CURRENT_STAGE;

        JsonObject json = StageModifierManager.get(stage);

        if (json == null) return 1.0f;

        if (json.has("mobcap_multiplier")) {
            return json.get("mobcap_multiplier").getAsFloat();
        }

        return 1.0f;
    }
}