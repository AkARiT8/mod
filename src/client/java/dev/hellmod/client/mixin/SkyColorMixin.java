package dev.hellmod.client.mixin;

import dev.hellmod.client.ClientEventState;
import dev.hellmod.stage.manager.StageManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public class SkyColorMixin {

    @Inject(
            method = "getSkyColor",
            at = @At("HEAD"),
            cancellable = true
    )
    private void modifySkyColor(
            Vec3d cameraPos,
            float tickDelta,
            CallbackInfoReturnable<Vec3d> cir
    ) {

        if (  ClientEventState.currentEvent
                .equals("lightning_storm")) {

            cir.setReturnValue(

                    new Vec3d(

                            0.05,
                            0.25,
                            0.7
                    )
            );
        }
    }
}