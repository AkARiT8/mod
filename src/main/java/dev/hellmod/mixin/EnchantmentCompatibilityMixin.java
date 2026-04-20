package dev.hellmod.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentCompatibilityMixin {

    @Inject(method = "canCombine", at = @At("HEAD"), cancellable = true)
    private void allowCustomCombos(Enchantment other, CallbackInfoReturnable<Boolean> cir) {

        Enchantment self = (Enchantment)(Object)this;


        boolean selfDamage =
                self == Enchantments.SHARPNESS ||
                        self == Enchantments.SMITE ||
                        self == Enchantments.BANE_OF_ARTHROPODS;

        boolean otherDamage =
                other == Enchantments.SHARPNESS ||
                        other == Enchantments.SMITE ||
                        other == Enchantments.BANE_OF_ARTHROPODS;

        boolean selfProtection =
                self == Enchantments.PROTECTION ||
                        self == Enchantments.FIRE_PROTECTION ||
                        self == Enchantments.BLAST_PROTECTION ||
                        self == Enchantments.PROJECTILE_PROTECTION;

        boolean otherProtection =
                other == Enchantments.PROTECTION ||
                        other == Enchantments.FIRE_PROTECTION ||
                        other == Enchantments.BLAST_PROTECTION ||
                        other == Enchantments.PROJECTILE_PROTECTION;

        if ((selfDamage && otherDamage) || (selfProtection && otherProtection)) {
            cir.setReturnValue(true);
        }
    }
}