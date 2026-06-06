package dev.hellmod.mixin;

import dev.hellmod.mixin.Accessor.AnvilScreenHandlerAccessor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Inject(method = "canTakeOutput", at = @At("HEAD"), cancellable = true)
    private void allowExpensiveOutput(PlayerEntity player, boolean present, CallbackInfoReturnable<Boolean> cir) {

        if (present) {
            cir.setReturnValue(true);
        }
    }

    @Redirect(
            method = "updateResult",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/screen/Property;set(I)V"
            )
    )
    private void clampCost(Property property, int value) {

        if (value >= 40) {
            property.set(39);
        } else {
            property.set(value);
        }
    }
}