package dev.hellmod.client.Renderer;

import dev.hellmod.client.model.DoomZombieModel;
import dev.hellmod.entity.DoomZombieEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.util.Identifier;

public class DoomZombieRenderer
        extends MobEntityRenderer<DoomZombieEntity, DoomZombieModel> {

    public DoomZombieRenderer(EntityRendererFactory.Context context) {
        super(
                context,
                new DoomZombieModel(
                        context.getPart(DoomZombieModel.LAYER_LOCATION)
                ),
                0.5F
        );

        this.addFeature(new HeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()));
    }

    @Override
    public Identifier getTexture(DoomZombieEntity entity) {
        return Identifier.of(
                "hellmod",
                "textures/entity/doom_zombie.png"
        );
    }
}