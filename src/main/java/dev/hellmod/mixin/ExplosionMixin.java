package dev.hellmod.mixin;

import dev.hellmod.structures.WorldStructureGenerator;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(Explosion.class)
public class ExplosionMixin {

    @Redirect(
            method = "collectBlocksAndDamageEntities",
            at = @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/world/explosion/ExplosionBehavior;getBlastResistance(Lnet/minecraft/world/explosion/Explosion;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/fluid/FluidState;)Ljava/util/Optional;"
            )
    )
    private Optional<Float> protectBlocks(

            net.minecraft.world.explosion.ExplosionBehavior behavior,

            Explosion explosion,
            BlockView world,
            BlockPos pos,
            BlockState blockState,
            FluidState fluidState
    ) {

        if (
                WorldStructureGenerator
                        .isProtected(pos)
        ) {


            return Optional.of(
                    3600000F
            );
        }

        return behavior.getBlastResistance(
                explosion,
                world,
                pos,
                blockState,
                fluidState
        );
    }
}