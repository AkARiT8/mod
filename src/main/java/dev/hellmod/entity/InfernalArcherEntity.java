package dev.hellmod.entity;

import dev.hellmod.items.ModItems;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

public class InfernalArcherEntity extends WitherSkeletonEntity {

    public InfernalArcherEntity(EntityType<? extends WitherSkeletonEntity> type,
                                World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return WitherSkeletonEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 35.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.250D)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0D);
    }

    @Override
    protected void initEquipment(Random random,
                                 LocalDifficulty difficulty) {

        this.equipStack(
                EquipmentSlot.HEAD,
                new ItemStack(Items.DIAMOND_HELMET)
        );


        this.equipStack(
                EquipmentSlot.CHEST,
                new ItemStack(Items.IRON_CHESTPLATE)
        );

        this.equipStack(
                EquipmentSlot.FEET,
                new ItemStack(ModItems.NETHER_INFUSED_BOOTS)
        );

        ItemStack bow = new ItemStack(Items.BOW);

        bow.addEnchantment(
                Enchantments.POWER,
                22
        );

        this.equipStack(
                EquipmentSlot.MAINHAND,
                bow
        );

        this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0f);
        this.setEquipmentDropChance(EquipmentSlot.HEAD, 0f);
        this.setEquipmentDropChance(EquipmentSlot.CHEST, 0f);
        this.setEquipmentDropChance(EquipmentSlot.LEGS, 0f);
        this.setEquipmentDropChance(EquipmentSlot.FEET, 0f);
    }

}