package dev.hellmod.entity;

import dev.hellmod.entity.IA.FlyAroundTargetGoal;
import dev.hellmod.entity.IA.PatrolFlyGoal;
import dev.hellmod.entity.IA.SpitBurstGoal;
import dev.hellmod.entity.IA.SpitBurstGoalTier2;
import dev.hellmod.stage.manager.StageData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class SnoterTier2Entity extends SnoterEntity{

    public SnoterTier2Entity(EntityType<? extends HostileEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initGoals() {

        this.goalSelector.add(
                0,
                new FlyAroundTargetGoal(this)
        );

        this.goalSelector.add(
                1,
                new SpitBurstGoalTier2(this)
        );


        this.goalSelector.add(
                2,
                new PatrolFlyGoal(this)
        );


        this.targetSelector.add(
                1,
                new ActiveTargetGoal<>(
                        this,
                        PlayerEntity.class,
                        true
                )
        );
    }

    public static boolean canSpawnTier2(
            EntityType<SnoterTier2Entity> type,
            ServerWorldAccess world,
            SpawnReason reason,
            BlockPos pos,
            Random random
    ) {

        ServerWorld serverWorld = world.toServerWorld();

        StageData stageData = StageData.get(serverWorld);

        if (stageData.getStage() != 2) {
            return false;
        }

        return HostileEntity.canSpawnInDark(
                type,
                world,
                reason,
                pos,
                random
        );
    }

}
