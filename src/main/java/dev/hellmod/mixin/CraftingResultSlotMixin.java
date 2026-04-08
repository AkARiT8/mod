package dev.hellmod.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import dev.hellmod.HellMod;

@Mixin(Slot.class)
public abstract class CraftingResultSlotMixin {

    @Shadow public abstract ItemStack getStack();

    @Inject(method = "canTakeItems", at = @At("HEAD"), cancellable = true)
    private void blockDiamondSword(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (HellMod.manager.isBlocked(this.getStack(),player)) {
            cir.setReturnValue(false);
            player.sendMessage(
                    Text.literal("Recipe Locked").formatted(Formatting.RED),
                    true
            );
        }
    }
}