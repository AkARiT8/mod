package dev.hellmod.client.Renderer;

import dev.hellmod.client.model.SnoterModel;
import dev.hellmod.entity.SnoterEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SnoterRenderer
        extends MobEntityRenderer<
        SnoterEntity,
        SnoterModel> {

    public SnoterRenderer(
            EntityRendererFactory.Context context
    ) {
        super(
                context,
                new SnoterModel(
                        context.getPart(
                                SnoterModel.LAYER_LOCATION
                        )
                ),
                0.4f
        );
    }


    @Override
    public Identifier getTexture(SnoterEntity entity) {

        return new Identifier(
                "hellmod",
                "textures/entity/snoter.png"
        );
    }
}