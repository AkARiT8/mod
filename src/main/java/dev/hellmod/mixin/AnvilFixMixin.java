package dev.hellmod.mixin;

import dev.hellmod.mixin.Accessor.AnvilScreenHandlerAccessor;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(AnvilScreenHandler.class)
public abstract class AnvilFixMixin {

    @Inject(method = "updateResult", at = @At("TAIL"))
    private void fixEnchantments(CallbackInfo ci) {

        AnvilScreenHandler self = (AnvilScreenHandler)(Object)this;

        ItemStack base = self.getSlot(0).getStack();
        ItemStack output = self.getSlot(2).getStack();

        if (base.isEmpty() || output.isEmpty()) return;

        ItemEnchantmentsComponent enchants =
                output.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);

        boolean invalid = false;

        for (RegistryEntry<Enchantment> entry : enchants.getEnchantments()) {
            Enchantment enchantment = entry.value();

            if (!enchantment.isAcceptableItem(base)) {
                invalid = true;
                break;
            }
        }

        if (invalid) {

            self.getSlot(2).setStack(ItemStack.EMPTY);

            ((AnvilScreenHandlerAccessor)(Object)this)
                    .getLevelCost()
                    .set(-1);
        }
    }
}