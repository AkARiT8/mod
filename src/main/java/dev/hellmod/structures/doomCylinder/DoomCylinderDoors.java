package dev.hellmod.structures.doomCylinder;

import dev.hellmod.blocks.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import static dev.hellmod.structures.doomCylinder.DoomCylinderController.DoorBlock;

import java.util.List;

public class DoomCylinderDoors {

    public static final List<List<DoorBlock>> ENTRY_DOOR_OPEN = List.of(

            List.of(
                    new DoorBlock(
                            new BlockPos(-1, -2, -7),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, -2, -7),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, -2, -9),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, -2, -9),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            ),

            List.of(
                    new DoorBlock(
                            new BlockPos(-2, -2, -7),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, -2, -6),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, -2, -7),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, -2, -6),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, -2, -9),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, -2, -10),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, -2, -9),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, -2, -10),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            ),

            List.of(
                    new DoorBlock(
                            new BlockPos(-3, -2, -7),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, -2, -6),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, -2, -5),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, -2, -7),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, -2, -6),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, -2, -5),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-3, -2, -9),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, -2, -10),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, -2, -11),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, -2, -9),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, -2, -10),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, -2, -11),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            ),

            List.of(
                    new DoorBlock(
                            new BlockPos(-4, -2, -7),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-3, -2, -6),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, -2, -5),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, -2, -4),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(4, -2, -7),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, -2, -6),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, -2, -5),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, -2, -4),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-4, -2, -9),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-3, -2, -10),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, -2, -11),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, -2, -12),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(4, -2, -9),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, -2, -10),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, -2, -11),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, -2, -12),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            ),

            List.of(
                    new DoorBlock(
                            new BlockPos(-4, -2, -6),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-3, -2, -5),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, -2, -4),
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(4, -2, -6),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, -2, -5),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, -2, -4),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-4, -2, -10),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-3, -2, -11),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, -2, -12),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(4, -2, -10),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, -2, -11),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, -2, -12),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            ),

            List.of(
                    new DoorBlock(
                            new BlockPos(0, -2, -8),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            ),

            List.of(
                    new DoorBlock(
                            new BlockPos(0, -2, -7),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(0, -2, -9),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, -2, -8),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, -2, -8),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            ),

            List.of(
                    new DoorBlock(
                            new BlockPos(0, -2, -6),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(0, -2, -10),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, -2, -8),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, -2, -8),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            ),

            List.of(
                    new DoorBlock(
                            new BlockPos(0, -2, -5),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(0, -2, -11),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-3, -2, -8),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, -2, -8),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            ),

            List.of(
                    new DoorBlock(
                            new BlockPos(0, -2, -4),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(0, -2, -12),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-4, -2, -8),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(4, -2, -8),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            )



    );

    public static final List<List<DoorBlock>> ENTRY_DOOR_CLOSE = List.of(

            List.of(
                    new DoorBlock(
                            new BlockPos(0, 10, 3),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.Z)
                    ),
                    new DoorBlock(
                            new BlockPos(0, 10, 11),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.Z)
                    ),
                    new DoorBlock(
                            new BlockPos(-4, 10, 7),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.X)
                    ),
                    new DoorBlock(
                            new BlockPos(4, 10, 7),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.X)
                    )
            ),
            List.of(
                    new DoorBlock(
                            new BlockPos(0, 10, 4),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.Z)
                    ),
                    new DoorBlock(
                            new BlockPos(0, 10, 10),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.Z)
                    ),
                    new DoorBlock(
                            new BlockPos(-3, 10, 7),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.X)
                    ),
                    new DoorBlock(
                            new BlockPos(3, 10, 7),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.X)
                    )
            ),
            List.of(
                    new DoorBlock(
                            new BlockPos(0, 10, 5),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.Z)
                    ),
                    new DoorBlock(
                            new BlockPos(0, 10, 9),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.Z)
                    ),
                    new DoorBlock(
                            new BlockPos(-2, 10, 7),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.X)
                    ),
                    new DoorBlock(
                            new BlockPos(2, 10, 7),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.X)
                    )
            ),
            List.of(
                    new DoorBlock(
                            new BlockPos(0, 10, 6),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.Z)
                    ),
                    new DoorBlock(
                            new BlockPos(0, 10, 8),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.Z)
                    ),
                    new DoorBlock(
                            new BlockPos(-1, 10, 7),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.X)
                    ),
                    new DoorBlock(
                            new BlockPos(1, 10, 7),
                            ModBlocks.DARK_OBSIDIAN_PILAR.getLeft().getDefaultState().with(Properties.AXIS, Direction.Axis.X)
                    )
            ),
            List.of(
                    new DoorBlock(
                            new BlockPos(0, 10, 7),
                            ModBlocks.DARK_OBSIDIAN_NODE.getLeft().getDefaultState()
                    )
            ),
            List.of(
                    new DoorBlock(
                            new BlockPos(-4, 10, 5),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-3, 10, 4),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, 10, 3),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(4, 10, 5),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, 10, 4),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, 10, 3),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-4, 10, 9),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-3, 10, 10),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, 10, 11),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(4, 10, 9),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, 10, 10),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, 10, 11),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    )
            ),
            List.of(
                    new DoorBlock(
                            new BlockPos(-4, 10, 6),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-3, 10, 5),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, 10, 4),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, 10, 3),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(4, 10, 6),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, 10, 5),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, 10, 4),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, 10, 3),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-4, 10, 8),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-3, 10, 9),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, 10, 10),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, 10, 11),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(4, 10, 8),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, 10, 9),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, 10, 10),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, 10, 11),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    )
            ),
            List.of(
                    new DoorBlock(
                            new BlockPos(-3, 10, 6),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, 10, 5),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, 10, 4),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, 10, 6),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, 10, 5),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, 10, 4),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-3, 10, 8),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, 10, 9),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, 10, 10),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(3, 10, 8),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, 10, 9),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, 10, 10),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    )
            ),
            List.of(
                    new DoorBlock(
                            new BlockPos(-2, 10, 6),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, 10, 5),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, 10, 6),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, 10, 5),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-2, 10, 8),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, 10, 9),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(2, 10, 8),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, 10, 9),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    )
            ),
            List.of(
                    new DoorBlock(
                            new BlockPos(-1, 10, 6),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, 10, 6),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(-1, 10, 8),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    ),
                    new DoorBlock(
                            new BlockPos(1, 10, 8),
                            ModBlocks.DARK_OBSIDIAN_SLAB.getLeft().getDefaultState()
                    )
            )
    );

    public static final List<List<DoorBlock>> SECOND_DOOR = List.of(

            List.of(
                    new DoorBlock(
                            new BlockPos(-1, -1, 17),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),

                    new DoorBlock(
                            new BlockPos(0, -1, 17),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),

                    new DoorBlock(
                            new BlockPos(1, -1, 17),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            ),

            List.of(
                    new DoorBlock(
                            new BlockPos(-1, 0, 17),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),

                    new DoorBlock(
                            new BlockPos(0, 0, 17),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),

                    new DoorBlock(
                            new BlockPos(1, 0, 17),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            ),

            List.of(
                    new DoorBlock(
                            new BlockPos(-1, 1, 17),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),

                    new DoorBlock(
                            new BlockPos(0, 1, 17),
                            Blocks.OBSIDIAN.getDefaultState()
                    ),

                    new DoorBlock(
                            new BlockPos(1, 1, 17),
                            Blocks.OBSIDIAN.getDefaultState()
                    )
            )
    );
}