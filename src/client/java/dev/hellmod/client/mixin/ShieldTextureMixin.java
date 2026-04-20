package dev.hellmod.client.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import dev.hellmod.items.ModItems;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltinModelItemRenderer.class)
public class ShieldTextureMixin {

    @Unique
    private ShieldEntityModel hellmod$shieldModel;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void hellmod$renderCustomShield(ItemStack stack,
                                            net.minecraft.client.render.model.json.ModelTransformationMode mode,
                                            MatrixStack matrices,
                                            VertexConsumerProvider vertexConsumers,
                                            int light,
                                            int overlay,
                                            CallbackInfo ci) {


        if (!stack.isOf(ModItems.TRUE_AMETHYST_SHIELD)) return;

        MinecraftClient client = MinecraftClient.getInstance();

        if (hellmod$shieldModel == null) {
            hellmod$shieldModel = new ShieldEntityModel(
                    client.getEntityModelLoader().getModelPart(EntityModelLayers.SHIELD)
            );
        }

        matrices.push();
        matrices.scale(1.0F, -1.0F, -1.0F);

        Identifier texture = new Identifier(
                "hellmod",
                "textures/entity/true_amethyst_shield_base_nopattern.png"
        );

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
                RenderLayer.getEntityCutoutNoCull(texture)
        );

        hellmod$shieldModel.getHandle().render(matrices, vertexConsumer, light, overlay);
        hellmod$shieldModel.getPlate().render(matrices, vertexConsumer, light, overlay);

        matrices.pop();

        ci.cancel();
    }
}