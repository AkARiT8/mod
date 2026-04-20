package dev.hellmod.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TrueAmethystBowItem extends BowItem {

    public TrueAmethystBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);

        if (!world.isClient) {
            for (var entity : world.getEntitiesByClass(ArrowEntity.class,
                    user.getBoundingBox().expand(3.0),
                    e -> e.getOwner() == user)) {

                entity.setDamage(entity.getDamage() * 2);
            }
        }
    }
}