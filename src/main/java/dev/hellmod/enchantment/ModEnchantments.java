package dev.hellmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    public static final Enchantment ENLIGHTENMENT = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("hellmod", "enlightenment"),
            new Enlightenment()
    );
    public static final Enchantment KNOWLEDGE = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("hellmod", "knowledge"),
            new Knowledge()
    );


    public static void register() {}
}