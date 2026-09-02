package dev.hellmod.entity;

import dev.hellmod.effects.ModEffects;
import dev.hellmod.entity.IA.DoomCreeperBeamMoveGoal;
import dev.hellmod.mixin.Accessor.CreeperAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;

public class DoomCreeperEntity extends CreeperEntity {

    private PlayerEntity beamTarget;
    private int beamChargeTime = 0;
    private static final int BEAM_CHARGE_DURATION = 60;
    private static final double BEAM_MAX_RANGE = 32.0;
    private boolean beamActive = false;
    private static final int BEAM_CHARGE_TIME = 15;
    private static final double BEAM_RANGE = 14.0;

    private static final TrackedData<Boolean> BEAM_ACTIVE =
            DataTracker.registerData(
                    DoomCreeperEntity.class,
                    TrackedDataHandlerRegistry.BOOLEAN
            );

    private static final TrackedData<Integer> BEAM_TARGET_ID =
            DataTracker.registerData(
                    DoomCreeperEntity.class,
                    TrackedDataHandlerRegistry.INTEGER
            );

    private static final TrackedData<Integer> BEAM_CHARGE =
            DataTracker.registerData(
                    DoomCreeperEntity.class,
                    TrackedDataHandlerRegistry.INTEGER
            );

    public DoomCreeperEntity(
            EntityType<? extends CreeperEntity> entityType,
            World world
    ) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return CreeperEntity.createCreeperAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.GENERIC_ARMOR, 20);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);

        builder.add(BEAM_ACTIVE, false);
        builder.add(BEAM_TARGET_ID, -1);
        builder.add(BEAM_CHARGE, 0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(5, new DoomCreeperBeamMoveGoal(this));
        this.goalSelector.add(4, new dev.hellmod.entity.goal.DoomCreeperBeamLookGoal(this));

    }

    public boolean isBeamActive() {
        return this.dataTracker.get(BEAM_ACTIVE);
    }

    public PlayerEntity getBeamTarget() {
        int id = this.dataTracker.get(BEAM_TARGET_ID);

        if (id < 0) {
            return null;
        }

        Entity entity = this.getWorld().getEntityById(id);

        if (entity instanceof PlayerEntity player) {
            return player;
        }

        return null;
    }

    public int getBeamChargeTime() {
        return this.dataTracker.get(BEAM_CHARGE);
    }

    public float getBeamProgress(float tickDelta) {
        return Math.min(
                1.0F,
                (this.getBeamChargeTime() + tickDelta)
                        / (float) BEAM_CHARGE_TIME
        );
    }

    private void setBeamActive(boolean active) {
        this.beamActive = active;
        this.dataTracker.set(BEAM_ACTIVE, active);
    }

    private void setBeamTarget(PlayerEntity target) {
        this.beamTarget = target;

        this.dataTracker.set(
                BEAM_TARGET_ID,
                target == null ? -1 : target.getId()
        );
    }

    private void setBeamChargeTime(int time) {
        this.beamChargeTime = time;
        this.dataTracker.set(BEAM_CHARGE, time);
    }

    @Override
    public boolean damage(
            DamageSource source,
            float amount
    ) {
        boolean damaged = super.damage(source, amount);

        if (damaged && !this.getWorld().isClient) {

            if (!this.isIgnited()) {

                setBeamActive(false);
                setBeamTarget(null);
                setBeamChargeTime(0);

                ((CreeperAccessor) this).setCurrentFuseTime(0);

                this.setAiDisabled(true);

                this.ignite();

                this.goalSelector.disableControl(Goal.Control.MOVE);
            }
        }

        return damaged;
    }


    private PlayerEntity findBeamTarget() {
        return this.getWorld().getPlayers().stream()
                .filter(player -> !player.isSpectator())
                .filter(player -> !player.isCreative())
                .filter(LivingEntity::isAlive)
                .filter(player -> this.squaredDistanceTo(player) <= BEAM_RANGE * BEAM_RANGE)
                .min((a, b) -> Double.compare(
                        this.squaredDistanceTo(a),
                        this.squaredDistanceTo(b)
                ))
                .orElse(null);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient) {
            return;
        }

        if (this.isIgnited()) {
            setBeamActive(false);
            setBeamTarget(null);
            setBeamChargeTime(0);
            return;
        }

        if (!beamActive) {

            PlayerEntity target = findBeamTarget();

            if (target != null) {
                setBeamActive(true);
                setBeamTarget(target);
                setBeamChargeTime(0);
            }

            return;
        }

        if (beamTarget == null
                || !beamTarget.isAlive()
                || beamTarget.isSpectator()
                || this.squaredDistanceTo(beamTarget)
                > BEAM_RANGE * BEAM_RANGE) {

            setBeamActive(false);
            setBeamTarget(null);
            setBeamChargeTime(0);

            return;
        }

        setBeamChargeTime(beamChargeTime + 1);

        if (beamChargeTime >= BEAM_CHARGE_TIME) {

            fireBeam();

            setBeamChargeTime(0);
        }
    }

    private void fireBeam() {
        if (beamTarget == null) {
            return;
        }

        if (!beamTarget.isAlive()
                || beamTarget.isCreative()
                || beamTarget.isSpectator()) {
            return;
        }

        if (this.squaredDistanceTo(beamTarget) > BEAM_RANGE * BEAM_RANGE) {
            return;
        }

        float damage = 8.0F;

        int beamCount = (int) this.getWorld().getEntitiesByClass(
                DoomCreeperEntity.class,
                this.getBoundingBox().expand(BEAM_RANGE),
                creeper -> creeper.isBeamActive()
                        && creeper.getBeamTarget() == beamTarget
        ).size();

        damage *= beamCount;

        beamTarget.damage(
                this.getWorld().getDamageSources().mobAttack(this),
                damage
        );

        beamTarget.addStatusEffect(
                new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 0)
        );

        beamTarget.addStatusEffect(
                new StatusEffectInstance(
                        Registries.STATUS_EFFECT.getEntry(ModEffects.FRAGILITY),
                        100,
                        0
                )
        );

        beamTarget.addStatusEffect(
                new StatusEffectInstance(
                        Registries.STATUS_EFFECT.getEntry(ModEffects.CONTAMINATION),
                        6000,
                        0
                )
        );


    }

}