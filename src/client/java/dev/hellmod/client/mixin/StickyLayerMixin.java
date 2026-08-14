package dev.hellmod.client.mixin;

import dev.hellmod.client.Renderer.StickyGreenLayer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class StickyLayerMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addStickyLayer(
            EntityRendererFactory.Context context,
            boolean slim,
            CallbackInfo ci
    ) {

        ((PlayerRendererFeatureAccessor<
                AbstractClientPlayerEntity,
                PlayerEntityModel<AbstractClientPlayerEntity>>)(Object)this)
                .invokeAddFeature(
                        new StickyGreenLayer(
                                (PlayerEntityRenderer)(Object)this
                        )
                );
    }
}