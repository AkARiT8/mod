package dev.hellmod.blocks.custom;

import dev.hellmod.blocks.entity.CustomButtonBlockEntity;
import dev.hellmod.structures.doomCylinder.DoomCylinderController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CustomButton extends Block implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty PRESSED = Properties.POWERED;

    private static final VoxelShape SHAPE_DOWN = VoxelShapes.cuboid(
            0.250,
            0.715,
            0.250,
            0.750,
            1.000,
            0.750
    );

    private static final VoxelShape SHAPE_UP = VoxelShapes.cuboid(
            0.250,
            0.000,
            0.250,
            0.750,
            0.285,
            0.750
    );

    private static final VoxelShape SHAPE_SOUTH= VoxelShapes.cuboid(
            0.250,
            0.250,
            0.000,
            0.750,
            0.750,
            0.285
    );

    private static final VoxelShape SHAPE_NORTH = VoxelShapes.cuboid(
            0.250,
            0.250,
            0.715,
            0.750,
            0.750,
            1.000
    );

    private static final VoxelShape SHAPE_WEST = VoxelShapes.cuboid(
            0.715,
            0.250,
            0.250,
            1.000,
            0.750,
            0.750
    );

    private static final VoxelShape SHAPE_EAST = VoxelShapes.cuboid(
            0.000,
            0.250,
            0.250,
            0.285,
            0.750,
            0.750
    );

    public CustomButton(Settings settings) {
        super(settings);

        setDefaultState(
                getStateManager()
                        .getDefaultState()
                        .with(FACING, Direction.UP)
                        .with(PRESSED, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, PRESSED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(
                FACING,
                ctx.getSide()
        );
    }

    @Override
    public VoxelShape getOutlineShape(
            BlockState state,
            BlockView world,
            BlockPos pos,
            ShapeContext context
    ) {
        return switch (state.get(FACING)) {
            case UP -> SHAPE_UP;
            case DOWN -> SHAPE_DOWN;
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
        };
    }

    @Override
    public BlockEntity createBlockEntity(
            BlockPos pos,
            BlockState state
    ) {
        return new CustomButtonBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    protected ActionResult onUse(
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            BlockHitResult hit
    ) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        if (!state.get(PRESSED)) {

            world.setBlockState(
                    pos,
                    state.with(PRESSED, true),
                    Block.NOTIFY_ALL
            );

            CustomButtonBlockEntity blockEntity =
                    (CustomButtonBlockEntity) world.getBlockEntity(pos);

            if (blockEntity != null && world instanceof ServerWorld serverWorld) {
                DoomCylinderController.activate(
                        serverWorld,
                        blockEntity
                );
            }
        }

        return ActionResult.SUCCESS;
    }

    public void resetButton(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);

        if (state.isOf(this) && state.get(PRESSED)) {
            world.setBlockState(
                    pos,
                    state.with(PRESSED, false),
                    Block.NOTIFY_ALL
            );
        }
    }
}