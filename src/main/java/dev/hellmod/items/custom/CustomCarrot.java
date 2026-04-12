package dev.hellmod.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CustomCarrot extends CustoModItem {
    public CustomCarrot(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        if (!world.isClient) {


            user.addStatusEffect(
                    new StatusEffectInstance(StatusEffects.SATURATION, 18000, 4)
            );
        }

        return super.finishUsing(stack, world, user);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
