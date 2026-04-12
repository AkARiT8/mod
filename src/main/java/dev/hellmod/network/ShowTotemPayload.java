package dev.hellmod.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ShowTotemPayload() implements CustomPayload {

    public static final Id<ShowTotemPayload> ID =
            new Id<>(new Identifier("hellmod", "show_totem"));

    public static final PacketCodec<RegistryByteBuf, ShowTotemPayload> CODEC =
            PacketCodec.unit(new ShowTotemPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}