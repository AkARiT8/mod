package dev.hellmod.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class BossPhantomEntity extends PhantomEntity {

    private final ServerBossBar bossBar;
    private int attackTicks = 0;

    public BossPhantomEntity(EntityType<? extends PhantomEntity> type, World world) {
        super(type, world);

        this.bossBar = new ServerBossBar(
                this.getDisplayName(),
                BossBar.Color.PURPLE,
                BossBar.Style.PROGRESS
        );

    }

    @Override
    public EntityData initialize(
            ServerWorldAccess world,
            LocalDifficulty difficulty,
            SpawnReason spawnReason,
            EntityData entityData
    ) {
        EntityData data = super.initialize(world, difficulty, spawnReason, entityData);

        this.setHealth(this.getMaxHealth());

        this.setPhantomSize(8);

        if (!world.toServerWorld().isClient()) {
            world.toServerWorld().setTimeOfDay(15000);
        }

        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, Integer.MAX_VALUE, 1));
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, Integer.MAX_VALUE, 1));

        double y = world.getTopY(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING,
                (int)this.getX(),
                (int)this.getZ());

        this.requestTeleport(this.getX(), y + 150, this.getZ());

        return data;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient) return;

        bossBar.setPercent(this.getHealth() / this.getMaxHealth());
        bossBar.setName(this.getDisplayName());

        attackTicks++;

        ServerWorld world = (ServerWorld) this.getWorld();

        if (attackTicks % 100 == 0) {

            for (ServerPlayerEntity player : world.getPlayers()) {

                double dist = this.squaredDistanceTo(player);

                if (dist <= 40 * 40) {


                    player.damage(
                            this.getDamageSources().mobAttack(this),
                            8.0f
                    );

                    player.addStatusEffect(
                            new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0)
                    );
                }
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {

        if (source.isOf(DamageTypes.FALL)) {
            return false;
        }

        return super.damage(source, amount);
    }

    @Override
    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {}

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        bossBar.removePlayer(player);
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        bossBar.clearPlayers();
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 250.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0);
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }
}