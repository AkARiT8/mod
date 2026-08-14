package dev.hellmod.entity.IA;

import dev.hellmod.entity.SnotProjectileEntity;
import dev.hellmod.entity.SnotProjectileTier2Entity;
import dev.hellmod.entity.SnoterEntity;
import net.minecraft.entity.LivingEntity;

public class SpitBurstGoalTier2 extends SpitBurstGoal{
    public SpitBurstGoalTier2(SnoterEntity mob) {
        super(mob);
    }


    protected void shoot(LivingEntity target) {

        SnotProjectileTier2Entity projectile =
                new SnotProjectileTier2Entity(
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

}
