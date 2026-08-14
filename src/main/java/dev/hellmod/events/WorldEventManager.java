package dev.hellmod.events;

import dev.hellmod.network.EventSyncPayload;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldEventManager {

    private static final Random RANDOM =
            new Random();

    private static final List<Class<?
            extends WorldEvent>> EVENT_POOL =
            new ArrayList<>();

    private static WorldEvent activeEvent;

    private static int cooldown = 0;

    public static void init() {

        ServerTickEvents.END_SERVER_TICK.register(server -> {

            ServerWorld world =
                    server.getOverworld();

            EventSaveState saveState =

                    world.getPersistentStateManager()
                            .getOrCreate(

                                    EventSaveState.TYPE,

                                    "hellmod_events"
                            );

            if (
                    activeEvent == null
                            &&
                            saveState.hasActiveEvent
            ) {

                activeEvent =
                        createEventFromId(
                                saveState.activeEventId
                        );

                if (activeEvent != null) {

                    activeEvent.load(
                            saveState
                    );
                }
            }

            if (activeEvent != null) {

                activeEvent.tick(world);

                syncEvent(world);

                saveState.hasActiveEvent = true;

                saveState.activeEventId =
                        activeEvent.getId();

                activeEvent.save(
                        saveState
                );

                saveState.markDirty();

                if (activeEvent.isFinished()) {

                    activeEvent.stop(world);

                    activeEvent = null;

                    syncEvent(world);

                    saveState.hasActiveEvent = false;

                    saveState.markDirty();

                    cooldown = 0;
                }

                return;
            }

            cooldown++;

            if (cooldown < 20 * 60 * 5) {
                return;
            }

            cooldown = 0;

            //startRandomEvent(world);
        });
    }
    public static void registerEvent(
            Class<? extends WorldEvent> eventClass
    ) {

        EVENT_POOL.add(eventClass);
    }

    private static void startRandomEvent(
            ServerWorld world
    ) {

        if (EVENT_POOL.isEmpty()) {
            return;
        }

        try {

            int index =
                    RANDOM.nextInt(
                            EVENT_POOL.size()
                    );

            activeEvent =
                    EVENT_POOL.get(index)
                            .getDeclaredConstructor()
                            .newInstance();

            activeEvent.start(world);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public static WorldEvent getActiveEvent() {

        return activeEvent;
    }

    public static void forceStartEvent(
            WorldEvent event,
            ServerWorld world
    ) {

        EventSaveState saveState =

                world.getPersistentStateManager()
                        .getOrCreate(

                                EventSaveState.TYPE,

                                "hellmod_events"
                        );

        if (activeEvent != null) {

            activeEvent.stop(world);
        }

        activeEvent = event;

        activeEvent.start(world);

        saveState.hasActiveEvent = true;

        saveState.activeEventId =
                activeEvent.getId();

        activeEvent.save(
                saveState
        );

        saveState.markDirty();
    }

    public static void stopCurrentEvent(
            ServerWorld world
    ) {

        if (activeEvent == null) {
            return;
        }

        EventSaveState saveState =

                world.getPersistentStateManager()
                        .getOrCreate(

                                EventSaveState.TYPE,

                                "hellmod_events"
                        );

        activeEvent.stop(world);

        activeEvent = null;

        saveState.hasActiveEvent = false;

        saveState.markDirty();
    }
    private static WorldEvent createEventFromId(
            String id
    ) {

        return switch (id) {

            case "lightning_storm" ->

                    new LightningStormEvent();

            default -> null;
        };
    }
    private static void syncEvent(
            ServerWorld world
    ) {

        String eventId =
                activeEvent != null
                        ? activeEvent.getId()
                        : "";

        String state =
                activeEvent != null
                        ? activeEvent.getState().name()
                        : "";

        EventSyncPayload payload =
                new EventSyncPayload(

                        eventId,

                        state
                );

        for (ServerPlayerEntity player
                : world.getServer()
                .getPlayerManager()
                .getPlayerList()) {

            ServerPlayNetworking.send(

                    player,

                    payload
            );
        }
    }
}
