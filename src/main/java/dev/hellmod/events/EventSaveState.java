package dev.hellmod.events;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.PersistentState;

public class EventSaveState
        extends PersistentState {

    public boolean hasActiveEvent = false;

    public String activeEventId = "";

    public String eventState = "";

    public int timer = 0;

    public int finalStrikes = 0;

    @Override
    public NbtCompound writeNbt(
            NbtCompound nbt,
            RegistryWrapper.WrapperLookup registryLookup
    ) {

        nbt.putBoolean(
                "hasActiveEvent",
                hasActiveEvent
        );

        nbt.putString(
                "activeEventId",
                activeEventId
        );

        nbt.putString(
                "eventState",
                eventState
        );

        nbt.putInt(
                "timer",
                timer
        );

        nbt.putInt(
                "finalStrikes",
                finalStrikes
        );

        return nbt;
    }

    public static EventSaveState fromNbt(
            NbtCompound nbt,
            RegistryWrapper.WrapperLookup registryLookup
    ) {

        EventSaveState state =
                new EventSaveState();

        state.hasActiveEvent =
                nbt.getBoolean(
                        "hasActiveEvent"
                );

        state.activeEventId =
                nbt.getString(
                        "activeEventId"
                );

        state.eventState =
                nbt.getString(
                        "eventState"
                );

        state.timer =
                nbt.getInt(
                        "timer"
                );

        state.finalStrikes =
                nbt.getInt(
                        "finalStrikes"
                );

        return state;
    }

    public static final Type<EventSaveState> TYPE =
            new Type<>(

                    EventSaveState::new,

                    EventSaveState::fromNbt,

                    null
            );
}