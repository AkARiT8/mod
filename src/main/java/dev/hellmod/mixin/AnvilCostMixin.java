package dev.hellmod.mixin;

import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.screen.Property;

@Mixin(AnvilScreenHandler.class)
public class AnvilCostMixin {

    @Inject(method = "updateResult", at = @At("TAIL"))
    private void modifyCost(CallbackInfo ci) {

        Property levelCost = ((AnvilScreenHandlerAccessor)(Object)this).getLevelCost();

        int cost = levelCost.get();

        int newCost = Math.max(1, cost / 2);

        levelCost.set(newCost);

    }
}