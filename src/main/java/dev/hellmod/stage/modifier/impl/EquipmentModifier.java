package dev.hellmod.stage.modifier.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.hellmod.stage.modifier.EntityModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;

public class EquipmentModifier implements EntityModifier {

    @Override
    public void apply(LivingEntity entity, JsonObject data) {

        if (!(entity instanceof MobEntity mob)) return;
        if (!data.has("equipment")) return;

        JsonObject equipment = data.getAsJsonObject("equipment");

        for (Map.Entry<String, JsonElement> entry : equipment.entrySet()) {

            String slotName = entry.getKey();
            JsonObject itemData = entry.getValue().getAsJsonObject();

            EquipmentSlot slot = getSlot(slotName);

            if (slot == null) {
                continue;
            }
            String itemId = itemData.get("item").getAsString();
            var item = Registries.ITEM.get(new Identifier(itemId));

            if (item == null) {
                continue;
            }

            ItemStack stack = new ItemStack(item);

            if (itemData.has("enchantments")) {

                var enchants = itemData.getAsJsonArray("enchantments");

                for (JsonElement e : enchants) {
                    JsonObject ench = e.getAsJsonObject();

                    Identifier enchId = new Identifier(ench.get("id").getAsString());
                    int level = ench.get("level").getAsInt();

                    var entryEnch = Registries.ENCHANTMENT.getEntry(enchId);

                    if (entryEnch.isEmpty()) {
                        continue;
                    }

                    stack.addEnchantment(entryEnch.get().value(), level);
                }
            }

            mob.equipStack(slot, stack);

            mob.setEquipmentDropChance(slot, 0.0f);
        }
    }

    private EquipmentSlot getSlot(String name) {
        return switch (name.toLowerCase()) {
            case "mainhand" -> EquipmentSlot.MAINHAND;
            case "offhand" -> EquipmentSlot.OFFHAND;
            case "head" -> EquipmentSlot.HEAD;
            case "chest" -> EquipmentSlot.CHEST;
            case "legs" -> EquipmentSlot.LEGS;
            case "feet" -> EquipmentSlot.FEET;
            default -> null;
        };
    }
}