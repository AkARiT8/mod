package dev.hellmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.hellmod.stage.modifier.LootDropHandler;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivinEntityMixin {

    @Inject(method = "dropLoot", at = @At("TAIL"))
    private void hellmod$onDropLoot(DamageSource source, boolean causedByPlayer, CallbackInfo ci) {

        LivingEntity entity = (LivingEntity)(Object)this;

        if (!(entity.getWorld() instanceof ServerWorld world)) return;

        LootDropHandler.onDeath(entity, world);
    }
}