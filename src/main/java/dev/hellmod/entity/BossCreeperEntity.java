package dev.hellmod.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class BossCreeperEntity extends CreeperEntity {

    private final ServerBossBar bossBar;
    private int lifeTicks = 0;

    public BossCreeperEntity(EntityType<? extends CreeperEntity> type, World world) {
        super(type, world);

        this.bossBar = new ServerBossBar(
                this.getDisplayName(),
                BossBar.Color.GREEN,
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

        this.requestTeleport(this.getX(), this.getY() + 150, this.getZ());

        if (!world.toServerWorld().isClient()) {
            world.toServerWorld().setTimeOfDay(15000);
        }

        return data;
    }



    @Override
    protected void initGoals() {}

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient) return;

        lifeTicks++;

        ServerWorld world = (ServerWorld) this.getWorld();

        bossBar.setPercent(this.getHealth() / this.getMaxHealth());

        int maxTime = 6000;
        int secondsLeft = (maxTime - lifeTicks) / 20;

        bossBar.setName(
                net.minecraft.text.Text.literal("☢ Boss Creeper - " + secondsLeft + "s")
        );

        if (lifeTicks % 600 == 200) {

            var random = world.getRandom();

            EntityType<? extends MobEntity>[] pool = new EntityType[]{
                    EntityType.ZOMBIE,
                    EntityType.SKELETON,
                    EntityType.CREEPER,
                    EntityType.SPIDER,
                    EntityType.PHANTOM
            };

            for (int i = 0; i < 5; i++) {

                EntityType<? extends MobEntity> type = pool[random.nextInt(pool.length)];

                MobEntity mob = type.create(world);

                if (mob != null) {

                    double x = this.getX() + random.nextInt(10) - 5;
                    double z = this.getZ() + random.nextInt(10) - 5;
                    double y = this.getY();

                    if (type == EntityType.PHANTOM) {
                        y += 15 + random.nextInt(10);
                    }

                    mob.refreshPositionAndAngles(x, y, z, 0, 0);

                    world.spawnEntity(mob);
                }
            }
        }

        if (lifeTicks % 200 == 0) {

            float radius = 10.0f;

            world.playSound(
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    SoundEvents.ENTITY_GENERIC_EXPLODE.value(),
                    SoundCategory.HOSTILE,
                    4.0f,
                    0.6f
            );

            world.spawnParticles(
                    ParticleTypes.EXPLOSION,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    1, 0, 0, 0, 0
            );

            for (PlayerEntity player : world.getPlayers()) {

                double dist = this.squaredDistanceTo(player);

                if (dist <= radius * radius) {

                    player.damage(
                            this.getDamageSources().explosion(this, this),
                            50.0f
                    );

                    double dx = player.getX() - this.getX();
                    double dz = player.getZ() - this.getZ();

                    player.addVelocity(dx * 0.2, 0.5, dz * 0.2);
                }
            }
        }

        if (lifeTicks >= maxTime) {

            world.createExplosion(
                    this,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    50.0f,
                    true,
                    World.ExplosionSourceType.MOB
            );

            this.discard();
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {

        if (source.isOf(DamageTypes.EXPLOSION) || source.isOf(DamageTypes.PLAYER_EXPLOSION)) {
            return false;
        }

        if (source.getAttacker() instanceof MobEntity) {
            return false;
        }

        if (source.isOf(DamageTypes.FALL)) {
            return false;
        }

        return super.damage(source, amount);
    }

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
        return CreeperEntity.createCreeperAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1000.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0);
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }
}