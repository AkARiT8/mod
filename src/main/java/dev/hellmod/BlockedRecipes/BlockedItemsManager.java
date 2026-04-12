package dev.hellmod.BlockedRecipes;

import dev.hellmod.blocks.custom.StageData;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class BlockedItemsManager {

    private final Map<Item, Integer> blockedItems = new HashMap<>();

    public BlockedItemsManager() {}

    public void addBlocked(Item item, int stage) {
        blockedItems.put(item, stage);
    }
    public void removeBlocked(Item item) {
        blockedItems.remove(item);
    }
    public boolean isBlocked(ItemStack stack, PlayerEntity player) {

        Integer requiredStage = blockedItems.get(stack.getItem());
        if (requiredStage == null) return false;

        if (!(player.getWorld() instanceof ServerWorld world)) return false;

        int currentStage = StageData.get(world).getStage();

        return currentStage < requiredStage;
    }
    public void resetBlockedItems() {
        blockedItems.clear();
        BlockedItemsLoader.load(this); // recarga el JSON
    }
}