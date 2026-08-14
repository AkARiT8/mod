package dev.hellmod.client.mixin;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntityRenderer.class)
public interface PlayerRendererFeatureAccessor<
        T extends LivingEntity,
        M extends EntityModel<T>> {

    @Invoker("addFeature")
    boolean invokeAddFeature(FeatureRenderer<T, M> feature);
}