

package dev.hellmod.client.model;

import dev.hellmod.entity.SnoterTier2Entity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

	public class SnoterModelTier2 extends EntityModel<SnoterTier2Entity> {

		public static final EntityModelLayer LAYER_LOCATION =
				new EntityModelLayer(
						Identifier.of("hellmod", "snoter_tier_2"),
						"main"
				);

		private final ModelPart SNOTER;
		private final ModelPart OJOS;
		private final ModelPart BOCA;
		private final ModelPart ARMOR;
		private final ModelPart RWING;
		private final ModelPart LWING;

		public SnoterModelTier2(ModelPart root) {
			this.SNOTER = root.getChild("SNOTER");
			this.OJOS = this.SNOTER.getChild("OJOS");
			this.BOCA = this.SNOTER.getChild("BOCA");
			this.ARMOR = this.SNOTER.getChild("ARMOR");
			this.RWING = this.SNOTER.getChild("RWING");
			this.LWING = this.SNOTER.getChild("LWING");
		}

		public static TexturedModelData getTexturedModelData() {
			ModelData modelData = new ModelData();
			ModelPartData modelPartData = modelData.getRoot();
			ModelPartData SNOTER = modelPartData.addChild("SNOTER", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -8.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

			ModelPartData OJOS = SNOTER.addChild("OJOS", ModelPartBuilder.create().uv(26, 32).cuboid(0.0F, -1.0F, -1.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
					.uv(16, 32).cuboid(-5.0F, -1.0F, -1.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
					.uv(0, 20).cuboid(-7.0F, 2.0F, -3.0F, 12.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -11.0F, 0.0F));

			ModelPartData BOCA = SNOTER.addChild("BOCA", ModelPartBuilder.create().uv(28, 29).cuboid(-3.0F, 5.5F, 0.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F))
					.uv(14, 37).cuboid(-3.0F, 2.5F, 0.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
					.uv(36, 32).cuboid(0.0F, 2.5F, 0.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
					.uv(16, 29).cuboid(-3.0F, 1.5F, 0.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -7.0F, 5.0F));

			ModelPartData ARMOR = SNOTER.addChild("ARMOR", ModelPartBuilder.create().uv(6, 35).cuboid(-9.5F, 6.5F, -1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
					.uv(36, 20).cuboid(-9.5F, -0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F))
					.uv(16, 27).cuboid(-7.5F, -2.5F, -0.5F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F))
					.uv(0, 27).cuboid(-9.5F, 7.5F, -8.5F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
					.uv(0, 27).cuboid(-9.5F, -2.5F, -8.5F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
					.uv(16, 27).cuboid(-7.5F, 7.5F, -0.5F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F))
					.uv(16, 27).cuboid(-7.5F, -2.5F, -10.5F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F))
					.uv(0, 27).cuboid(0.5F, 7.5F, -8.5F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
					.uv(16, 27).cuboid(-7.5F, 7.5F, -10.5F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F))
					.uv(36, 20).cuboid(0.5F, -0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F))
					.uv(36, 20).cuboid(0.5F, -0.5F, -10.5F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F))
					.uv(6, 35).cuboid(-9.5F, -2.5F, -1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
					.uv(36, 20).cuboid(-9.5F, -0.5F, -10.5F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F))
					.uv(6, 35).cuboid(-9.5F, -2.5F, -10.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
					.uv(6, 35).cuboid(-0.5F, -2.5F, -10.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
					.uv(6, 35).cuboid(-9.5F, 6.5F, -10.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
					.uv(6, 35).cuboid(-0.5F, 6.5F, -10.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
					.uv(0, 27).cuboid(0.5F, -2.5F, -8.5F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
					.uv(6, 35).cuboid(-0.5F, 6.5F, -1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
					.uv(6, 35).cuboid(-0.5F, -2.5F, -1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -6.0F, 5.0F));

			ModelPartData RWING = SNOTER.addChild("RWING", ModelPartBuilder.create(), ModelTransform.pivot(8.0F, -4.0F, -2.0F));

			ModelPartData WING_r1 = RWING.addChild("WING_r1", ModelPartBuilder.create().uv(20, 37).cuboid(-1.4F, -5.2F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
					.uv(0, 35).cuboid(-1.4F, -5.2F, 1.0F, 1.0F, 5.0F, 2.0F, new Dilation(0.0F))
					.uv(20, 37).cuboid(-1.4F, -5.2F, 3.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

			ModelPartData LWING = SNOTER.addChild("LWING", ModelPartBuilder.create(), ModelTransform.pivot(-7.0F, -7.0F, 1.0F));

			ModelPartData WING_r2 = LWING.addChild("WING_r2", ModelPartBuilder.create().uv(20, 37).cuboid(0.2F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
					.uv(0, 35).cuboid(0.2F, -2.0F, -2.0F, 1.0F, 5.0F, 2.0F, new Dilation(0.0F))
					.uv(20, 37).cuboid(0.2F, -2.0F, -3.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));
			return TexturedModelData.of(modelData, 64, 64);
		}

		@Override
		public void setAngles(
				SnoterTier2Entity entity,
				float limbSwing,
				float limbSwingAmount,
				float ageInTicks,
				float netHeadYaw,
				float headPitch) {

			this.SNOTER.roll =
					(float)Math.sin(
							ageInTicks * 0.10F
					) * 0.08F;

			this.SNOTER.pivotY =
					24.0f + (float) Math.sin(ageInTicks * 0.1f) * 0.5f;
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
				float alpha) {

			this.SNOTER.render(
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