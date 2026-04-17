package dev.hellmod.stage.modifier;

import com.google.gson.JsonObject;
import dev.hellmod.util.VariantHolder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;

public class StageModifierApplier {

    public static void apply(LivingEntity entity, JsonObject entityData) {

        if (entity instanceof CreeperEntity creeper) {

            if (entityData.has("explosion_power")) {
                float power = entityData.get("explosion_power").getAsFloat();

                ((VariantHolder) creeper).hellmod$setExplosionPower(power);

            }
        }

        if (entityData.has("follow_range")) {

            double range = entityData.get("follow_range").getAsDouble();

            var attr = entity.getAttributeInstance(
                    net.minecraft.entity.attribute.EntityAttributes.GENERIC_FOLLOW_RANGE
            );

            if (attr != null) {
                attr.setBaseValue(range);
            }

        }

        if (entityData.has("mod_spawn") && entityData.get("mod_spawn").getAsBoolean()) {

            if (entity.getWorld().getRandom().nextFloat() < 0.03f) {

                if (!(entity.getWorld() instanceof ServerWorld world)) return;

                if (!world.getRegistryKey().equals(ServerWorld.OVERWORLD)) return;

                if (world.getRandom().nextBoolean()) {

                    int count = 2 + world.getRandom().nextInt(3);

                    for (int i = 0; i < count; i++) {

                        var phantom = net.minecraft.entity.EntityType.PHANTOM.create(world);

                        if (phantom != null) {
                            phantom.refreshPositionAndAngles(
                                    entity.getX() + world.getRandom().nextInt(6) - 3,
                                    entity.getY() + 15 + world.getRandom().nextInt(10),
                                    entity.getZ() + world.getRandom().nextInt(6) - 3,
                                    0,
                                    0
                            );

                            world.spawnEntity(phantom);
                        }
                    }

                } else {

                    var ghast = net.minecraft.entity.EntityType.GHAST.create(world);

                    if (ghast != null) {
                        ghast.refreshPositionAndAngles(
                                entity.getX(),
                                entity.getY() + 20,
                                entity.getZ(),
                                0,
                                0
                        );

                        world.spawnEntity(ghast);
                    }
                }
            }
        }

        for (var entry : entityData.entrySet()) {

            String key = entry.getKey();

            var modifier = EntityModifierRegistry.get(key);

            if (modifier != null) {

                JsonObject wrapper = new JsonObject();
                wrapper.add(key, entry.getValue());

                modifier.apply(entity, wrapper);
            }
        }
    }
}