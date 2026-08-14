package dev.hellmod.mixin;

import dev.hellmod.stage.manager.StageData;
import dev.hellmod.structures.InfernalManor.InfernalManorUtil;
import net.minecraft.block.spawner.MobSpawnerLogic;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobSpawnerLogic.class)
public class MobSpawnerLogicMixin {

    @Inject(
            method = "serverTick",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelInfernalManorSpawns(
            ServerWorld world,
            BlockPos pos,
            CallbackInfo ci
    ) {

        StageData stageData =
                StageData.get(world);

        if (stageData.getStage() >= 2) {
            return;
        }

        if (!InfernalManorUtil.isInsideInfernalManor(
                world,
                pos
        )) {
            return;
        }

        ci.cancel();
    }
}