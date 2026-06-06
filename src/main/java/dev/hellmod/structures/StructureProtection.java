package dev.hellmod.structures;

import dev.hellmod.blocks.ModBlocks;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class StructureProtection {

    public static void init() {


        PlayerBlockBreakEvents.BEFORE.register((

                world,
                player,
                pos,
                state,
                blockEntity

        ) -> {

            if (
                    WorldStructureGenerator
                            .isProtected(pos)
            ) {

                System.out.println(
                        "BREAK CANCELLED"
                );

                return false;
            }

            return true;
        });


        UseBlockCallback.EVENT.register((

                player,
                world,
                hand,
                hitResult

        ) -> {

            BlockPos clickedPos =
                    hitResult.getBlockPos();

            BlockPos placePos =
                    clickedPos.offset(
                            hitResult.getSide()
                    );


            var stack =
                    player.getStackInHand(hand);

            var item =
                    stack.getItem();


            boolean dangerousItem =

                    item instanceof BlockItem

                            || item instanceof
                            net.minecraft.item.FlintAndSteelItem

                            || item instanceof
                            net.minecraft.item.FireChargeItem

                            || item instanceof
                            net.minecraft.item.BucketItem;


            if (!dangerousItem) {

                return ActionResult.PASS;
            }

            if (
                    WorldStructureGenerator
                            .isProtected(placePos)
            ) {

                var clickedState =
                        world.getBlockState(
                                clickedPos
                        );

                if (

                        clickedState.isOf(
                                ModBlocks.STAGE_BLOCK.getLeft()
                        )

                ) {

                    return ActionResult.PASS;
                }

                System.out.println(
                        "PLACE CANCELLED"
                );

                return ActionResult.FAIL;
            }

            return ActionResult.PASS;
        });
    }
}