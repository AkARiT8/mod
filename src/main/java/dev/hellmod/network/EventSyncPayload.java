package dev.hellmod.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record EventSyncPayload(
        String event,
        String state
) implements CustomPayload {

    public static final Id<EventSyncPayload> ID =

            new Id<>(
                    new Identifier(
                            "hellmod",
                            "event_sync"
                    )
            );

    public static final PacketCodec<
            RegistryByteBuf,
            EventSyncPayload> CODEC =

            PacketCodec.tuple(

                    PacketCodecs.STRING,

                    EventSyncPayload::event,

                    PacketCodecs.STRING,

                    EventSyncPayload::state,

                    EventSyncPayload::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {

        return ID;
    }
}