package dev.hellmod.client;

import dev.hellmod.blocks.custom.StageSyncPayload;
import dev.hellmod.stage.manager.StageManager;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ModClientNetworking {

    public static void register() {

        ClientPlayNetworking.registerGlobalReceiver(
                StageSyncPayload.PACKET_ID,
                (payload, context) -> {
                    context.client().execute(() -> {
                        StageManager.setStage(payload.stage());
                    });
                }
        );
    }
}