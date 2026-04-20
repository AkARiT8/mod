package dev.hellmod.client;

import dev.hellmod.entity.BossCreeperEntity;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.CreeperEntity;

public class BossCreeperRenderer extends CreeperEntityRenderer {

    private static final float SCALE = 5f;

    public BossCreeperRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius *= SCALE;
    }

    @Override
    protected void scale(CreeperEntity entity, MatrixStack matrices, float amount) {
        matrices.scale(SCALE, SCALE, SCALE);
        super.scale(entity, matrices, amount);
    }
}