package dev.hellmod.structures;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class InteriorDebug {

    public static void init() {

        ServerTickEvents.END_SERVER_TICK.register(server -> {

            var world =
                    server.getOverworld();

            for (ServerPlayerEntity player
                    : world.getPlayers()) {
            }
        });
    }
}