package dev.hellmod.items;

import dev.hellmod.HellMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item PURE_IRON_INGOT = registerItem("pure_iron_ingot",new Item(new Item.Settings()));

    private static Item registerItem(String itemid, Item item){
        return Registry.register(Registries.ITEM, new Identifier(HellMod.MODID, itemid), item);
    }

    public static void registerItems(){
        HellMod.LOGGER.info("Registing items");
    }
}
