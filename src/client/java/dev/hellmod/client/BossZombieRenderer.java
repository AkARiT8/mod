package dev.hellmod.client;

import dev.hellmod.entity.BossZombieEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;

public class BossZombieRenderer extends ZombieEntityRenderer {

    private static final float SCALE = 1.8f;

    public BossZombieRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius *= SCALE;
    }

    @Override
    protected void scale(ZombieEntity entity, MatrixStack matrices, float amount) {
        matrices.scale(1.8f, 1.8f, 1.8f);
    }
}