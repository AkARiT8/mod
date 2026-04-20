package dev.hellmod.mixin;

import dev.hellmod.mixin.Accessor.AnvilScreenHandlerAccessor;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public class AnvilCostMixin {

    @Inject(method = "updateResult", at = @At("TAIL"))
    private void modifyCost(CallbackInfo ci) {

        AnvilScreenHandler self = (AnvilScreenHandler)(Object)this;

        ItemStack result = self.getSlot(2).getStack();

        if (result.isEmpty()) {
            return;
        }

        Property levelCost = ((AnvilScreenHandlerAccessor)(Object)this).getLevelCost();

        int cost = levelCost.get();

        if (cost <= 0) {
            return;
        }

        int newCost = Math.max(1, cost / 2);

        levelCost.set(newCost);
    }
}