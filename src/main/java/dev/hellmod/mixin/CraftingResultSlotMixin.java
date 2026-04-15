package dev.hellmod.mixin;

import dev.hellmod.HellMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public abstract class CraftingResultSlotMixin {

    @Inject(method = "canTakeItems", at = @At("HEAD"), cancellable = true)
    private void blockCraft(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {

        Slot slot = (Slot)(Object)this;

        if (!(slot instanceof CraftingResultSlot)) return;

        ItemStack stack = slot.getStack();

        if (stack.isEmpty()) return;

        if (HellMod.manager.isBlocked(stack, player)) {

            player.sendMessage(
                    Text.literal("Recipe Locked").formatted(Formatting.RED),
                    true
            );

            cir.setReturnValue(false);
        }
    }
}