package dev.hellmod.mixin.mobMixin;

import dev.hellmod.util.VariantHolder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class CreeperExplodeMixin {

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void hellmod$customExplosion(CallbackInfo ci) {

        CreeperEntity creeper = (CreeperEntity)(Object)this;
        World world = creeper.getWorld();

        if (!world.isClient) {

            float power = ((VariantHolder) creeper).hellmod$getExplosionPower();
            String variant = ((VariantHolder) creeper).hellmod$getVariant();

            world.createExplosion(
                    creeper,
                    creeper.getX(),
                    creeper.getY(),
                    creeper.getZ(),
                    power,
                    World.ExplosionSourceType.MOB
            );
            System.out.println("VARIANT: " + variant);
            if ("debuff".equals(variant)) {

                double radius = power * 2.0;

                var entities = world.getEntitiesByClass(
                        LivingEntity.class,
                        creeper.getBoundingBox().expand(radius),
                        e -> e instanceof PlayerEntity
                );

                for (LivingEntity target : entities) {

                    double distance = creeper.distanceTo(target);

                    float damage = power * (1.0f - (float)(distance / radius));

                    if (damage > 0.1f) {

                        PlayerEntity player = (PlayerEntity) target;

                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.POISON, 100, 1));

                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.SLOWNESS, 100, 4));

                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.WEAKNESS, 100, 0));

                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.BLINDNESS, 100, 0));

                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.WITHER, 100, 0));

                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.NAUSEA, 100, 5));



                    }
                }
            }

            creeper.discard();
        }

        ci.cancel();
    }
}