package dev.hellmod.entity.IA;

import dev.hellmod.entity.SnoterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import dev.hellmod.entity.SnotProjectileEntity;

public class SpitBurstGoal extends Goal {

    protected final SnoterEntity mob;

    private int burstShots;
    private int burstTimer;

    public SpitBurstGoal(SnoterEntity mob) {
        this.mob = mob;
    }

    @Override
    public boolean canStart() {

        return mob.getTarget() != null
                && mob.canShoot();
    }

    @Override
    public void start() {

        mob.setShooting(true);

        burstShots = 3;
        burstTimer = 0;
    }

    @Override
    public boolean shouldContinue() {

        return burstShots > 0;
    }

    @Override
    public void tick() {

        LivingEntity target = mob.getTarget();

        if (target == null)
            return;

        burstTimer++;

        if (burstTimer >= 8) {

            burstTimer = 0;

            shoot(target);

            burstShots--;

            if (burstShots <= 0) {

                if (mob.getRandom().nextFloat() < 0.35F) {
                    mob.reverseOrbitDirection();
                }

                mob.setShooting(false);

                mob.resetShootCooldown();
            }
        }
    }

    protected void shoot(LivingEntity target) {

        SnotProjectileEntity projectile =
                new SnotProjectileEntity(
                        mob.getWorld(),
                        mob
                );

        double dx =
                target.getX() - mob.getX();

        double dy =
                target.getEyeY() - projectile.getY();

        double dz =
                target.getZ() - mob.getZ();

        projectile.setVelocity(
                dx,
                dy,
                dz,
                1.6f,
                0.5f
        );

        mob.getWorld().spawnEntity(
                projectile
        );
    }

    @Override
    public void stop() {

        mob.setShooting(false);
    }
}