package dev.hellmod.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.nbt.NbtCompound;

public class PlayerUtils {

    public static void syncHealth(PlayerEntity player) {

        NbtCompound nbt = player.writeNbt(new NbtCompound());

        int hearts = nbt.getInt("extra_hearts");

        int MAX_EXTRA_HEARTS = 12;

        if (hearts > MAX_EXTRA_HEARTS) {
            hearts = MAX_EXTRA_HEARTS;
            nbt.putInt("extra_hearts", hearts);
            player.readNbt(nbt);
        }

        EntityAttributeInstance attr =
                player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

        if (attr == null) return;

        double baseHealth = 20.0;
        double newMaxHealth = baseHealth + (hearts * 2.0);

        attr.setBaseValue(newMaxHealth);
    }
}