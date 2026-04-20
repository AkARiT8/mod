package dev.hellmod.mixin.Accessor;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AnvilScreenHandler.class)
public interface AnvilScreenHandlerAccessor {

    @Accessor("levelCost")
    Property getLevelCost();

}