package dev.hellmod.stage.modifier.impl;

import com.google.gson.JsonObject;
import dev.hellmod.stage.modifier.StageModifierManager;

public class NetherModifier {

    public static boolean isNetherDisabled(int stage) {

        JsonObject data = StageModifierManager.get(stage);

        if (data == null) return false;

        if (!data.has("disable_nether")) return false;

        return data.get("disable_nether").getAsBoolean();
    }
}