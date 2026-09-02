package dev.hellmod.client.Renderer;

import dev.hellmod.client.model.DoomSkeletonModel;
import dev.hellmod.entity.DoomSkeletonEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.util.Identifier;

public class DoomSkeletonRenderer extends MobEntityRenderer<DoomSkeletonEntity, DoomSkeletonModel<DoomSkeletonEntity>> {

    public DoomSkeletonRenderer(EntityRendererFactory.Context context) {
        super(
                context,
                new DoomSkeletonModel<>(
                        context.getPart(DoomSkeletonModel.LAYER)
                ),
                0.5f
        );

        this.addFeature(
                new HeldItemFeatureRenderer<>(
                        this,
                        context.getHeldItemRenderer()
                )
        );
    }

    @Override
    public Identifier getTexture(DoomSkeletonEntity entity) {
        return Identifier.of(
                "hellmod",
                "textures/entity/doom_skeleton.png"
        );
    }
}