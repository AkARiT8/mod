package dev.hellmod.client.model;

import dev.hellmod.entity.SnotProjectileEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SnotProjectileModel extends EntityModel<SnotProjectileEntity> {

    public static final EntityModelLayer LAYER_LOCATION =
            new EntityModelLayer(
                    Identifier.of("hellmod", "snot_projectile"),
                    "main"
            );

    private final ModelPart SNOTPROYECTILE;

    public SnotProjectileModel(ModelPart root) {
        this.SNOTPROYECTILE =
                root.getChild("SNOTPROYECTILE");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData SNOTPROYECTILE = modelPartData.addChild("SNOTPROYECTILE", ModelPartBuilder.create().uv(8, 18).cuboid(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0F, 2F, 0F));

        ModelPartData ESTELA = SNOTPROYECTILE.addChild("ESTELA", ModelPartBuilder.create().uv(12, 6).cuboid(-4.0F, -4.0F, 0.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(-6.0F, -2.0F, 0.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(0, 6).cuboid(-2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(12, 0).cuboid(-5.0F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(12, 11).cuboid(-2.0F, -1.0F, 1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, 1.5F, -0.5F));

        ModelPartData SMOOTH = SNOTPROYECTILE.addChild("SMOOTH", ModelPartBuilder.create().uv(12, 15).cuboid(-1.0F, -2.0F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 17).cuboid(-2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 18).cuboid(0.0F, -1.0F, 1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 18).cuboid(-2.0F, -2.0F, 1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 0.5F, 0.5F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(
            SnotProjectileEntity entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {

//        this.SNOTPROYECTILE.roll =
//                ageInTicks * 0.35F;
//
//        this.SNOTPROYECTILE.pivotY =
//                24.0F +
//                        (float)Math.sin(
//                                ageInTicks * 0.5F
//                        ) * 0.15F;
    }

    @Override
    public void render(
            MatrixStack matrices,
            VertexConsumer vertexConsumer,
            int light,
            int overlay,
            float red,
            float green,
            float blue,
            float alpha
    ) {

        SNOTPROYECTILE.render(
                matrices,
                vertexConsumer,
                light,
                overlay,
                red,
                green,
                blue,
                alpha
        );
    }
}