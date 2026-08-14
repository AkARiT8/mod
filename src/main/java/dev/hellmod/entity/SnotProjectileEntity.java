package dev.hellmod.entity;

import dev.hellmod.effects.ModEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class SnotProjectileEntity extends ThrownItemEntity {

    public SnotProjectileEntity(
            EntityType<? extends SnotProjectileEntity> entityType,
            World world
    ) {
        super(entityType, world);
    }

    public SnotProjectileEntity(
            World world,
            LivingEntity owner
    ) {
        super(ModEntities.SNOT_PROJECTILE, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.SLIME_BALL;
    }

    @Override
    protected void onEntityHit(EntityHitResult hitResult) {

        super.onEntityHit(hitResult);

        if (hitResult.getEntity() instanceof LivingEntity target) {

            target.damage(
                    getDamageSources().mobProjectile(
                            this,
                            (LivingEntity)getOwner()
                    ),
                    12.0f
            );

            target.addStatusEffect(
                    new StatusEffectInstance(
                            Registries.STATUS_EFFECT
                                    .getEntry(ModEffects.STICKY),
                            8 * 20,
                            0
                    )
            );
        }

        discard();
    }

    @Override
    protected void onCollision(HitResult hitResult) {

        super.onCollision(hitResult);

        discard();
    }
}