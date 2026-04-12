package dev.hellmod.mixin;

import dev.hellmod.access.GhastAccessor;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireballEntity.class)
public abstract class FireballMixin {

    @Inject(method = "onCollision", at = @At("HEAD"), cancellable = true)
    private void onHit(CallbackInfo ci) {

        FireballEntity fireball = (FireballEntity)(Object)this;

        if (!(fireball.getOwner() instanceof GhastEntity ghast)) return;
        if (!(ghast instanceof GhastAccessor accessor)) return;
        if (!(fireball.getWorld() instanceof ServerWorld world)) return;

        float power = accessor.hellmod$getFireballPower();

        var explosion = world.createExplosion(
                fireball,
                fireball.getX(),
                fireball.getY(),
                fireball.getZ(),
                power,
                false,
                World.ExplosionSourceType.MOB
        );

        for (BlockPos pos : explosion.getAffectedBlocks()) {
            world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        }

        fireball.discard();
        ci.cancel();
    }
}