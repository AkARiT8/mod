package dev.hellmod.BlockedRecipes;

import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class BlockedItemsManager {

    private final Map<Item, Identifier> blockedItems = new HashMap<>();

    public BlockedItemsManager() {}

    public void addBlocked(Item item, Identifier identifier) {
        blockedItems.put(item,identifier );
    }
    public void removeBlocked(Item item) {
        blockedItems.remove(item);
    }
    public boolean isBlocked(ItemStack stack, PlayerEntity player) {
        Identifier advId = blockedItems.get(stack.getItem());
        if (advId == null) return false;

        if (!(player instanceof ServerPlayerEntity serverPlayer)) {
            return false;
        }

        AdvancementEntry advE = serverPlayer.getServer().getAdvancementLoader().get(advId);
        System.out.println(advE);
        if (advE == null) return false;

        if (serverPlayer.getAdvancementTracker().getProgress(advE).isDone()) {
            blockedItems.remove(stack.getItem());
            return false;
        }
        return true;
    }
    public void resetBlockedItems() {
        blockedItems.clear();
        BlockedItemsLoader.load(this); // recarga el JSON
    }
}