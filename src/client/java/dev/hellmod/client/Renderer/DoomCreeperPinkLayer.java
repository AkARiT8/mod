package dev.hellmod.client.Renderer;

import dev.hellmod.client.model.DoomCreeperModel;
import dev.hellmod.entity.DoomCreeperEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class DoomCreeperPinkLayer
        extends FeatureRenderer<DoomCreeperEntity, DoomCreeperModel> {

    private static final Identifier TEXTURE =
            Identifier.of(
                    "hellmod",
                    "textures/entity/doom_creeper.png"
            );

    public DoomCreeperPinkLayer(
            FeatureRendererContext<DoomCreeperEntity, DoomCreeperModel> context
    ) {
        super(context);
    }

    @Override
    public void render(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            DoomCreeperEntity entity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float animationProgress,
            float headYaw,
            float headPitch
    ) {
        float fuse = entity.getClientFuseTime(tickDelta);

        float progress =
                MathHelper.clamp(fuse, 0.0F, 1.0F);

        if (progress <= 0.0F) {
            return;
        }

        VertexConsumer vertexConsumer =
                vertexConsumers.getBuffer(
                        RenderLayer.getEntityTranslucent(TEXTURE)
                );

        // Color rosa
        float red = 1.0F;
        float green = 0.3F;
        float blue = 0.6F;

        // Opacidad según la ignición
        float alpha = progress;

        this.getContextModel().render(
                matrices,
                vertexConsumer,
                light,
                OverlayTexture.DEFAULT_UV,
                red,
                green,
                blue,
                alpha
        );
    }
}