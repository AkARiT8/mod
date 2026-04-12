package dev.hellmod.stage.modifier;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class StageModifierManager {

    private static final Map<Integer, JsonObject> STAGES = new HashMap<>();

    public static void load(ResourceManager manager) {
        STAGES.clear();

        var resources = manager.findResources("stages", path -> path.getPath().endsWith(".json"));

        for (var entry : resources.entrySet()) {
            try {
                Identifier id = entry.getKey();

                String name = id.getPath().substring(id.getPath().lastIndexOf("/") + 1);
                int stage = Integer.parseInt(name.replace("stage_", "").replace(".json", ""));

                BufferedReader reader = entry.getValue().getReader();

                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

                STAGES.put(stage, json);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static JsonObject get(int stage) {
        return STAGES.get(stage);
    }
}