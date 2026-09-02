package dev.hellmod.entity;

import dev.hellmod.items.ModItems;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

public class DoomZombieEntity extends ZombieEntity {

    private boolean specialAttackUsed;
    private int specialAttackTicks;

    public DoomZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.GENERIC_ARMOR, 20);
    }

    @Override
    public void tick() {
        if (!this.getWorld().isClient) {
            PlayerEntity target = this.getTarget() instanceof PlayerEntity player ? player : null;

            if (!specialAttackUsed && specialAttackTicks == 0 && target != null && target.isAlive() && this.squaredDistanceTo(target) <= 16.0) {
                specialAttackTicks = 10;
                this.getNavigation().stop();
            }

            if (specialAttackTicks > 0) {
                specialAttackTicks--;

                this.getNavigation().stop();
                this.getLookControl().lookAt(target, 30.0F, 30.0F);

                if (specialAttackTicks == 0 && target != null && target.isAlive()) {
                    Vec3d direction = target.getPos().subtract(this.getPos()).normalize();

                    this.setVelocity(
                            direction.x * 0.75,
                            0.35,
                            direction.z * 0.75
                    );

                    this.velocityDirty = true;
                    this.swingHand(Hand.MAIN_HAND);

                    float damage = (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 2.0F;
                    target.damage(this.getDamageSources().mobAttack(this), damage);

                    specialAttackUsed = true;
                }
            }
        }

        super.tick();
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("SpecialAttackUsed", specialAttackUsed);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        specialAttackUsed = nbt.getBoolean("SpecialAttackUsed");
    }

    @Override
    protected void initEquipment(Random random, LocalDifficulty difficulty) {
        super.initEquipment(random, difficulty);

        ItemStack mainAxe = new ItemStack(ModItems.TRUE_AMETHYST_AXE);
        ItemStack offAxe = new ItemStack(ModItems.TRUE_AMETHYST_AXE);

        mainAxe.addEnchantment(Enchantments.SHARPNESS, 20);
        offAxe.addEnchantment(Enchantments.SHARPNESS, 20);

        this.equipStack(EquipmentSlot.MAINHAND, mainAxe);
        this.equipStack(EquipmentSlot.OFFHAND, offAxe);

        this.handDropChances[EquipmentSlot.MAINHAND.getEntitySlotId()] = 0.0F;
        this.handDropChances[EquipmentSlot.OFFHAND.getEntitySlotId()] = 0.0F;
    }
}