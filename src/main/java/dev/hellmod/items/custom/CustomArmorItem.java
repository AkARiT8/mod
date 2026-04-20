package dev.hellmod.items.custom;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.item.ArmorMaterial;

public class CustomArmorItem extends ArmorItem {

    public CustomArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }
    @Override
    public int getEnchantability() {
        return 15;
    }
}