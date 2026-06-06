package dev.hellmod.mixin;

import dev.hellmod.structures.WorldStructureGenerator;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFireBlock.class)
public class FireBlockMixin {

    @Inject(
            method = "onBlockAdded",
            at = @At("HEAD"),
            cancellable = true
    )
    private void preventFire(

            net.minecraft.block.BlockState state,
            World world,
            BlockPos pos,
            net.minecraft.block.BlockState oldState,
            boolean notify,

            CallbackInfo ci
    ) {

        if (
                WorldStructureGenerator
                        .isProtected(pos)
        ) {

            world.removeBlock(
                    pos,
                    false
            );

            ci.cancel();
        }
    }
}