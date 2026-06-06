package dev.hellmod.network;

import net.minecraft.util.Identifier;

public class ModPacket {
    public static final Identifier SHOW_TOTEM = new Identifier("hellmod", "show_totem");
    public static final Identifier STAGE_SYNC = new Identifier("hellmod", "stage_sync");
    public static final Identifier EVENT_SYNC = new Identifier("hellmod", "event_sync");
    public static final Identifier ASCEND = Identifier.of("hellmod", "ascend");
}
