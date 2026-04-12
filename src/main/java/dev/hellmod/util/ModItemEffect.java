package dev.hellmod.util;

import dev.hellmod.items.ModItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class ModItemEffect {

    public static void tick(PlayerEntity player) {

        ItemStack main = player.getMainHandStack();
        ItemStack off = player.getOffHandStack();

        if (isSpeedTotem(main) || isSpeedTotem(off)) {

            player.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.SPEED,
                            5,
                            0,
                            true,
                            false,
                            true
                    )
            );
        }
    }

    public static boolean isSpeedTotem(ItemStack stack) {
        return stack.isOf(ModItems.SPEED_TOTEM_OF_UNDYING);
    }
}