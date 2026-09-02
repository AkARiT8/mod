package dev.hellmod.entity.IA;

import dev.hellmod.entity.DoomCreeperEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class DoomCreeperBeamMoveGoal extends Goal {

    private final DoomCreeperEntity creeper;
    private static final double MIN_DISTANCE = 7.0;

    public DoomCreeperBeamMoveGoal(DoomCreeperEntity creeper) {
        this.creeper = creeper;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (!creeper.isBeamActive()) {
            return false;
        }

        PlayerEntity target = creeper.getBeamTarget();

        if (target == null || !target.isAlive() || target.isCreative() || target.isSpectator()) {
            return false;
        }

        return creeper.squaredDistanceTo(target) > MIN_DISTANCE * MIN_DISTANCE;
    }

    @Override
    public boolean shouldContinue() {
        return canStart();
    }

    @Override
    public void start() {
        moveTowardsTarget();
    }

    @Override
    public void tick() {
        moveTowardsTarget();
    }

    private void moveTowardsTarget() {
        PlayerEntity target = creeper.getBeamTarget();

        if (target == null) {
            return;
        }

        creeper.getNavigation().startMovingTo(
                target.getX(),
                target.getY(),
                target.getZ(),
                1.0
        );
    }

    @Override
    public void stop() {
        creeper.getNavigation().stop();
    }
}