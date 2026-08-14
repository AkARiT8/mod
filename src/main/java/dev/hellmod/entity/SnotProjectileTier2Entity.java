package dev.hellmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class SnotProjectileTier2Entity extends SnotProjectileEntity{
    public SnotProjectileTier2Entity(EntityType<? extends SnotProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public SnotProjectileTier2Entity(World world, LivingEntity owner) {
        super(world, owner);
    }
}
