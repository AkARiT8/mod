package dev.hellmod.entity.goal;

import dev.hellmod.entity.DoomCreeperEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class DoomCreeperBeamLookGoal extends Goal {

    private final DoomCreeperEntity creeper;

    public DoomCreeperBeamLookGoal(DoomCreeperEntity creeper) {
        this.creeper = creeper;
        this.setControls(EnumSet.of(Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (!creeper.isBeamActive()) {
            return false;
        }

        PlayerEntity target = creeper.getBeamTarget();

        return target != null
                && target.isAlive()
                && !target.isCreative()
                && !target.isSpectator();
    }

    @Override
    public boolean shouldContinue() {
        return canStart();
    }

    @Override
    public void tick() {
        PlayerEntity target = creeper.getBeamTarget();

        if (target != null) {
            creeper.getLookControl().lookAt(target, 30.0F, 30.0F);
        }
    }
}