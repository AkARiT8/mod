package dev.hellmod.entity.IA;

import dev.hellmod.entity.SnoterEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public class PatrolFlyGoal extends Goal {

    private final SnoterEntity mob;

    private double targetX;
    private double targetY;
    private double targetZ;

    private int idleTicks;

    public PatrolFlyGoal(SnoterEntity mob) {

        this.mob = mob;

        this.setControls(
                EnumSet.of(Control.MOVE)
        );
    }

    @Override
    public boolean canStart() {
        return mob.getTarget() == null;
    }

    @Override
    public boolean shouldContinue() {
        return mob.getTarget() == null;
    }

    @Override
    public void start() {
        chooseNewDestination();
    }

    @Override
    public void tick() {

        double distSq =
                mob.squaredDistanceTo(
                        targetX,
                        targetY,
                        targetZ
                );

        if (distSq > 4.0D) {

            mob.getMoveControl().moveTo(
                    targetX,
                    targetY,
                    targetZ,
                    0.6D
            );

            return;
        }

        idleTicks++;

        if (idleTicks > 20 + mob.getRandom().nextInt(40)) {

            idleTicks = 0;

            chooseNewDestination();
        }
    }

    private void chooseNewDestination() {

        double angle =
                mob.getRandom().nextDouble()
                        * Math.PI * 2;

        double radius =
                3 +
                        mob.getRandom().nextDouble() * 8;

        targetX =
                mob.getX()
                        + Math.cos(angle) * radius;

        targetZ =
                mob.getZ()
                        + Math.sin(angle) * radius;

        targetY =
                mob.getY()
                        + (mob.getRandom().nextDouble() - 0.5D) * 3D;

        if (targetY < mob.getWorld().getBottomY() + 5) {
            targetY = mob.getWorld().getBottomY() + 5;
        }
    }
}