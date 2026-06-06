package dev.hellmod.blocks.custom;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record StageSyncPayload(int stage) implements CustomPayload {

    public static final Identifier ID = new Identifier("hellmod", "stage_sync");
    public static final Id<StageSyncPayload> PACKET_ID = new Id<>(ID);

    public static final PacketCodec<RegistryByteBuf, StageSyncPayload> CODEC =
            PacketCodec.of(
                    (payload, buf) -> buf.writeInt(payload.stage()),
                    buf -> new StageSyncPayload(buf.readInt())
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}