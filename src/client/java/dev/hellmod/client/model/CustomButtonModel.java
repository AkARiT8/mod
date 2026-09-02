package dev.hellmod.client.model;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class CustomButtonModel<T extends Entity> extends EntityModel<T> {

	public static final EntityModelLayer LAYER_LOCATION =
			new EntityModelLayer(
					new Identifier("hellmod", "custom_button"),
					"main"
			);

	private final ModelPart BUTTON;
	private final ModelPart BASE;

	public CustomButtonModel(ModelPart root) {
		this.BUTTON = root.getChild("BUTTON");
		this.BASE = root.getChild("BASE");
	}

	public static TexturedModelData getTexturedModelData() {

		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		ModelPartData BUTTON = root.addChild(
				"BUTTON",

				ModelPartBuilder.create()

						.uv(0, 14)
						.cuboid(
								-5.0F, 0.0F, -3.0F,
								8.0F, 4.0F, 8.0F
						)

						.uv(0, 26)
						.cuboid(
								-4.0F, -1.0F, -2.0F,
								6.0F, 1.0F, 6.0F
						)

						.uv(32, 14)
						.cuboid(
								-3.0F, -1.0F, 4.0F,
								4.0F, 1.0F, 1.0F
						)

						.uv(32, 16)
						.cuboid(
								-3.0F, -1.0F, -3.0F,
								4.0F, 1.0F, 1.0F
						)

						.uv(24, 26)
						.cuboid(
								-5.0F, -1.0F, -1.0F,
								1.0F, 1.0F, 4.0F
						)

						.uv(24, 31)
						.cuboid(
								2.0F, -1.0F, -1.0F,
								1.0F, 1.0F, 4.0F
						),

				ModelTransform.pivot(
						2.0F,
						18.0F,
						-2.0F
				)
		);

		ModelPartData BASE = root.addChild(
				"BASE",

				ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(
								-12.0F, -4.0F, 2.0F,
								10.0F, 4.0F, 10.0F
						),

				ModelTransform.pivot(
						8.0F,
						24.0F,
						-8.0F
				)
		);

		return TexturedModelData.of(
				modelData,
				64,
				64
		);
	}

	@Override
	public void setAngles(
			T entity,
			float limbAngle,
			float limbDistance,
			float animationProgress,
			float headYaw,
			float headPitch
	) {
	}

	@Override
	public void render(
			MatrixStack matrices,
			VertexConsumer vertices,
			int light,
			int overlay,
			float red,
			float green,
			float blue,
			float alpha
	) {

		BUTTON.render(
				matrices,
				vertices,
				light,
				overlay,
				red,
				green,
				blue,
				alpha
		);

		BASE.render(
				matrices,
				vertices,
				light,
				overlay,
				red,
				green,
				blue,
				alpha
		);
	}

	public void renderButton(
			MatrixStack matrices,
			VertexConsumer vertices,
			int light,
			int overlay,
			float red,
			float green,
			float blue,
			float alpha
	) {

		BUTTON.render(
				matrices,
				vertices,
				light,
				overlay,
				red,
				green,
				blue,
				alpha
		);
	}

	public void renderBase(
			MatrixStack matrices,
			VertexConsumer vertices,
			int light,
			int overlay,
			float red,
			float green,
			float blue,
			float alpha
	) {

		BASE.render(
				matrices,
				vertices,
				light,
				overlay,
				red,
				green,
				blue,
				alpha
		);
	}
}