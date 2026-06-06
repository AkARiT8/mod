package dev.hellmod.client;

import dev.hellmod.client.model.AirshipModel;
import dev.hellmod.entity.AirshipEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;

public class AirshipRenderer extends EntityRenderer<AirshipEntity> {

    private final AirshipModel model;

    public AirshipRenderer(EntityRendererFactory.Context context) {
        super(context);

        this.model = new AirshipModel(
                context.getPart(
                        AirshipModel.LAYER_LOCATION
                )
        );
    }

    @Override
    public void render(
            AirshipEntity entity,
            float entityYaw,
            float tickDelta,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light
    ) {

        matrices.push();

        matrices.translate(0, 1.4, 0);
        matrices.scale(2.8f, -2.8f, 2.8f);

        float yaw = getYaw(entity, entityYaw);

        float roll = MathHelper.clamp(
                entity.getTrackedYawVelocity() * 15f,
                -150f,
                150f
        );

        matrices.multiply(
                new Quaternionf()
                        .rotateY((float)Math.toRadians(-yaw))
                        .rotateX((float)Math.toRadians(-entity.getTrackedPitch() * 1.3))
                        .rotateZ((float)Math.toRadians(-roll))
        );

        model.setAngles(
                entity,
                0,
                0,
                entity.age + tickDelta,
                0,
                0
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
                1,
                1,
                1,
                1
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

    private static float getYaw(AirshipEntity entity, float entityYaw) {
        Vec3d velocity = entity.getVelocity();

        float yaw = entityYaw;


        if (
                velocity.horizontalLengthSquared() > 0.001
                        && !entity.horizontalCollision
        ) {

            float velocityYaw = (float) Math.toDegrees(
                    Math.atan2(
                            -velocity.x,
                            velocity.z
                    )
            );

            float driftFactor = 0.7f;

            float delta = MathHelper.wrapDegrees(
                    velocityYaw - entityYaw
            );

            yaw = entityYaw + delta * driftFactor;

        }
        return yaw;
    }

    @Override
    public Identifier getTexture(AirshipEntity entity) {
        return new Identifier(
                "hellmod",
                "textures/entity/airship.png"
        );
    }
}