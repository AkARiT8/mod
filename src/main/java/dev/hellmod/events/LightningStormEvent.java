package dev.hellmod.events;

import dev.hellmod.structures.BunkerDoor;
import dev.hellmod.structures.WorldStructureGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class LightningStormEvent implements WorldEvent {

    private EventState state =
            EventState.PREPARING;

    private int timer = 0;

    private int finalStrikes = 0;

    private boolean finished = false;

    private boolean bunkerOpened = false;

    @Override
    public void start(
            ServerWorld world
    ) {

        timer = 0;

        state = EventState.PREPARING;

        finished = false;

        finalStrikes = 0;

        bunkerOpened = false;

        world.setWeather(
                0,
                999999,
                true,
                true
        );

        for (ServerPlayerEntity player
                : world.getServer()
                .getPlayerManager()
                .getPlayerList()) {

            player.sendMessage(

                    Text.literal(
                            "§c⚠ ELECTRICAL STORM INCOMING ⚠"
                    )
            );
        }
    }

    @Override
    public void tick(
            ServerWorld world
    ) {

        timer++;

        if (state == EventState.PREPARING) {

            if (timer >= 20 * 15) {

                timer = 0;

                state = EventState.ACTIVE;

                world.getServer()
                        .getPlayerManager()
                        .getPlayerList()
                        .forEach(player ->

                                player.sendMessage(

                                        Text.literal(
                                                "§4TAKE COVER NOW"
                                        )
                                )
                        );
            }

            return;
        }

        if (state == EventState.ACTIVE) {

            if (timer % 60 == 0) {

                for (ServerPlayerEntity player
                        : world.getServer()
                        .getPlayerManager()
                        .getPlayerList()) {

                    if (
                            WorldStructureGenerator
                                    .isInsideInterior(
                                            player.getBlockPos()
                                    )
                    ) {

                        continue;
                    }

                    LightningEntity lightning =
                            EntityType.LIGHTNING_BOLT
                                    .create(
                                            player.getServerWorld()
                                    );

                    if (lightning == null) {
                        continue;
                    }

                    lightning.refreshPositionAfterTeleport(
                            player.getX(),
                            player.getY(),
                            player.getZ()
                    );

                    player.getServerWorld()
                            .spawnEntity(
                                    lightning
                            );
                }
            }

            if (timer >= 20 * 60) {

                timer = 0;

                BunkerDoor.close();

                state = EventState.ENDING;
            }

            return;
        }

        if (state == EventState.ENDING) {

            if (timer % 10 == 0) {

                finalStrikes++;

                for (ServerPlayerEntity player
                        : world.getServer()
                        .getPlayerManager()
                        .getPlayerList()) {

                    if (
                            WorldStructureGenerator
                                    .isInsideInterior(
                                            player.getBlockPos()
                                    )
                    ) {

                        System.out.println(
                                player.getName().getString()
                                        + " SAFE INSIDE"
                        );

                        continue;
                    }

                    LightningEntity lightning =
                            EntityType.LIGHTNING_BOLT
                                    .create(
                                            player.getServerWorld()
                                    );

                    if (lightning == null) {
                        continue;
                    }

                    lightning.refreshPositionAfterTeleport(

                            player.getX(),
                            player.getY(),
                            player.getZ()
                    );

                    player.getServerWorld()
                            .spawnEntity(
                                    lightning
                            );

                    player.damage(

                            player.getServerWorld()
                                    .getDamageSources()
                                    .lightningBolt(),

                            40.0F
                    );
                }
            }

            if (finalStrikes >= 20) {

                timer = 0;

                state = EventState.FINAL;
            }

            return;
        }

        if (state == EventState.FINAL) {

            if (!bunkerOpened) {

                bunkerOpened = true;

                BunkerDoor.open();
            }

            world.setWeather(
                    0,
                    0,
                    false,
                    false
            );

            world.getServer()
                    .getPlayerManager()
                    .getPlayerList()
                    .forEach(player ->

                            player.sendMessage(

                                    Text.literal(
                                            "§aStorm ended"
                                    )
                            )
                    );

            state = EventState.FINISHED;

            finished = true;
        }
    }

    @Override
    public void stop(
            ServerWorld world
    ) {

        world.setWeather(
                0,
                0,
                false,
                false
        );
    }

    @Override
    public boolean isFinished() {

        return finished;
    }

    @Override
    public EventState getState() {

        return state;
    }

    @Override
    public void save(
            EventSaveState state
    ) {

        state.timer = timer;

        state.finalStrikes = finalStrikes;

        state.eventState =
                this.state.name();
    }

    @Override
    public void load(
            EventSaveState state
    ) {

        timer = state.timer;

        finalStrikes =
                state.finalStrikes;

        this.state =
                EventState.valueOf(
                        state.eventState
                );
    }

    @Override
    public String getId() {

        return "lightning_storm";
    }
}