package dev.hellmod.client;

import dev.hellmod.client.ClientEventState;
import dev.hellmod.network.EventSyncPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientPacketHandler {

    public static void init() {

        ClientPlayNetworking.registerGlobalReceiver(

                EventSyncPayload.ID,

                (payload, context) -> {

                    ClientEventState.currentEvent =
                            payload.event();

                    ClientEventState.currentState =
                            payload.state();
                }
        );
    }
}