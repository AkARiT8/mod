package dev.hellmod.BlockedRecipes;

import dev.hellmod.stage.manager.StageData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

import java.util.HashMap;
import java.util.Map;

public class BlockedItemsManager {

    private final Map<Item, Integer> blockedItems = new HashMap<>();

    public void addBlocked(Item item, int requiredStage) {
        blockedItems.put(item, requiredStage);
    }

    public void removeBlocked(Item item) {
        blockedItems.remove(item);
    }

    public boolean isBlocked(ItemStack stack, PlayerEntity player) {

        Integer requiredStage = blockedItems.get(stack.getItem());

        if (requiredStage == null) {
            return false;
        }

        if (!(player.getWorld() instanceof ServerWorld world)) {
            return false;
        }

        int currentStage =
                StageData.get(world).getStage();

        return currentStage < requiredStage;
    }

    public int getRequiredStage(ItemStack stack) {
        return blockedItems.getOrDefault(
                stack.getItem(),
                0
        );
    }

    public boolean hasRestriction(ItemStack stack) {
        return blockedItems.containsKey(stack.getItem());
    }
}