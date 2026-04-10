package dev.hellmod.blocks.custom;

import dev.hellmod.blocks.entity.StageBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StageBlock extends Block implements BlockEntityProvider {

    public StageBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StageBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, BlockHitResult hit) {

        if (!world.isClient) {
            var blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof StageBlockEntity) {
                player.openHandledScreen((StageBlockEntity) blockEntity);
            }
        }
        return ActionResult.SUCCESS;
    }
}