package dev.hellmod.mixin;

import dev.hellmod.blocks.custom.StageData;
import dev.hellmod.stage.modifier.StageModifierManager;
import dev.hellmod.util.VariantHolder;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class CreeperFuseMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void hellmod$applyFuse(CallbackInfo ci) {

        CreeperEntity creeper = (CreeperEntity)(Object)this;

        if (creeper.getWorld().isClient) return;

        CreeperAccessor acc = (CreeperAccessor) creeper;
        VariantHolder holder = (VariantHolder) creeper;

        int current = acc.getCurrentFuseTime();

        if (current == 1) {

            int fuse = holder.hellmod$getFuseTime();

            acc.setFuseTime(fuse);

        }
    }
}