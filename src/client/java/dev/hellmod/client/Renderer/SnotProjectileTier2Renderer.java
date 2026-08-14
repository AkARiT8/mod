package dev.hellmod.client.Renderer;

import dev.hellmod.entity.SnotProjectileEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class SnotProjectileTier2Renderer extends SnotProjectileRenderer{
    public SnotProjectileTier2Renderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(
            SnotProjectileEntity entity
    ) {
        return Identifier.of(
                "hellmod",
                "textures/entity/snot_projectile.png" //cambio
        );
    }
}
