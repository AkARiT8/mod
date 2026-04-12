package dev.hellmod.stage.recipe;

import com.google.gson.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.resource.ResourceManager;

import java.util.*;

public class StageRecipeManager {

    private static final Map<Integer, StageRecipe> RECIPES = new HashMap<>();

    public static void load(ResourceManager manager) {
        RECIPES.clear();

        var resources = manager.findResources("stages", path -> path.getPath().endsWith(".json"));

        for (var entry : resources.entrySet()) {
            try {
                Identifier id = entry.getKey();

                String name = id.getPath().substring(id.getPath().lastIndexOf("/") + 1);
                int stage = Integer.parseInt(name.replace("stage_", "").replace(".json", ""));

                JsonObject json = JsonParser.parseReader(entry.getValue().getReader()).getAsJsonObject();

                List<Item> inputs = new ArrayList<>();

                JsonArray array = json.getAsJsonArray("inputs");

                for (JsonElement element : array) {
                    JsonObject obj = element.getAsJsonObject();

                    String itemId = obj.get("item").getAsString();
                    Item item = Registries.ITEM.get(new Identifier(itemId));

                    inputs.add(item);
                }

                RECIPES.put(stage, new StageRecipe(inputs));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static StageRecipe get(int stage) {
        return RECIPES.get(stage);
    }
}