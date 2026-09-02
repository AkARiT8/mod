package dev.hellmod.client.model;

import dev.hellmod.entity.DoomZombieEntity;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;

public class DoomZombieModel extends ZombieEntityModel<DoomZombieEntity> {

	public static final EntityModelLayer LAYER_LOCATION =
			new EntityModelLayer(Identifier.of("hellmod", "doom_zombie"), "main");

	public DoomZombieModel(ModelPart root) {
		super(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData data = new ModelData();
		ModelPartData root = data.getRoot();

		ModelPartData head = root.addChild(
				"head",
				ModelPartBuilder.create()
						.uv(0, 11)
						.cuboid(-4, -8, -4, 8, 8, 8),
				ModelTransform.pivot(0, 0, 0)
		);

		head.addChild(
				"head_extra",
				ModelPartBuilder.create()
						.uv(0, 0).cuboid(-5, -6, -4, 10, 1, 10)
						.uv(32, 11).cuboid(-6, -7, 5, 2, 1, 2)
						.uv(20, 60).cuboid(-6, -9, 6, 1, 2, 1)
						.uv(60, 20).cuboid(-6, -9, -5, 1, 2, 1)
						.uv(60, 23).cuboid(5, -9, -5, 1, 2, 1)
						.uv(24, 60).cuboid(5, -9, 6, 1, 2, 1)
						.uv(60, 12).cuboid(-1, -7, 0, 2, 1, 2)
						.uv(32, 14).cuboid(-6, -7, -5, 2, 1, 2)
						.uv(60, 6).cuboid(4, -7, -5, 2, 1, 2)
						.uv(60, 9).cuboid(4, -7, 5, 2, 1, 2)
						.uv(24, 27).cuboid(4, -5, -3, 1, 8, 9)
						.uv(40, 0).cuboid(-5, -5, -3, 1, 8, 9)
						.uv(14, 60).cuboid(2, 2, -4, 2, 1, 1)
						.uv(44, 36).cuboid(4, 0, -4, 1, 3, 1)
						.uv(44, 40).cuboid(-5, 0, -4, 1, 3, 1)
						.uv(60, 26).cuboid(-5, -5, -4, 1, 2, 1)
						.uv(28, 60).cuboid(4, -5, -4, 1, 2, 1)
						.uv(60, 15).cuboid(-4, 2, -4, 2, 1, 1)
						.uv(48, 36).cuboid(-4, -5, 5, 8, 8, 1),
				ModelTransform.pivot(0, -3, -1)
		);

		ModelPartData body = root.addChild(
				"body",
				ModelPartBuilder.create()
						.uv(0, 27)
						.cuboid(-4, 0, -2, 8, 12, 4),
				ModelTransform.pivot(0, 0, 0)
		);

		body.addChild(
				"extra_body",
				ModelPartBuilder.create()
						.uv(60, 0).cuboid(-9, -22, -2, 1, 2, 4)
						.uv(32, 20).cuboid(8, -22, -2, 1, 2, 4)
						.uv(0, 59).cuboid(8, -24, -3, 1, 2, 6)
						.uv(32, 17).cuboid(-8, -24, 2, 16, 2, 1)
						.uv(48, 59).cuboid(-9, -24, -3, 1, 2, 6)
						.uv(48, 52).cuboid(5, -25, -3, 4, 1, 6)
						.uv(48, 45).cuboid(-9, -25, -3, 4, 1, 6),
				ModelTransform.pivot(0, 24, 0)
		);

		root.addChild(
				"left_leg",
				ModelPartBuilder.create()
						.uv(0, 43)
						.cuboid(-2, 0, -2, 4, 12, 4),
				ModelTransform.pivot(2, 12, 0)
		);

		root.addChild(
				"right_leg",
				ModelPartBuilder.create()
						.uv(16, 44)
						.cuboid(-2, 0, -2, 4, 12, 4),
				ModelTransform.pivot(-2, 12, 0)
		);

		root.addChild(
				"left_arm",
				ModelPartBuilder.create()
						.uv(44, 20)
						.cuboid(-1, -2, -2, 4, 12, 4),
				ModelTransform.pivot(5, 2, 0)
		);

		root.addChild(
				"right_arm",
				ModelPartBuilder.create()
						.uv(32, 44)
						.cuboid(-3, -2, -2, 4, 12, 4),
				ModelTransform.pivot(-5, 2, 0)
		);

		root.addChild(
				"hat",
				ModelPartBuilder.create(),
				ModelTransform.pivot(0, 0, 0)
		);

		return TexturedModelData.of(data, 128, 128);
	}
}