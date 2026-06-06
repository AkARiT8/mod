package dev.hellmod.network;

import dev.hellmod.blocks.custom.StageSyncPayload;
import dev.hellmod.stage.manager.StageData;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class ModServerEvents {

    public static void register() {

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            sendStage(handler.player);
        });
    }

    public static void sendStage(ServerPlayerEntity player) {

        ServerWorld world = player.getServerWorld();
        StageData data = StageData.get(world);

        ServerPlayNetworking.send(player, new StageSyncPayload(data.getStage()));
    }
}