package dev.hellmod.client.Renderer;

import dev.hellmod.client.model.DoomCreeperModel;
import dev.hellmod.entity.DoomCreeperEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;

public class DoomCreeperRenderer
        extends MobEntityRenderer<DoomCreeperEntity, DoomCreeperModel> {

    private static final Identifier TEXTURE =
            Identifier.of("hellmod", "textures/entity/doom_creeper.png");

    private static final Identifier BEAM_TEXTURE =
            Identifier.of("hellmod", "textures/entity/doom_beam.png");

    private final Map<Integer, Vec3d> beamVisualTargets = new HashMap<>();

    public DoomCreeperRenderer(EntityRendererFactory.Context context) {

        super(
                context,
                new DoomCreeperModel(
                        context.getPart(DoomCreeperModel.LAYER_LOCATION)
                ),
                0.5F
        );

        this.addFeature(
                new DoomCreeperPinkLayer(this)
        );
    }

    @Override
    public Identifier getTexture(DoomCreeperEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(
            DoomCreeperEntity entity,
            MatrixStack matrices,
            float tickDelta
    ) {
        float fuse = entity.getClientFuseTime(tickDelta);

        float wobble =
                1.0F + MathHelper.cos(fuse * 100.0F) * 0.05F;

        matrices.scale(wobble, wobble, wobble);

        float progress = MathHelper.clamp(fuse, 0.0F, 1F);

        progress *= progress;
        progress *= progress;

        float horizontalScale = 1.0F + progress * 0.9F;
        float verticalScale = 1.0F + progress * 0.9F;

        matrices.scale(
                horizontalScale,
                verticalScale,
                horizontalScale
        );
    }

    @Override
    public void render(
            DoomCreeperEntity entity,
            float yaw,
            float tickDelta,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light
    ) {
        super.render(
                entity,
                yaw,
                tickDelta,
                matrices,
                vertexConsumers,
                light
        );

        renderBeam(
                entity,
                matrices,
                vertexConsumers,
                tickDelta
        );
    }

    private void renderBeam(
            DoomCreeperEntity entity,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            float tickDelta
    ) {
        if (!entity.isBeamActive() || entity.getBeamTarget() == null) {
            beamVisualTargets.remove(entity.getId());
            return;
        }

        VertexConsumer vertexConsumer =
                vertexConsumers.getBuffer(
                        RenderLayer.getEntityTranslucent(BEAM_TEXTURE)
                );

        Vec3d start = new Vec3d(0.0, 1.4, 0.0);

        Vec3d newTarget = entity.getBeamTarget().getLerpedPos(tickDelta)
                .add(0.0, entity.getBeamTarget().getHeight() * 0.5, 0.0)
                .subtract(entity.getLerpedPos(tickDelta));

        Vec3d beamVisualTarget = beamVisualTargets.get(entity.getId());

        if (beamVisualTarget == null) {
            beamVisualTarget = newTarget;
        }

        beamVisualTarget = beamVisualTarget.lerp(newTarget, 0.25);

        beamVisualTargets.put(entity.getId(), beamVisualTarget);

        Vec3d target = beamVisualTarget;

        Vec3d direction = target.subtract(start);
        double length = direction.length();

        if (length <= 0.01) return;

        direction = direction.normalize();

        Vec3d up = new Vec3d(0, 1, 0);
        if (Math.abs(direction.dotProduct(up)) > 0.95) {
            up = new Vec3d(1, 0, 0);
        }

        Vec3d side = direction.crossProduct(up).normalize();
        Vec3d side2 = direction.crossProduct(side).normalize();

        float width = 0.05F;

        Vec3d p1 = start
                .add(side.multiply(width))
                .add(side2.multiply(width));

        Vec3d p2 = start
                .subtract(side.multiply(width))
                .add(side2.multiply(width));

        Vec3d p3 = start
                .subtract(side.multiply(width))
                .subtract(side2.multiply(width));

        Vec3d p4 = start
                .add(side.multiply(width))
                .subtract(side2.multiply(width));

        Vec3d end = start.add(direction.multiply(length));

        Vec3d e1 = end
                .add(side.multiply(width))
                .add(side2.multiply(width));

        Vec3d e2 = end
                .subtract(side.multiply(width))
                .add(side2.multiply(width));

        Vec3d e3 = end
                .subtract(side.multiply(width))
                .subtract(side2.multiply(width));

        Vec3d e4 = end
                .add(side.multiply(width))
                .subtract(side2.multiply(width));

        int beamLight = 15728880;

        vertex(vertexConsumer, matrices, p1, 0F, 0F, beamLight);
        vertex(vertexConsumer, matrices, p2, 1F, 0F, beamLight);
        vertex(vertexConsumer, matrices, e2, 1F, 1F, beamLight);
        vertex(vertexConsumer, matrices, e1, 0F, 1F, beamLight);

        vertex(vertexConsumer, matrices, p2, 0F, 0F, beamLight);
        vertex(vertexConsumer, matrices, p3, 1F, 0F, beamLight);
        vertex(vertexConsumer, matrices, e3, 1F, 1F, beamLight);
        vertex(vertexConsumer, matrices, e2, 0F, 1F, beamLight);

        vertex(vertexConsumer, matrices, p3, 0F, 0F, beamLight);
        vertex(vertexConsumer, matrices, p4, 1F, 0F, beamLight);
        vertex(vertexConsumer, matrices, e4, 1F, 1F, beamLight);
        vertex(vertexConsumer, matrices, e3, 0F, 1F, beamLight);

        vertex(vertexConsumer, matrices, p4, 0F, 0F, beamLight);
        vertex(vertexConsumer, matrices, p1, 1F, 0F, beamLight);
        vertex(vertexConsumer, matrices, e1, 1F, 1F, beamLight);
        vertex(vertexConsumer, matrices, e4, 0F, 1F, beamLight);
    }
private void vertex(
        VertexConsumer consumer,
        MatrixStack matrices,
        Vec3d pos,
        float u,
        float v,
        int light
) {
    var matrix = matrices.peek().getPositionMatrix();

    consumer.vertex(
                    matrix,
                    (float) pos.x,
                    (float) pos.y,
                    (float) pos.z
            )
            .color(255, 255, 255, 255)
            .texture(u, v)
            .overlay(0, 10)
            .light(light)
            .normal(0, 1, 0)
            .next();
    }

    @Override
    public boolean shouldRender(
            DoomCreeperEntity entity,
            Frustum frustum,
            double x,
            double y,
            double z
    ) {
        if (entity.isBeamActive() && entity.getBeamTarget() != null) {
            return true;
        }

        return super.shouldRender(entity, frustum, x, y, z);
    }
}