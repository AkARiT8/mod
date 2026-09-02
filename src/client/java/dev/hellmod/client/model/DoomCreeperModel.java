package dev.hellmod.client.model;

import dev.hellmod.entity.DoomCreeperEntity;
import net.minecraft.client.model.Dilation;
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
import net.minecraft.util.math.MathHelper;

public class DoomCreeperModel extends EntityModel<DoomCreeperEntity> {

	public static final EntityModelLayer LAYER_LOCATION =
			new EntityModelLayer(
					Identifier.of("hellmod", "doom_creeper"),
					"main"
			);

	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart left_tusk;
	private final ModelPart right_tusk;
	private final ModelPart top_tusk;
	private final ModelPart extra;
	private final ModelPart body;
	private final ModelPart extra_body;
	private final ModelPart left_front_leg;
	private final ModelPart left_back_leg;
	private final ModelPart right_front_leg;
	private final ModelPart right_back_leg;

	public DoomCreeperModel(ModelPart root) {

		this.root = root.getChild("root");

		this.head = this.root.getChild("head");
		this.left_tusk = this.head.getChild("left_tusk");
		this.right_tusk = this.head.getChild("right_tusk");
		this.top_tusk = this.head.getChild("top_tusk");
		this.extra = this.head.getChild("extra");

		this.body = this.root.getChild("body");
		this.extra_body = this.body.getChild("extra_body");

		this.left_front_leg = this.root.getChild("left_front_leg");
		this.left_back_leg = this.root.getChild("left_back_leg");
		this.right_front_leg = this.root.getChild("right_front_leg");
		this.right_back_leg = this.root.getChild("right_back_leg");
	}

	public static TexturedModelData getTexturedModelData() {

		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData root = modelPartData.addChild(
				"root",
				ModelPartBuilder.create(),
				ModelTransform.of(
						0.0F, 13.0F, 0.0F,
						0.0F, (float) Math.PI, 0.0F
				)
		);

		ModelPartData head = root.addChild(
				"head",
				ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(
								-4.0F, -4.0F, -4.0F,
								8.0F, 8.0F, 8.0F,
								new Dilation(0.0F)
						),
				ModelTransform.pivot(0.0F, -10.0F, 0.0F)
		);

		ModelPartData left_tusk = head.addChild(
				"left_tusk",
				ModelPartBuilder.create()
						.uv(34, 36)
						.cuboid(-3.0F, -4.0F, 1.0F, 1.0F, 3.0F, 6.0F)
						.uv(0, 50)
						.cuboid(-4.0F, -3.0F, 2.0F, 1.0F, 2.0F, 4.0F)
						.uv(40, 28)
						.cuboid(-3.0F, -5.0F, -2.0F, 1.0F, 1.0F, 6.0F)
						.uv(54, 16)
						.cuboid(-3.0F, -4.0F, -2.0F, 1.0F, 3.0F, 3.0F)
						.uv(54, 6)
						.cuboid(-3.0F, -6.0F, -3.0F, 1.0F, 1.0F, 4.0F)
						.uv(10, 50)
						.cuboid(-3.0F, -5.0F, -3.0F, 1.0F, 3.0F, 1.0F)
						.uv(44, 45)
						.cuboid(-4.0F, -4.0F, 0.0F, 1.0F, 1.0F, 5.0F)
						.uv(42, 57)
						.cuboid(-4.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F)
						.uv(48, 6)
						.cuboid(-2.0F, -3.0F, 6.0F, 1.0F, 2.0F, 2.0F)
						.uv(26, 49)
						.cuboid(-1.0F, -3.0F, 7.0F, 1.0F, 1.0F, 1.0F),
				ModelTransform.pivot(-2.0F, 4.0F, 0.0F)
		);

		ModelPartData right_tusk = head.addChild(
				"right_tusk",
				ModelPartBuilder.create()
						.uv(40, 10)
						.cuboid(2.0F, -4.0F, 1.0F, 1.0F, 3.0F, 6.0F)
						.uv(26, 51)
						.cuboid(3.0F, -3.0F, 2.0F, 1.0F, 2.0F, 4.0F)
						.uv(32, 10)
						.cuboid(2.0F, -5.0F, -2.0F, 1.0F, 3.0F, 3.0F)
						.uv(16, 31)
						.cuboid(2.0F, -5.0F, 1.0F, 1.0F, 1.0F, 3.0F)
						.uv(54, 11)
						.cuboid(2.0F, -6.0F, -3.0F, 1.0F, 1.0F, 4.0F)
						.uv(6, 56)
						.cuboid(2.0F, -5.0F, -3.0F, 1.0F, 3.0F, 1.0F)
						.uv(48, 0)
						.cuboid(3.0F, -4.0F, 0.0F, 1.0F, 1.0F, 5.0F)
						.uv(48, 57)
						.cuboid(3.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F)
						.uv(18, 55)
						.cuboid(1.0F, -3.0F, 6.0F, 1.0F, 2.0F, 2.0F)
						.uv(10, 58)
						.cuboid(0.0F, -3.0F, 7.0F, 1.0F, 1.0F, 1.0F),
				ModelTransform.pivot(2.0F, 4.0F, 0.0F)
		);

		ModelPartData top_tusk = head.addChild(
				"top_tusk",
				ModelPartBuilder.create()
						.uv(14, 44)
						.cuboid(-2.0F, -3.0F, 3.0F, 4.0F, 1.0F, 4.0F)
						.uv(16, 36)
						.cuboid(-1.0F, -4.0F, 4.0F, 2.0F, 1.0F, 7.0F)
						.uv(30, 45)
						.cuboid(-1.0F, -5.0F, 5.0F, 2.0F, 1.0F, 5.0F)
						.uv(48, 35)
						.cuboid(-2.0F, -4.0F, 5.0F, 1.0F, 1.0F, 5.0F)
						.uv(14, 49)
						.cuboid(1.0F, -4.0F, 5.0F, 1.0F, 1.0F, 5.0F)
						.uv(48, 41)
						.cuboid(-1.0F, -3.0F, 10.0F, 2.0F, 1.0F, 2.0F)
						.uv(54, 57)
						.cuboid(-1.0F, -2.0F, 11.0F, 2.0F, 1.0F, 1.0F),
				ModelTransform.pivot(0.0F, -2.0F, -4.0F)
		);

		ModelPartData extra = head.addChild(
				"extra",
				ModelPartBuilder.create()
						.uv(54, 22)
						.cuboid(-5.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F)
						.uv(54, 27)
						.cuboid(4.0F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F)
						.uv(54, 32)
						.cuboid(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F)
						.uv(10, 55)
						.cuboid(2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F),
				ModelTransform.pivot(0.0F, -4.0F, 0.0F)
		);

		ModelPartData body = root.addChild(
				"body",
				ModelPartBuilder.create()
						.uv(0, 16)
						.cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 11.0F, 4.0F),
				ModelTransform.pivot(0.0F, -6.0F, 0.0F)
		);

