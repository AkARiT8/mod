package dev.hellmod.structures.InfernalManor;

import dev.hellmod.stage.manager.StageData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;

public class InfernalManorEffectHandler {

    public static void register() {

        ServerTickEvents.END_WORLD_TICK.register(world -> {

            for (ServerPlayerEntity player : world.getPlayers()) {
                StageData stageData =
                        StageData.get(world);
                if (stageData.getStage() >= 2) {
                    continue;
                }

                if (!InfernalManorUtil.isInsideInfernalManor(
                        world,
                        player.getBlockPos()
                )) {
                    continue;
                }

                applyEffects(player);
            }
        });
    }

    private static void applyEffects(ServerPlayerEntity player) {

        player.addStatusEffect(
                new StatusEffectInstance(
                        StatusEffects.DARKNESS,
                        40,
                        0,
                        false,
                        false
                )
        );

        player.addStatusEffect(
                new StatusEffectInstance(
                        StatusEffects.NAUSEA,
                        100,
                        1,
                        false,
                        false
                )
        );

        player.addStatusEffect(
                new StatusEffectInstance(
                        StatusEffects.BLINDNESS,
                        40,
                        1,
                        false,
                        false
                )
        );

        player.addStatusEffect(
                new StatusEffectInstance(
                        StatusEffects.WITHER,
                        40,
                        3,
                        false,
                        false
                )
        );
    }
}