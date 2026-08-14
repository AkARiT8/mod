package dev.hellmod.structures.InfernalManor;

import dev.hellmod.stage.manager.StageData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InfernalManorBlockPlacement {

    public static ActionResult onUseBlock(
            PlayerEntity player,
            World world,
            Hand hand,
            BlockHitResult hit
    ) {

        if (world.isClient()) {
            return ActionResult.PASS;
        }

        if (!(player.getStackInHand(hand).getItem() instanceof BlockItem)) {
            return ActionResult.PASS;
        }

        ServerWorld serverWorld = (ServerWorld) world;

        StageData stageData =
                StageData.get(serverWorld);

        if (stageData.getStage() >= 2) {
            return ActionResult.PASS;
        }

        BlockPos placePos =
                hit.getBlockPos().offset(
                        hit.getSide()
                );

        if (!InfernalManorUtil.isInsideInfernalManor(
                serverWorld,
                placePos
        )) {
            return ActionResult.PASS;
        }

        player.sendMessage(
                Text.literal("The manor rejects your offering."),
                true
        );

        return ActionResult.FAIL;
    }
}