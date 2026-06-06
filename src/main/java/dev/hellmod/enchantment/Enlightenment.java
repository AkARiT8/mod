package dev.hellmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.tag.ItemTags;

public class Enlightenment extends Enchantment {

    public Enlightenment() {
        super(Enchantment.properties(
                ItemTags.SWORDS,
                10,
                5,
                Enchantment.leveledCost(10, 10),
                Enchantment.leveledCost(20, 10),
                2,
                EquipmentSlot.MAINHAND
        ));
    }
}