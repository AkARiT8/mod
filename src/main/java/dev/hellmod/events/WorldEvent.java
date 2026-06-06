package dev.hellmod.events;

import net.minecraft.server.world.ServerWorld;

public interface WorldEvent {

    void start(ServerWorld world);

    void tick(ServerWorld world);

    void stop(ServerWorld world);

    boolean isFinished();

    EventState getState();

    String getId();

    void save(EventSaveState state);

    void load(EventSaveState state);
}