		ModelPartData extra_body = body.addChild(
				"extra_body",
				ModelPartBuilder.create()
						.uv(36, 51)
						.cuboid(-2.0F, -9.0F, 2.0F, 4.0F, 5.0F, 1.0F)
						.uv(46, 51)
						.cuboid(-2.0F, -9.0F, -3.0F, 4.0F, 5.0F, 1.0F)
						.uv(0, 56)
						.cuboid(-4.0F, -8.0F, -3.0F, 2.0F, 3.0F, 1.0F)
						.uv(56, 49)
						.cuboid(2.0F, -8.0F, -3.0F, 2.0F, 3.0F, 1.0F)
						.uv(56, 41)
						.cuboid(-4.0F, -8.0F, 2.0F, 2.0F, 3.0F, 1.0F)
						.uv(56, 53)
						.cuboid(-1.0F, -12.0F, 2.0F, 2.0F, 3.0F, 1.0F)
						.uv(24, 57)
						.cuboid(-1.0F, -4.0F, 2.0F, 2.0F, 3.0F, 1.0F)
						.uv(30, 57)
						.cuboid(-1.0F, -4.0F, -3.0F, 2.0F, 3.0F, 1.0F)
						.uv(36, 57)
						.cuboid(-1.0F, -12.0F, -3.0F, 2.0F, 3.0F, 1.0F)
						.uv(56, 45)
						.cuboid(2.0F, -8.0F, 2.0F, 2.0F, 3.0F, 1.0F)
						.uv(40, 19)
						.cuboid(-5.0F, -8.0F, -3.0F, 1.0F, 3.0F, 6.0F)
						.uv(0, 41)
						.cuboid(4.0F, -8.0F, -3.0F, 1.0F, 3.0F, 6.0F),
				ModelTransform.pivot(0.0F, 12.0F, 0.0F)
		);

		ModelPartData left_front_leg = root.addChild(
				"left_front_leg",
				ModelPartBuilder.create()
						.uv(24, 16)
						.cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 6.0F, 4.0F),
				ModelTransform.pivot(-2.0F, 5.0F, 2.0F)
		);

		ModelPartData left_back_leg = root.addChild(
				"left_back_leg",
				ModelPartBuilder.create()
						.uv(24, 26)
						.cuboid(-2.0F, 0.0F, -4.0F, 4.0F, 6.0F, 4.0F),
				ModelTransform.pivot(-2.0F, 5.0F, -2.0F)
		);

		ModelPartData right_front_leg = root.addChild(
				"right_front_leg",
				ModelPartBuilder.create()
						.uv(0, 31)
						.cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 6.0F, 4.0F),
				ModelTransform.pivot(2.0F, 5.0F, 2.0F)
		);

		ModelPartData right_back_leg = root.addChild(
				"right_back_leg",
				ModelPartBuilder.create()
						.uv(32, 0)
						.cuboid(-2.0F, 0.0F, -4.0F, 4.0F, 6.0F, 4.0F),
				ModelTransform.pivot(2.0F, 5.0F, -2.0F)
		);

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(
			DoomCreeperEntity entity,
			float limbAngle,
			float limbDistance,
			float animationProgress,
			float headYaw,
			float headPitch
	) {
		head.yaw = headYaw * (float) Math.PI / 180F;
		head.pitch = -headPitch * (float) Math.PI / 180F;

		float swing =
				(float) Math.cos(limbAngle * 0.6662F)
						* limbDistance;

		left_front_leg.pitch = swing;
		right_back_leg.pitch = swing;

		right_front_leg.pitch = -swing;
		left_back_leg.pitch = -swing;
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
		root.render(
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