package dev.hellmod.structures.InfernalManor;

import dev.hellmod.stage.manager.StageData;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InfernalManorBlockProtection {

    public static boolean beforeBreak(
            World world,
            PlayerEntity player,
            BlockPos pos,
            BlockState state,
            Object blockEntity
    ) {

        if (world.isClient()) {
            return true;
        }

        ServerWorld serverWorld = (ServerWorld) world;

        StageData stageData =
                StageData.get(serverWorld);

        if (stageData.getStage() >= 2) {
            return true;
        }

        if (!InfernalManorUtil.isInsideInfernalManor(
                serverWorld,
                pos
        )) {
            return true;
        }

        player.sendMessage(
                Text.literal("The manor resists your efforts."),
                true
        );

        return false;
    }
}