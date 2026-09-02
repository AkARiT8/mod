package dev.hellmod.mixin;

import dev.hellmod.items.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.entity.ai.goal.BowAttackGoal;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityMixin {

    @Redirect(
            method = "updateAttackType",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/ProjectileUtil;getHandPossiblyHolding(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/Item;)Lnet/minecraft/util/Hand;"
            )
    )
    private Hand hellmod$recognizeCustomBow(
            LivingEntity entity,
            Item item
    ) {
        if (item == Items.BOW
                && entity.getMainHandStack().isOf(ModItems.TRUE_AMETHYST_BOW)) {
            return Hand.MAIN_HAND;
        }

        return net.minecraft.entity.projectile.ProjectileUtil
                .getHandPossiblyHolding(entity, item);
    }

    @Redirect(
            method = "updateAttackType",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
            )
    )
    private boolean hellmod$customBowIsBow(
            ItemStack stack,
            Item item
    ) {
        if (item == Items.BOW
                && stack.isOf(ModItems.TRUE_AMETHYST_BOW)) {
            return true;
        }

        return stack.isOf(item);
    }
}