package dev.hellmod.network;

import dev.hellmod.blocks.custom.StageSyncPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ModNetworking {

    public static void register() {
        PayloadTypeRegistry.playS2C().register(
                StageSyncPayload.PACKET_ID,
                StageSyncPayload.CODEC
        );
    }
}