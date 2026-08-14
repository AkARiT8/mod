package dev.hellmod.client.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.hellmod.client.ClientEventState;
import dev.hellmod.stage.manager.StageManager;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class FogMixin {

    @Inject(method = "applyFog", at = @At("TAIL"))
    private static void applyFog(
            Camera camera,
            BackgroundRenderer.FogType fogType,
            float viewDistance,
            boolean thickFog,
            float tickDelta,
            CallbackInfo ci
    ) {

//        if (StageManager.getCurrentStage() == 2) {
//
//            RenderSystem.setShaderFogStart(0.0F);
//            RenderSystem.setShaderFogEnd(12.0F);
//
//            RenderSystem.setShaderFogColor(
//
//                    0.15F,
//                    0.15F,
//                    0.15F
//            );
//        }
//
//        if (  ClientEventState.currentEvent
//                .equals("lightning_storm")) {
//
//            RenderSystem.setShaderFogStart(5.0F);
//            RenderSystem.setShaderFogEnd(180.0F);
//
//            RenderSystem.setShaderFogColor(
//
//                    0.0F,
//                    0.28F,
//                    0.67F
//            );
//        }
    }
}