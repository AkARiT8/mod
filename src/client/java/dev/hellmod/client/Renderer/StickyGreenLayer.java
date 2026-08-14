package dev.hellmod.client.Renderer;

import dev.hellmod.effects.ModEffects;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.registry.Registries;

public class StickyGreenLayer extends FeatureRenderer<
        AbstractClientPlayerEntity,
        PlayerEntityModel<AbstractClientPlayerEntity>> {

    public StickyGreenLayer(
            FeatureRendererContext<
                    AbstractClientPlayerEntity,
                    PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
    }

    @Override
    public void render(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            AbstractClientPlayerEntity player,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float animationProgress,
            float headYaw,
            float headPitch
    ) {

        var sticky = Registries.STATUS_EFFECT.getEntry(ModEffects.STICKY);

        if (!player.hasStatusEffect(sticky)) {
            return;
        }

        VertexConsumer consumer = vertexConsumers.getBuffer(
                RenderLayer.getEntityTranslucent(
                        player.getSkinTextures().texture()
                )
        );

        getContextModel().render(
                matrices,
                consumer,
                light,
                OverlayTexture.DEFAULT_UV,
                0.1f,
                1.0f,
                0.1f,
                0.75f
        );
    }
}