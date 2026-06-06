package dev.hellmod.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;


public class AirshipModel extends EntityModel<Entity> {


	public static final EntityModelLayer LAYER_LOCATION =
			new EntityModelLayer(
					new Identifier("hellmod", "airship"),
					"main"
			);

	private final ModelPart CABINA_MORRO;
	public AirshipModel(ModelPart root) {
		this.CABINA_MORRO = root.getChild("CABINA_MORRO");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData CABINA_MORRO = modelPartData.addChild("CABINA_MORRO", ModelPartBuilder.create().uv(5, 20).cuboid(-3.0F, 0.0F, 7.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(40, 56).cuboid(-4.0F, 2.0F, 9.0F, 8.0F, 3.0F, 3.0F, new Dilation(0.0F))
				.uv(14, 25).cuboid(-10.0F, -3.0F, -2.0F, 20.0F, 8.0F, 5.0F, new Dilation(0.0F))
				.uv(0, 42).cuboid(-9.0F, -2.0F, 3.0F, 18.0F, 7.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 39).cuboid(-8.0F, -1.0F, 5.0F, 16.0F, 6.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 46).cuboid(-6.0F, 1.0F, 7.0F, 12.0F, 4.0F, 2.0F, new Dilation(0.0F))
				.uv(14, 58).cuboid(-2.0F, 3.0F, 14.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(9, 33).cuboid(-1.0F, 4.0F, 15.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 20).cuboid(-8.0F, -6.0F, -6.0F, 16.0F, 1.0F, 10.0F, new Dilation(0.0F))
				.uv(8, 32).cuboid(-6.0F, -5.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(8, 32).cuboid(4.0F, -5.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-13.0F, -4.0F, -7.0F, 26.0F, 9.0F, 5.0F, new Dilation(0.0F))
				.uv(26, 43).cuboid(-7.0F, -3.0F, -12.0F, 14.0F, 7.0F, 5.0F, new Dilation(0.0F))
				.uv(8, 31).cuboid(-2.0F, 0.0F, -17.0F, 4.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 57).cuboid(-2.0F, -2.0F, -14.0F, 4.0F, 5.0F, 2.0F, new Dilation(0.0F))
				.uv(42, 57).cuboid(-3.0F, 3.0F, 12.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(9, 19).cuboid(-3.0F, -4.0F, 5.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
				.uv(9, 19).cuboid(2.0F, -4.0F, 5.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
				.uv(5, 20).cuboid(-3.0F, -4.0F, 4.0F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(4, 19).cuboid(-3.0F, -5.0F, 5.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData CUERPO_MAIN_r1 = CABINA_MORRO.addChild("CUERPO_MAIN_r1", ModelPartBuilder.create().uv(22, 14).cuboid(-2.0F, -0.6F, -10.0F, 13.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-18.0F, -4.0F, 5.0F, 0.0F, 0.0F, -0.1309F));

		ModelPartData CUERPO_MAIN_r2 = CABINA_MORRO.addChild("CUERPO_MAIN_r2", ModelPartBuilder.create().uv(22, 14).cuboid(-2.0F, -0.85F, -10.0F, 13.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(9.0F, -5.0F, 5.0F, 0.0F, 0.0F, 0.0873F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		CABINA_MORRO.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}