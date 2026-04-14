package dev.hellmod.stage.modifier;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class LootDropHandler {

    public static void register() {

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
        });

        ServerEntityEvents.ENTITY_UNLOAD.register((entity, world) -> {
            if (entity instanceof LivingEntity living) {
                LootMemory.remove(living);
            }
        });
    }

    public static void onDeath(LivingEntity entity, ServerWorld world) {

        JsonArray drops = LootMemory.get(entity);
        if (drops == null) return;

        int lootingLevel = 0;
        if (entity.getAttacker() instanceof LivingEntity attacker) {
            lootingLevel = EnchantmentHelper.getLooting(attacker);
        }

        for (var element : drops) {

            JsonObject obj = element.getAsJsonObject();

            Identifier id = new Identifier(obj.get("item").getAsString());
            float chance = obj.get("chance").getAsFloat();

            float finalChance = chance;
            int extraCount = 0;

            if (chance >= 0.3f) {
                extraCount = world.random.nextInt(lootingLevel + 1);
            } else {
                float bonus = 0.1f * lootingLevel;
                finalChance = Math.min(chance + bonus, 1.0f);
            }

            if (world.random.nextFloat() <= finalChance) {

                int count = 1;

                if (obj.has("count")) {

                    if (obj.get("count").isJsonPrimitive()) {

                        count = obj.get("count").getAsInt();

                    } else {

                        JsonObject countObj = obj.getAsJsonObject("count");

                        int min = countObj.get("min").getAsInt();
                        int max = countObj.get("max").getAsInt();

                        count = min + world.random.nextInt(max - min + 1);
                    }
                }

                count += extraCount;

                ItemStack stack = new ItemStack(
                        Registries.ITEM.get(id),
                        count
                );

                entity.dropStack(stack);
            }
        }

        LootMemory.remove(entity);
    }
}