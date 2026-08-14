package dev.hellmod.entity;

import dev.hellmod.entity.IA.FlyAroundTargetGoal;
import dev.hellmod.entity.IA.PatrolFlyGoal;
import dev.hellmod.entity.IA.SpitBurstGoal;
import dev.hellmod.stage.manager.StageData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class SnoterEntity extends HostileEntity {

    private int burstCooldown = 0;
    private boolean shooting;
    private int orbitDirection = 1;

    public SnoterEntity(EntityType<? extends HostileEntity> type, World world) {
        super(type, world);

        this.moveControl = new FlightMoveControl(
                this,
                20,
                true
        );
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30D)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 5.50D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0D);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new BirdNavigation(this, world);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    public boolean handleFallDamage(
            float fallDistance,
            float damageMultiplier,
            DamageSource damageSource
    ) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient) {

            if (burstCooldown > 0) {
                burstCooldown--;
            }

            if (this.getTarget() != null) {

                LivingEntity target = this.getTarget();

                double dx = target.getX() - this.getX();
                double dy = target.getEyeY() - this.getEyeY();
                double dz = target.getZ() - this.getZ();

                double horizontalDistance =
                        Math.sqrt(dx * dx + dz * dz);

                float yaw = (float)(
                        Math.toDegrees(
                                Math.atan2(dz, dx)
                        ) - 90F
                );

                float pitch = (float)(
                        -Math.toDegrees(
                                Math.atan2(
                                        dy,
                                        horizontalDistance
                                )
                        )
                );

                this.setYaw(yaw);
                this.setPitch(pitch);

                this.setHeadYaw(yaw);
                this.setBodyYaw(yaw);
            }
        }
    }

    @Override
    protected void initGoals() {

        this.goalSelector.add(
                0,
                new FlyAroundTargetGoal(this)
        );

        this.goalSelector.add(
                1,
                new SpitBurstGoal(this)
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

    public static boolean canSpawn(
            EntityType<SnoterEntity> type,
            ServerWorldAccess world,
            SpawnReason reason,
            BlockPos pos,
            Random random
    ) {

        ServerWorld serverWorld = world.toServerWorld();

        StageData stageData = StageData.get(serverWorld);

        if (stageData.getStage() != 1) {
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

    public boolean canShoot() {
        return burstCooldown <= 0;
    }

    public void resetShootCooldown() {

        burstCooldown =
                40 + this.random.nextInt(41);
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
    public int getOrbitDirection() {
        return orbitDirection;
    }

    public void reverseOrbitDirection() {
        orbitDirection *= -1;
    }
}