package dev.hellmod.client.Renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.WitherSkeletonEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.WitherSkeletonEntity;

public class InfernalKnightRenderer extends WitherSkeletonEntityRenderer {

    public InfernalKnightRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    protected void scale(
            WitherSkeletonEntity entity,
            MatrixStack matrices,
            float tickDelta) {

        matrices.scale(1.5f, 1.5f, 1.5f);
    }
}