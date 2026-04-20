package dev.hellmod.entity;

import dev.hellmod.items.ModItems;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class BossZombieEntity extends ZombieEntity {

    private final ServerBossBar bossBar;

    public BossZombieEntity(EntityType<? extends ZombieEntity> type, World world) {
        super(type, world);

        this.bossBar = new ServerBossBar(
                this.getDisplayName(),
                BossBar.Color.RED,
                BossBar.Style.PROGRESS
        );
    }

    @Override
    protected void initEquipment(net.minecraft.util.math.random.Random random,
                                 net.minecraft.world.LocalDifficulty difficulty) {

        ItemStack sword = new ItemStack(ModItems.NETHER_INFUSED_AXE);
        sword.addEnchantment(Enchantments.SHARPNESS, 5);
        this.equipStack(EquipmentSlot.MAINHAND, sword);

        ItemStack helmet = new ItemStack(Items.NETHERITE_HELMET);
        helmet.addEnchantment(Enchantments.BLAST_PROTECTION, 100);
        helmet.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 3);

        ItemStack chest = new ItemStack(ModItems.NETHER_INFUSED_CHESTPLATE);
        chest.addEnchantment(Enchantments.BLAST_PROTECTION, 100);
        chest.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 3);

        ItemStack boots = new ItemStack(Items.NETHERITE_BOOTS);
        boots.addEnchantment(Enchantments.BLAST_PROTECTION, 100);
        boots.addEnchantment(Enchantments.FEATHER_FALLING, 4);
        boots.addEnchantment(Enchantments.PROJECTILE_PROTECTION, 3);

        this.equipStack(EquipmentSlot.HEAD, helmet);
        this.equipStack(EquipmentSlot.CHEST, chest);
        this.equipStack(EquipmentSlot.FEET, boots);
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

        this.initEquipment(world.getRandom(), difficulty);

        this.requestTeleport(this.getX(), this.getY() + 500, this.getZ());

        if (!world.toServerWorld().isClient()) {
            world.toServerWorld().setTimeOfDay(15000);
        }

        return data;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient) {
            bossBar.setPercent(this.getHealth() / this.getMaxHealth());
            bossBar.setName(this.getDisplayName());
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {


        if (source.isOf(DamageTypes.FALL)) {


            this.getWorld().createExplosion(
                    this,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    10.0f,
                    World.ExplosionSourceType.NONE
            );
            return false;
        }

        return super.damage(source, amount);
    }
    @Override
    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
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

    public static DefaultAttributeContainer.Builder createBossAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 150.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.GENERIC_ARMOR, 10.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0);
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }
    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public void setBaby(boolean baby) {
        super.setBaby(false);
    }
}