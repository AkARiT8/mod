package dev.hellmod.structures.doomCylinder;

import dev.hellmod.blocks.custom.CustomButton;
import dev.hellmod.blocks.entity.CustomButtonBlockEntity;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import static dev.hellmod.structures.doomCylinder.DoomCylinderDoors.*;

import java.util.ArrayList;
import java.util.List;

public class DoomCylinderController {

    public enum ButtonType {
        ENTRY,
        LOCK,
        FINAL
    }

    private static final List<DoorSequence> ACTIVE_SEQUENCES =
            new ArrayList<>();


    public record DoorBlock(
            BlockPos offset,
            BlockState state
    ) {
    }


    private record DoorAction(
            BlockPos offset,
            BlockState state
    ) {
    }


    private static class DoorSequence {

        private final ServerWorld world;
        private final BlockPos buttonPos;
        private final List<List<DoorAction>> groups;

        private int groupIndex = 0;
        private int tickCounter = 0;

        private static final int TICKS_BETWEEN_GROUPS = 5;

        public DoorSequence(
                ServerWorld world,
                BlockPos buttonPos,
                List<List<DoorAction>> groups
        ) {
            this.world = world;
            this.buttonPos = buttonPos;
            this.groups = groups;
        }

        public boolean tick() {

            tickCounter++;

            if (tickCounter < TICKS_BETWEEN_GROUPS) {
                return false;
            }

            tickCounter = 0;

            if (groupIndex >= groups.size()) {
                return true;
            }

            List<DoorAction> group = groups.get(groupIndex);

            System.out.println(
                    "========== DOOM CYLINDER =========="
            );

            System.out.println(
                    "Ejecutando grupo "
                            + (groupIndex + 1)
                            + "/"
                            + groups.size()
            );

            for (DoorAction action : group) {

                BlockPos targetPos = buttonPos.add(
                        action.offset()
                );

                System.out.println(
                        "Bloque: "
                                + targetPos
                                + " -> "
                                + action.state().getBlock()
                );

                world.setBlockState(
                        targetPos,
                        action.state(),
                        3
                );
            }

            groupIndex++;

            System.out.println(
                    "==================================="
            );

            return groupIndex >= groups.size();
        }
    }


    public static void activate(
            ServerWorld world,
            CustomButtonBlockEntity button
    ) {
        switch (button.getButtonType()) {

            case ENTRY -> activateEntry(world, button);

            case LOCK -> activateLock(world, button);

            case FINAL -> activateFinal(world, button);
        }
    }


    public static void registerTick() {

        ServerTickEvents.END_SERVER_TICK.register(server -> {

            ACTIVE_SEQUENCES.removeIf(DoorSequence::tick);

        });
    }


    private static void activateEntry(
            ServerWorld world,
            CustomButtonBlockEntity button
    ) {

        openDoor(
                world,
                button,
                ENTRY_DOOR_OPEN
        );
    }

    private static void activateLock(
            ServerWorld world,
            CustomButtonBlockEntity button
    ) {

        BlockPos buttonPos = button.getPos();

        System.out.println(
                "========== DOOM CYLINDER =========="
        );

        System.out.println(
                "LOCK ACTIVADO"
        );

        System.out.println(
                "Botón: " + buttonPos
        );


        List<List<DoorAction>> actions =
                new ArrayList<>();
        for (List<DoorBlock> group : ENTRY_DOOR_CLOSE) {

            List<DoorAction> groupActions =
                    new ArrayList<>();

            for (DoorBlock block : group) {

                groupActions.add(
                        new DoorAction(
                                block.offset(),
                                block.state()
                        )
                );
            }

            actions.add(groupActions);
        }

        for (List<DoorBlock> group : SECOND_DOOR) {

            List<DoorAction> groupActions =
                    new ArrayList<>();

            for (DoorBlock block : group) {

                groupActions.add(
                        new DoorAction(
                                block.offset(),
                                Blocks.AIR.getDefaultState()
                        )
                );
            }

            actions.add(groupActions);
        }

        DoorSequence sequence =
                new DoorSequence(
                        world,
                        buttonPos,
                        actions
                );

        ACTIVE_SEQUENCES.add(sequence);


        System.out.println(
                "Secuencia LOCK creada con "
                        + actions.size()
                        + " grupos"
        );

        System.out.println(
                "==================================="
        );
    }


    private static void activateFinal(
            ServerWorld world,
            CustomButtonBlockEntity button
    ) {

        System.out.println(
                "DOOM CYLINDER: FINAL activado"
        );
    }

    private static void openDoor(
            ServerWorld world,
            CustomButtonBlockEntity button,
            List<List<DoorBlock>> door
    ) {

        BlockPos buttonPos = button.getPos();

        System.out.println(
                "========== DOOM CYLINDER =========="
        );

        System.out.println(
                "ABRIENDO PUERTA"
        );

        System.out.println(
                "Botón: " + buttonPos
        );

        System.out.println(
                "FACING: "
                        + button.getCachedState()
                        .get(CustomButton.FACING)
        );

        System.out.println(
                "Grupos: " + door.size()
        );


        List<List<DoorAction>> actions =
                new ArrayList<>();


        for (List<DoorBlock> group : door) {

            List<DoorAction> groupActions =
                    new ArrayList<>();

            for (DoorBlock block : group) {

                groupActions.add(
                        new DoorAction(
                                block.offset(),
                                Blocks.AIR.getDefaultState()
                        )
                );
            }

            actions.add(groupActions);
        }


        DoorSequence sequence =
                new DoorSequence(
                        world,
                        buttonPos,
                        actions
                );

        ACTIVE_SEQUENCES.add(sequence);


        System.out.println(
                "Secuencia creada con "
                        + actions.size()
                        + " grupos"
        );

        System.out.println(
                "==================================="
        );
    }

    private static void closeDoor(
            ServerWorld world,
            CustomButtonBlockEntity button,
            List<List<DoorBlock>> door
    ) {

        BlockPos buttonPos = button.getPos();

        System.out.println(
                "========== DOOM CYLINDER =========="
        );

        System.out.println(
                "CERRANDO PUERTA"
        );

        System.out.println(
                "Botón: " + buttonPos
        );

        System.out.println(
                "Grupos: " + door.size()
        );


        List<List<DoorAction>> actions =
                new ArrayList<>();


        for (List<DoorBlock> group : door) {

            List<DoorAction> groupActions =
                    new ArrayList<>();

            for (DoorBlock block : group) {

                groupActions.add(
                        new DoorAction(
                                block.offset(),
                                block.state()
                        )
                );
            }

            actions.add(groupActions);
        }


        DoorSequence sequence =
                new DoorSequence(
                        world,
                        buttonPos,
                        actions
                );

        ACTIVE_SEQUENCES.add(sequence);


        System.out.println(
                "Secuencia creada con "
                        + actions.size()
                        + " grupos"
        );

        System.out.println(
                "==================================="
        );
    }
}