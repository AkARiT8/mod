package dev.hellmod.client.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class DoomSkeletonModel<T extends MobEntity & RangedAttackMob>
		extends SkeletonEntityModel<T> {

	public static final EntityModelLayer LAYER =
			new EntityModelLayer(
					Identifier.of("hellmod", "doom_skeleton"),
					"main"
			);

	public static final EntityModelLayer LAYER_LOCATION = LAYER;

	private final ModelPart leftArm;
	private final ModelPart rightArm;

	public DoomSkeletonModel(ModelPart root) {
		super(root);

		this.leftArm = root.getChild("left_arm");
		this.rightArm = root.getChild("right_arm");
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
		super.setAngles(
				entity,
				limbAngle,
				limbDistance,
				animationProgress,
				headYaw,
				headPitch
		);

		boolean hasBow =
				entity.getMainHandStack().getItem() instanceof BowItem;

		boolean isAttacking =
				entity.isAttacking();

		if (hasBow && isAttacking) {

			this.leftArm.pitch = -1.50F;
			this.leftArm.yaw = 0.45F;
			this.leftArm.roll = 0.0F;

			this.rightArm.pitch = -1.57F;
			this.rightArm.yaw = -0.25F;
			this.rightArm.roll = 0.0F;
		}
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		root.addChild(
				"hat",
				ModelPartBuilder.create(),
				ModelTransform.pivot(0.0F, 24.0F, 0.0F)
		);

		root.addChild(
				"left_leg",
				ModelPartBuilder.create()
						.uv(8, 20)
						.cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, Dilation.NONE)
						.uv(26, 40)
						.cuboid(-1.0F, 2.0F, -2.0F, 2.0F, 6.0F, 1.0F, Dilation.NONE),
				ModelTransform.pivot(2.0F, 29.0F, 0.0F)
		);

		root.addChild(
				"right_leg",
				ModelPartBuilder.create()
						.uv(0, 20)
						.cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, Dilation.NONE)
						.uv(32, 40)
						.cuboid(-1.0F, 2.0F, -2.0F, 2.0F, 6.0F, 1.0F, Dilation.NONE),
				ModelTransform.pivot(-2.0F, 29.0F, 0.0F)
		);

		root.addChild(
				"body",
				ModelPartBuilder.create()
						.uv(8, 34)
						.cuboid(-4.0F, 4.0F, -2.0F, 0.0F, 1.0F, 4.0F, Dilation.NONE)
						.uv(38, 5)
						.cuboid(4.0F, 4.0F, -2.0F, 0.0F, 1.0F, 4.0F, Dilation.NONE)
						.uv(34, 30)
						.cuboid(-4.0F, 2.0F, -2.0F, 0.0F, 1.0F, 4.0F, Dilation.NONE)
						.uv(14, 42)
						.cuboid(4.0F, 3.0F, -1.0F, 0.0F, 1.0F, 2.0F, Dilation.NONE)
						.uv(18, 44)
						.cuboid(4.0F, 1.0F, -1.0F, 0.0F, 1.0F, 2.0F, Dilation.NONE)
						.uv(42, 34)
						.cuboid(-4.0F, 1.0F, -1.0F, 0.0F, 1.0F, 2.0F, Dilation.NONE)
						.uv(42, 37)
						.cuboid(-4.0F, 3.0F, -1.0F, 0.0F, 1.0F, 2.0F, Dilation.NONE)
						.uv(38, 0)
						.cuboid(4.0F, 2.0F, -2.0F, 0.0F, 1.0F, 4.0F, Dilation.NONE)
						.uv(34, 35)
						.cuboid(-4.0F, 0.0F, -2.0F, 0.0F, 1.0F, 4.0F, Dilation.NONE)
						.uv(28, 20)
						.cuboid(-4.0F, 0.0F, 2.0F, 8.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(28, 21)
						.cuboid(-4.0F, 2.0F, 2.0F, 8.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(28, 23)
						.cuboid(-4.0F, 2.0F, -2.0F, 8.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(40, 13)
						.cuboid(-3.0F, 5.0F, -2.0F, 1.0F, 2.0F, 0.0F, Dilation.NONE)
						.uv(12, 47)
						.cuboid(2.0F, 5.0F, -2.0F, 1.0F, 2.0F, 0.0F, Dilation.NONE)
						.uv(8, 48)
						.cuboid(1.0F, 3.0F, -2.0F, 1.0F, 2.0F, 0.0F, Dilation.NONE)
						.uv(10, 48)
						.cuboid(-2.0F, 3.0F, -2.0F, 1.0F, 2.0F, 0.0F, Dilation.NONE)
						.uv(8, 47)
						.cuboid(2.0F, 0.0F, -2.0F, 2.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(26, 39)
						.cuboid(-4.0F, 4.0F, -2.0F, 1.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(14, 48)
						.cuboid(-3.0F, 3.0F, -2.0F, 1.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(48, 14)
						.cuboid(2.0F, 3.0F, -2.0F, 1.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(22, 47)
						.cuboid(-3.0F, 1.0F, -2.0F, 2.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(26, 47)
						.cuboid(1.0F, 1.0F, -2.0F, 2.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(40, 15)
						.cuboid(3.0F, 4.0F, -2.0F, 1.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(42, 45)
						.cuboid(1.0F, 6.0F, -2.0F, 1.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(48, 13)
						.cuboid(-2.0F, 6.0F, -2.0F, 1.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(44, 20)
						.cuboid(-1.0F, 3.0F, -2.0F, 2.0F, 3.0F, 0.0F, Dilation.NONE)
						.uv(18, 47)
						.cuboid(-4.0F, 0.0F, -2.0F, 2.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(44, 23)
						.cuboid(-1.0F, 3.0F, 2.0F, 2.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(24, 34)
						.cuboid(-1.0F, 5.0F, 2.0F, 2.0F, 5.0F, 0.0F, Dilation.NONE)
						.uv(30, 47)
						.cuboid(-3.0F, 7.0F, 2.0F, 2.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(34, 47)
						.cuboid(1.0F, 7.0F, 2.0F, 2.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(0, 48)
						.cuboid(-4.0F, 6.0F, 2.0F, 2.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(4, 48)
						.cuboid(2.0F, 6.0F, 2.0F, 2.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(46, 9)
						.cuboid(-1.0F, 1.0F, 2.0F, 2.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(28, 22)
						.cuboid(-4.0F, 4.0F, 2.0F, 8.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(16, 34)
						.cuboid(4.0F, 0.0F, -2.0F, 0.0F, 1.0F, 4.0F, Dilation.NONE)
						.uv(0, 34)
						.cuboid(-4.0F, 10.0F, -2.0F, 0.0F, 2.0F, 4.0F, Dilation.NONE)
						.uv(32, 10)
						.cuboid(4.0F, 10.0F, -2.0F, 0.0F, 2.0F, 4.0F, Dilation.NONE)
						.uv(42, 30)
						.cuboid(-2.0F, 11.0F, -2.0F, 4.0F, 1.0F, 0.0F, Dilation.NONE)
						.uv(46, 5)
						.cuboid(2.0F, 10.0F, -2.0F, 2.0F, 2.0F, 0.0F, Dilation.NONE)
						.uv(46, 3)
						.cuboid(-4.0F, 10.0F, -2.0F, 2.0F, 2.0F, 0.0F, Dilation.NONE)
						.uv(24, 18)
						.cuboid(-4.0F, 10.0F, 2.0F, 8.0F, 2.0F, 0.0F, Dilation.NONE)
						.uv(0, 16)
						.cuboid(-4.0F, 12.0F, -2.0F, 8.0F, 0.0F, 4.0F, Dilation.NONE),
				ModelTransform.pivot(0.0F, 17.0F, 0.0F)
		);

		ModelPartData leftArm = root.addChild(
				"left_arm",
				ModelPartBuilder.create(),
				ModelTransform.pivot(5.0F, 19.0F, 0.0F)
		);

		leftArm.addChild(
				"LEFT_ARM_r1",
				ModelPartBuilder.create()
						.uv(42, 31)
						.cuboid(0.0F, -2.0F, -1.0F, 1.0F, 1.0F, 2.0F, Dilation.NONE)
						.uv(38, 40)
						.cuboid(0.0F, 7.0F, -1.0F, 1.0F, 3.0F, 2.0F, Dilation.NONE)
						.uv(28, 30)
						.cuboid(1.0F, -1.0F, -1.0F, 1.0F, 8.0F, 2.0F, Dilation.NONE)
						.uv(22, 20)
						.cuboid(-1.0F, -2.0F, -1.0F, 1.0F, 12.0F, 2.0F, Dilation.NONE),
				ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F)
		);

		ModelPartData rightArm = root.addChild(
				"right_arm",
				ModelPartBuilder.create(),
				ModelTransform.pivot(-5.0F, 19.0F, 0.0F)
		);

		rightArm.addChild(
				"RIGHT_ARM_r1",
				ModelPartBuilder.create()
						.uv(42, 13)
						.cuboid(-1.0F, -2.0F, -1.0F, 1.0F, 3.0F, 2.0F, Dilation.NONE)
						.uv(8, 42)
						.cuboid(-1.0F, 7.0F, -1.0F, 1.0F, 3.0F, 2.0F, Dilation.NONE)
						.uv(32, 0)
						.cuboid(-2.0F, -1.0F, -1.0F, 1.0F, 8.0F, 2.0F, Dilation.NONE)
						.uv(16, 20)
						.cuboid(0.0F, -2.0F, -1.0F, 1.0F, 12.0F, 2.0F, Dilation.NONE),
				ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F)
		);

		root.addChild(
				"head",
				ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, Dilation.NONE)
						.uv(28, 24)
						.cuboid(2.0F, -8.0F, -4.0F, 5.0F, 1.0F, 2.0F, Dilation.NONE)
						.uv(40, 10)
						.cuboid(-6.0F, -9.0F, -4.0F, 3.0F, 1.0F, 2.0F, Dilation.NONE)
						.uv(42, 26)
						.cuboid(-6.0F, -8.0F, -2.0F, 3.0F, 1.0F, 1.0F, Dilation.NONE)
						.uv(42, 28)
						.cuboid(3.0F, -8.0F, -2.0F, 3.0F, 1.0F, 1.0F, Dilation.NONE)
						.uv(38, 45)
						.cuboid(5.0F, -7.0F, -2.0F, 1.0F, 2.0F, 1.0F, Dilation.NONE)
						.uv(46, 0)
						.cuboid(-6.0F, -7.0F, -2.0F, 1.0F, 2.0F, 1.0F, Dilation.NONE)
						.uv(8, 39)
						.cuboid(3.0F, -9.0F, -4.0F, 3.0F, 1.0F, 2.0F, Dilation.NONE)
						.uv(28, 27)
						.cuboid(-7.0F, -8.0F, -4.0F, 5.0F, 1.0F, 2.0F, Dilation.NONE)
						.uv(24, 16)
						.cuboid(-4.0F, -7.0F, -5.0F, 8.0F, 1.0F, 1.0F, Dilation.NONE)
						.uv(42, 46)
						.cuboid(3.0F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, Dilation.NONE)
						.uv(46, 46)
						.cuboid(-4.0F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, Dilation.NONE)
						.uv(40, 18)
						.cuboid(-6.0F, -8.0F, -5.0F, 3.0F, 1.0F, 1.0F, Dilation.NONE)
						.uv(42, 24)
						.cuboid(3.0F, -8.0F, -5.0F, 3.0F, 1.0F, 1.0F, Dilation.NONE)
						.uv(18, 39)
						.cuboid(-7.0F, -7.0F, -4.0F, 2.0F, 3.0F, 2.0F, Dilation.NONE)
						.uv(0, 40)
						.cuboid(5.0F, -7.0F, -4.0F, 2.0F, 3.0F, 2.0F, Dilation.NONE)
						.uv(46, 7)
						.cuboid(5.0F, -4.0F, -4.0F, 1.0F, 1.0F, 1.0F, Dilation.NONE)
						.uv(22, 44)
						.cuboid(5.0F, -5.0F, -5.0F, 1.0F, 2.0F, 1.0F, Dilation.NONE)
						.uv(0, 45)
						.cuboid(-6.0F, -5.0F, -5.0F, 1.0F, 2.0F, 1.0F, Dilation.NONE)
						.uv(44, 40)
						.cuboid(5.0F, -4.0F, -6.0F, 1.0F, 2.0F, 1.0F, Dilation.NONE)
						.uv(4, 45)
						.cuboid(-6.0F, -4.0F, -6.0F, 1.0F, 2.0F, 1.0F, Dilation.NONE)
						.uv(44, 43)
						.cuboid(4.0F, -3.0F, -6.0F, 1.0F, 2.0F, 1.0F, Dilation.NONE)
						.uv(46, 36)
						.cuboid(3.0F, -2.0F, -6.0F, 1.0F, 1.0F, 1.0F, Dilation.NONE)
						.uv(46, 38)
						.cuboid(-4.0F, -2.0F, -6.0F, 1.0F, 1.0F, 1.0F, Dilation.NONE)
						.uv(14, 45)
						.cuboid(-5.0F, -3.0F, -6.0F, 1.0F, 2.0F, 1.0F, Dilation.NONE)
						.uv(46, 34)
						.cuboid(-6.0F, -4.0F, -4.0F, 1.0F, 1.0F, 1.0F, Dilation.NONE),
				ModelTransform.pivot(0.0F, 16.0F, 0.0F)
		);

		return TexturedModelData.of(modelData, 64, 64);
	}
}