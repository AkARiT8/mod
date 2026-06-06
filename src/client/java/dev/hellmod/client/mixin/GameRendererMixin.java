package dev.hellmod.client.mixin;

import dev.hellmod.client.CameraRoll;
import net.minecraft.client.render.GameRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @ModifyVariable(
            method = "renderWorld",
            at = @At("STORE"),
            ordinal = 1
    )
    private Matrix4f hellmod$rollCamera(Matrix4f matrix) {

        matrix.rotateZ(
                (float)Math.toRadians(
                        CameraRoll.ROLL
                )
        );

        return matrix;
    }
}