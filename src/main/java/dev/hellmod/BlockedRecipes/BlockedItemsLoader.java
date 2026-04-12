package dev.hellmod.BlockedRecipes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class BlockedItemsLoader {

    public static void load(BlockedItemsManager manager) {
        try (InputStreamReader reader = new InputStreamReader(
                BlockedItemsLoader.class.getResourceAsStream("/assets/hellmod/blockedRecipes/blocked_items.json"))) {

            Type listType = new TypeToken<List<BlockedItemData>>() {}.getType();
            List<BlockedItemData> list = new Gson().fromJson(reader, listType);

            for (BlockedItemData data : list) {

                Identifier itemId = new Identifier(data.getItem());
                Item item = Registries.ITEM.get(itemId);

                if (item != null) {
                    manager.addBlocked(item, data.getStage());
                }
            }
        } catch (Exception e) {
            System.err.println("[BlockedItemsLoader] Error cargando JSON:" + e);
        }
    }

}