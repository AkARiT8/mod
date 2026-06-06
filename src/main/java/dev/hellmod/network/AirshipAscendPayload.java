package dev.hellmod.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record AirshipAscendPayload(
        boolean ascending,
        boolean braking
) implements CustomPayload {

    public static final Id<AirshipAscendPayload> ID =
            new Id<>(
                    new Identifier(
                            "hellmod",
                            "airship_ascend"
                    )
            );

    public static final PacketCodec<
            RegistryByteBuf,
            AirshipAscendPayload> CODEC =

            PacketCodec.tuple(

                    PacketCodecs.BOOL,
                    AirshipAscendPayload::ascending,

                    PacketCodecs.BOOL,
                    AirshipAscendPayload::braking,

                    AirshipAscendPayload::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}