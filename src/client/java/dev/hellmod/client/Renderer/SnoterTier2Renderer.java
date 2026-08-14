package dev.hellmod.client.Renderer;

import dev.hellmod.client.model.SnoterModelTier2;
import dev.hellmod.entity.SnoterTier2Entity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SnoterTier2Renderer extends MobEntityRenderer<SnoterTier2Entity, SnoterModelTier2> {

    public SnoterTier2Renderer(EntityRendererFactory.Context context) {
        super(
                context,
                new SnoterModelTier2(
                        context.getPart(
                                SnoterModelTier2.LAYER_LOCATION
                        )
                ),
                0.4f
        );
    }

    @Override
    public Identifier getTexture(SnoterTier2Entity entity) {
        return new Identifier(
                "hellmod",
                "textures/entity/snoter_tier_2.png"
        );
    }
}