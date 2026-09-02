package dev.hellmod.mixin.mobMixin;

import dev.hellmod.entity.DoomCreeperEntity;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class DoomCreeperFuseMixin {

    @Shadow
    private int fuseTime;

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void hellmod$setFuseTime(CallbackInfo ci) {

        if ((Object) this instanceof DoomCreeperEntity) {
            this.fuseTime = 200;
        }
    }
}