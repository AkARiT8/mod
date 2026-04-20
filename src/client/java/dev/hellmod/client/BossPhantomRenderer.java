package dev.hellmod.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PhantomEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.PhantomEntity;

public class BossPhantomRenderer extends PhantomEntityRenderer {

    private static final float SCALE = 3.0f;

    public BossPhantomRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius *= SCALE;
    }

    @Override
    protected void scale(PhantomEntity entity, MatrixStack matrices, float amount) {
        matrices.scale(SCALE, SCALE, SCALE);
        super.scale(entity, matrices, amount);
    }
}