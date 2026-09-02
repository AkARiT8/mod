package dev.hellmod.entity;

import dev.hellmod.items.ModItems;
import dev.hellmod.items.custom.TrueAmethystBowItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

public class DoomSkeletonEntity extends SkeletonEntity {

    public DoomSkeletonEntity(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return createAbstractSkeletonAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0);
    }

    @Override
    protected void initEquipment(Random random, LocalDifficulty difficulty) {
        ItemStack bow = new ItemStack(ModItems.TRUE_AMETHYST_BOW);

        bow.addEnchantment(
                net.minecraft.enchantment.Enchantments.POWER,
                60
        );

        this.equipStack(
                EquipmentSlot.MAINHAND,
                bow
        );

        this.handDropChances[EquipmentSlot.MAINHAND.getEntitySlotId()] = 0.0F;
    }

    @Override
    public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
        return weapon instanceof TrueAmethystBowItem;
    }
}