package dev.hellmod.mixin;

import dev.hellmod.access.BlazeAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmallFireballEntity.class)
public abstract class SmallFireballMixin {

    @Unique
    private static final ThreadLocal<Boolean> hellmod$spawning =
            ThreadLocal.withInitial(() -> false);

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void onCreate(CallbackInfo ci) {

        if (hellmod$spawning.get()) return;

        SmallFireballEntity fireball = (SmallFireballEntity)(Object)this;

        if (!(fireball.getOwner() instanceof BlazeEntity blaze)) return;
        if (!(blaze instanceof BlazeAccessor accessor)) return;

        float damage = accessor.hellmod$getBlazeDamage();
        int count = accessor.hellmod$getBlazeShots();

        fireball.setVelocity(
                fireball.getVelocity().x,
                fireball.getVelocity().y,
                fireball.getVelocity().z,
                damage,
                0.0f
        );

        LivingEntity target = blaze.getTarget();
        if (target == null) return;

        double dx = target.getX() - blaze.getX();
        double dy = target.getBodyY(0.5) - blaze.getEyeY();
        double dz = target.getZ() - blaze.getZ();

        float spread = 0.3f;

        hellmod$spawning.set(true);

        for (int i = 1; i < count; i++) {

            var random = blaze.getRandom();

            Vec3d dir = new Vec3d(dx, dy, dz).normalize();

            dir = dir.add(
                    (random.nextFloat() - 0.5f) * spread,
                    (random.nextFloat() - 0.5f) * spread,
                    (random.nextFloat() - 0.5f) * spread
            ).normalize();

            SmallFireballEntity extra = new SmallFireballEntity(
                    blaze.getWorld(),
                    blaze,
                    dir.x,
                    dir.y,
                    dir.z
            );

            extra.setPosition(
                    blaze.getX(),
                    blaze.getEyeY(),
                    blaze.getZ()
            );

            extra.setVelocity(
                    dir.x,
                    dir.y,
                    dir.z,
                    damage,
                    0.0f
            );

            extra.velocityDirty = true;

            blaze.getWorld().spawnEntity(extra);
        }

        hellmod$spawning.set(false);
    }
}