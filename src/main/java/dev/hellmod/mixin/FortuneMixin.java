package dev.hellmod.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class FortuneMixin {

    @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
    private void modifyMaxLevel(CallbackInfoReturnable<Integer> cir) {

        Enchantment self = (Enchantment)(Object)this;

        if (self == Enchantments.FORTUNE) {
            cir.setReturnValue(5);
        }
    }
}