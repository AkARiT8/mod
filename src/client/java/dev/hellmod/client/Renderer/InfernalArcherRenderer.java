package dev.hellmod.client.Renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.WitherSkeletonEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.WitherSkeletonEntity;

public class InfernalArcherRenderer extends WitherSkeletonEntityRenderer {

    public InfernalArcherRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    protected void scale(
            WitherSkeletonEntity entity,
            MatrixStack matrices,
            float tickDelta) {

        matrices.scale(0.85f, 0.85f, 0.85f);
    }
}