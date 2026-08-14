package dev.hellmod.structures.InfernalManor;

import dev.hellmod.stage.manager.StageData;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class InfernalManorChestHandler {

    public static ActionResult onUseBlock(
            PlayerEntity player,
            World world,
            Hand hand,
            BlockHitResult hit
    ) {

        if (world.isClient()) {
            return ActionResult.PASS;
        }

        Block block =
                world.getBlockState(hit.getBlockPos())
                        .getBlock();

        if (!(block instanceof ChestBlock)) {
            return ActionResult.PASS;
        }

        ServerWorld serverWorld =
                (ServerWorld) world;

        StageData stageData =
                StageData.get(serverWorld);

        if (stageData.getStage() >= 2) {
            return ActionResult.PASS;
        }

        if (!InfernalManorUtil.isInsideInfernalManor(
                serverWorld,
                hit.getBlockPos()
        )) {
            return ActionResult.PASS;
        }

        player.sendMessage(
                Text.literal("The manor remains sealed."),
                true
        );

        return ActionResult.FAIL;
    }
}