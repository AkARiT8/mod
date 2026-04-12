package dev.hellmod.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class CustomHeart extends CustoModItem {

    private static final int MAX_EXTRA_HEARTS = 4;

    public CustomHeart(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        if (!world.isClient && user instanceof PlayerEntity player) {

            EntityAttributeInstance attr =
                    player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

            if (attr == null) return super.finishUsing(stack, world, user);

            double baseHealth = 20.0;
            double currentMax = attr.getBaseValue();

            double extraHearts = (currentMax - baseHealth) / 2.0;

            if (extraHearts >= MAX_EXTRA_HEARTS) {
                return super.finishUsing(stack, world, user);
            }

            double newMaxHealth = currentMax + 2.0;

            attr.setBaseValue(newMaxHealth);

            player.addStatusEffect(
                    new StatusEffectInstance(StatusEffects.NAUSEA, 500, 5)
            );
        }

        return super.finishUsing(stack, world, user);
    }

    private void applyHealth(PlayerEntity player, int hearts) {

        EntityAttributeInstance attr =
                player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

        if (attr == null) return;

        double baseHealth = 20.0;

        double newMaxHealth = baseHealth + (hearts * 2.0);

        attr.setBaseValue(newMaxHealth);

    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}