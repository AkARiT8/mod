package dev.hellmod.client.model;

import dev.hellmod.entity.SnoterEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SnoterModel extends EntityModel<SnoterEntity> {

	public static final EntityModelLayer LAYER_LOCATION =
			new EntityModelLayer(
					Identifier.of("hellmod", "snoter"),
					"main"
			);

	private final ModelPart SNOTER;
	private final ModelPart CUERPOR;
	private final ModelPart CUERPOR2;
	private final ModelPart OJOS;
	private final ModelPart BOCA;

	public SnoterModel(ModelPart root) {

		this.SNOTER = root.getChild("SNOTER");

		this.CUERPOR = this.SNOTER.getChild("CUERPOR");
		this.CUERPOR2 = this.SNOTER.getChild("CUERPOR2");
		this.OJOS = this.SNOTER.getChild("OJOS");
		this.BOCA = this.SNOTER.getChild("BOCA");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData SNOTER = modelPartData.addChild("SNOTER", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -8.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData CUERPOR = SNOTER.addChild("CUERPOR", ModelPartBuilder.create().uv(6, 27).cuboid(11.0F, -4.0F, -1.0F, 1.0F, 6.0F, 2.0F, new Dilation(0.0F))
				.uv(7, 28).cuboid(11.0F, -4.0F, 1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
				.uv(7, 28).cuboid(11.0F, 2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(7, 28).cuboid(11.0F, -4.0F, -2.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, -4.0F, 0.0F));

		ModelPartData CUERPOR2 = SNOTER.addChild("CUERPOR2", ModelPartBuilder.create().uv(6, 27).cuboid(11.0F, -4.0F, -1.0F, 1.0F, 6.0F, 2.0F, new Dilation(0.0F))
				.uv(7, 28).cuboid(11.0F, -4.0F, 1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
				.uv(7, 28).cuboid(11.0F, 2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(7, 28).cuboid(11.0F, -4.0F, -2.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-17.0F, -4.0F, 0.0F));

		ModelPartData OJOS = SNOTER.addChild("OJOS", ModelPartBuilder.create().uv(22, 27).cuboid(0.0F, -1.0F, -1.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
				.uv(12, 27).cuboid(-5.0F, -1.0F, -1.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 20).cuboid(-8.0F, 2.0F, -3.0F, 14.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -11.0F, 0.0F));

		ModelPartData BOCA = SNOTER.addChild("BOCA", ModelPartBuilder.create().uv(22, 32).cuboid(-3.0F, 5.5F, 0.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(32, 31).cuboid(-3.0F, 2.5F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(32, 27).cuboid(0.0F, 2.5F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(12, 32).cuboid(-3.0F, 1.5F, 0.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -7.0F, 5.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(
			SnoterEntity entity,
			float limbSwing,
			float limbSwingAmount,
			float ageInTicks,
			float netHeadYaw,
			float headPitch
	) {

		this.SNOTER.pivotY =
				24.0F +
						(float)Math.sin(
								ageInTicks * 0.15F
						) * 1.5F;

		this.SNOTER.roll =
				(float)Math.sin(
						ageInTicks * 0.10F
				) * 0.08F;
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

		SNOTER.render(
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