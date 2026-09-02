package dev.hellmod.client.Renderer;

import dev.hellmod.blocks.custom.CustomButton;
import dev.hellmod.blocks.entity.CustomButtonBlockEntity;
import dev.hellmod.client.model.CustomButtonModel;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

public class CustomButtonBlockEntityRenderer
        implements BlockEntityRenderer<CustomButtonBlockEntity> {

    private final CustomButtonModel<?> model;

    public CustomButtonBlockEntityRenderer(
            BlockEntityRendererFactory.Context context
    ) {
        model = new CustomButtonModel<>(
                context.getLayerModelPart(CustomButtonModel.LAYER_LOCATION)
        );
    }

    @Override
    public void render(
            CustomButtonBlockEntity blockEntity,
            float tickDelta,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int overlay
    ) {

        boolean pressed = blockEntity
                .getCachedState()
                .get(CustomButton.PRESSED);

        Identifier texture = Identifier.of(
                "hellmod",
                pressed
                        ? "textures/block/custom_button/custom_button_pressed.png"
                        : "textures/block/custom_button/custom_button.png"
        );

        VertexConsumer vertexConsumer =
                vertexConsumers.getBuffer(
                        RenderLayer.getEntityCutout(texture)
                );


        matrices.push();

        Direction facing = blockEntity
                .getCachedState()
                .get(CustomButton.FACING);

        switch (facing) {

            case DOWN -> {
            }

            case UP -> {
                matrices.translate(
                        0.0F,
                        1.0F,
                        1.0F
                );

                matrices.multiply(
                        RotationAxis.POSITIVE_X
                                .rotationDegrees(180.0F)
                );
            }

            case SOUTH -> {
                matrices.translate(
                        0.0F,
                        0.0F,
                        1.0F
                );

                matrices.multiply(
                        RotationAxis.POSITIVE_X
                                .rotationDegrees(-90.0F)
                );
            }

            case NORTH -> {
                matrices.translate(
                        0.0F,
                        1.0F,
                        0.0F
                );

                matrices.multiply(
                        RotationAxis.POSITIVE_X
                                .rotationDegrees(90.0F)
                );
            }

            case EAST -> {
                matrices.translate(
                        1.0F,
                        0.0F,
                        0.0F
                );

                matrices.multiply(
                        RotationAxis.POSITIVE_Z
                                .rotationDegrees(90.0F)
                );
            }

            case WEST -> {
                matrices.translate(
                        0.0F,
                        1.0F,
                        0.0F
                );

                matrices.multiply(
                        RotationAxis.POSITIVE_Z
                                .rotationDegrees(-90.0F)
                );
            }
        }

        matrices.translate(
                0.5F - (1.0F / 16.0F),
                -0.35F,
                0.5F + (1.0F / 16.0F)
        );

        model.renderBase(
                matrices,
                vertexConsumer,
                light,
                overlay,
                1.0F,
                1.0F,
                1.0F,
                1.0F
        );

        if (pressed) {

            matrices.push();

            matrices.translate(
                    0.0F,
                    1.0F / 16.0F,
                    0.0F
            );

            model.renderButton(
                    matrices,
                    vertexConsumer,
                    light,
                    overlay,
                    1.0F,
                    1.0F,
                    1.0F,
                    1.0F
            );

            matrices.pop();

        } else {

            model.renderButton(
                    matrices,
                    vertexConsumer,
                    light,
                    overlay,
                    1.0F,
                    1.0F,
                    1.0F,
                    1.0F
            );
        }


        matrices.pop();
    }
}