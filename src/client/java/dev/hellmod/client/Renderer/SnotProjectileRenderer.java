package dev.hellmod.client.Renderer;

import dev.hellmod.client.model.SnotProjectileModel;
import dev.hellmod.entity.SnotProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class SnotProjectileRenderer
        extends EntityRenderer<SnotProjectileEntity> {

    private final SnotProjectileModel model;

    public SnotProjectileRenderer(
            EntityRendererFactory.Context context
    ) {
        super(context);

        this.model =
                new SnotProjectileModel(
                        context.getPart(
                                SnotProjectileModel.LAYER_LOCATION
                        )
                );
    }

    @Override
    public void render(
            SnotProjectileEntity entity,
            float entityYaw,
            float tickDelta,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light
    ) {

        matrices.push();

        double vx = entity.getVelocity().x;
        double vy = entity.getVelocity().y;
        double vz = entity.getVelocity().z;

        double horizontal =
                Math.sqrt(vx * vx + vz * vz);

        float yaw = (float)(
                Math.toDegrees(
                        Math.atan2(-vz, vx)
                ) - 90.0F
        );

        float pitch = (float)(
                -Math.toDegrees(
                        Math.atan2(-vy, horizontal)
                )
        );

        matrices.multiply(
                RotationAxis.POSITIVE_Y.rotationDegrees(
                        yaw
                )
        );

        matrices.multiply(
                RotationAxis.POSITIVE_X.rotationDegrees(
                        pitch
                )
        );

        model.render(
                matrices,
                vertexConsumers.getBuffer(
                        model.getLayer(
                                getTexture(entity)
                        )
                ),
                light,
                OverlayTexture.DEFAULT_UV,
                1f,
                1f,
                1f,
                1f
        );

        matrices.pop();

        super.render(
                entity,
                entityYaw,
                tickDelta,
                matrices,
                vertexConsumers,
                light
        );
    }

    @Override
    public Identifier getTexture(
            SnotProjectileEntity entity
    ) {
        return Identifier.of(
                "hellmod",
                "textures/entity/snot_projectile.png"
        );
    }
}