package dev.hellmod.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class InvencibilityRune extends CustoModItem {

    public InvencibilityRune(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);

        if (user.getItemCooldownManager().isCoolingDown(this)) {
            return TypedActionResult.fail(stack);
        }

        if (!world.isClient) {

            Box area = user.getBoundingBox().expand(3.0);

            List<PlayerEntity> players = world.getEntitiesByClass(
                    PlayerEntity.class,
                    area,
                    p -> p != user
            );

            for (PlayerEntity player : players) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE , 140, 10));
            }
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE , 140, 10));

            double radius = 3.0;

            for (int i = 0; i < 360; i += 30) {
                double rad = Math.toRadians(i);

                double x = user.getX() + Math.cos(rad) * radius;
                double z = user.getZ() + Math.sin(rad) * radius;
                double y = user.getY() + 0.1;

                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.POOF,
                        x, y, z,
                        1,
                        0, 0, 0,
                        0
                );
            }


            world.playSound(
                    null,
                    user.getBlockPos(),
                    SoundEvents.BLOCK_ANVIL_FALL,
                    SoundCategory.PLAYERS,
                    1.0f,
                    1.0f
            );
        }

        user.getItemCooldownManager().set(this, 1000);

        return TypedActionResult.success(stack, world.isClient());
    }
}