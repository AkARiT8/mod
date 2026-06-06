package dev.hellmod.structures;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class BunkerDoor {

    private static final List<BlockPos> DOOR_BLOCKS =
            new ArrayList<>();

    private static boolean opening = false;

    private static boolean closing = false;

    private static int timer = 0;

    private static int progress = 0;

    public static void init() {

        ServerTickEvents.END_SERVER_TICK.register(server -> {

            if (!opening && !closing) {
                return;
            }

            timer++;

            if (timer < 20) {
                return;
            }

            timer = 0;

            ServerWorld world =
                    server.getOverworld();

            int blocksPerStep = 3;

            for (int i = 0;
                 i < blocksPerStep;
                 i++) {

                if (progress >= DOOR_BLOCKS.size()) {

                    opening = false;
                    closing = false;

                    return;
                }

                BlockPos pos;

                if (opening) {

                    pos =
                            DOOR_BLOCKS.get(progress);

                } else {

                    pos =
                            DOOR_BLOCKS.get(
                                    DOOR_BLOCKS.size()
                                            - 1
                                            - progress
                            );
                }

                world.playSound(

                        null,

                        pos,

                        SoundEvents.BLOCK_VINE_BREAK,

                        SoundCategory.BLOCKS,

                        1.0F,

                        0.8F
                );

                if (opening) {

                    world.breakBlock(
                            pos,
                            false
                    );
                }

                if (closing) {

                    world.setBlockState(

                            pos,

                            Blocks.OAK_LOG
                                    .getDefaultState()
                    );
                }

                progress++;
            }
        });
    }

    public static void setupDoor(
            BlockPos structurePos
    ) {

        DOOR_BLOCKS.clear();

        DOOR_BLOCKS.add(
                structurePos.add(8, 2, 5)
        );

        DOOR_BLOCKS.add(
                structurePos.add(9, 2, 5)
        );

        DOOR_BLOCKS.add(
                structurePos.add(10, 2, 5)
        );

        DOOR_BLOCKS.add(
                structurePos.add(8, 3, 5)
        );

        DOOR_BLOCKS.add(
                structurePos.add(9, 3, 5)
        );

        DOOR_BLOCKS.add(
                structurePos.add(10, 3, 5)
        );

        DOOR_BLOCKS.add(
                structurePos.add(8, 4, 5)
        );

        DOOR_BLOCKS.add(
                structurePos.add(9, 4, 5)
        );

        DOOR_BLOCKS.add(
                structurePos.add(10, 4, 5)
        );
    }

    public static void open() {

        closing = false;

        opening = true;

        timer = 0;

        progress = 0;
    }

    public static void close() {

        opening = false;

        closing = true;

        timer = 0;

        progress = 0;
    }
}