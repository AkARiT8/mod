package dev.hellmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.tag.ItemTags;

public class Knowledge extends Enchantment {

    public Knowledge() {
        super(Enchantment.properties(
                ItemTags.PICKAXES,
                10,
                5,
                Enchantment.leveledCost(10, 10),
                Enchantment.leveledCost(20, 10),
                2,
                EquipmentSlot.MAINHAND
        ));
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.SILK_TOUCH;
    }
}