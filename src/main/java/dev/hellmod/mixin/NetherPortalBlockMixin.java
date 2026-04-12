package dev.hellmod.mixin;

import dev.hellmod.blocks.custom.StageData;
import dev.hellmod.stage.modifier.StageModifierManager;
import dev.hellmod.stage.modifier.impl.NetherModifier;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {

    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void hellmod$blockNether(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {

        if (!(entity instanceof ServerPlayerEntity player)) return;

        ServerWorld Sworld = player.getServerWorld();
        int stage = StageData.get(Sworld).getStage();

        if (NetherModifier.isNetherDisabled(stage)) {
            ci.cancel();

            player.sendMessage(Text.literal("the portal is week..."), true);
        }
    }
}