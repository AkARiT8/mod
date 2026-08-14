package dev.hellmod.entity.IA;

import dev.hellmod.entity.SnoterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class FlyAroundTargetGoal extends Goal {

    private final SnoterEntity mob;

    public FlyAroundTargetGoal(SnoterEntity mob) {

        this.mob = mob;

        this.setControls(
                EnumSet.of(
                        Control.MOVE,
                        Control.LOOK
                )
        );
    }

    @Override
    public boolean canStart() {
        return mob.getTarget() != null;
    }

    @Override
    public boolean shouldContinue() {
        return mob.getTarget() != null;
    }

    @Override
    public void tick() {

        LivingEntity target = mob.getTarget();

        if (target == null)
            return;

        mob.getLookControl().lookAt(
                target,
                360,
                360
        );

        Vec3d toTarget =
                target.getPos().subtract(
                        mob.getPos()
                );

        double distance =
                toTarget.length();

        if (distance < 0.001)
            return;

        Vec3d radial =
                toTarget.normalize();

        Vec3d tangent =
                new Vec3d(
                        -radial.z,
                        0,
                        radial.x
                ).multiply(mob.getOrbitDirection());

        double desiredDistance = 6.0D;

        Vec3d movement =
                tangent.multiply(0.20D);

        if (distance < desiredDistance) {

            movement = movement.add(
                    radial.multiply(-0.10D)
            );
        }
        else if (distance > desiredDistance + 1.5D) {

            movement = movement.add(
                    radial.multiply(0.20D)
            );
        }

        double targetHeight =
                target.getY() + 3.0D;

        double verticalCorrection =
                (targetHeight - mob.getY()) * 0.10D;

        mob.setVelocity(
                movement.x,
                verticalCorrection,
                movement.z
        );
    }
}