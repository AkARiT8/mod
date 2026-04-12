package dev.hellmod.items;

import dev.hellmod.HellMod;
import dev.hellmod.items.custom.CustoModItem;
import dev.hellmod.items.custom.CustomCarrot;
import dev.hellmod.items.custom.CustomHeart;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {

    public static final Item PURE_IRON_INGOT = registerItem("pure_iron_ingot",new CustoModItem(new Item.Settings()));
    public static final Item PURE_GOLD_INGOT = registerItem("pure_gold_ingot",new CustoModItem(new Item.Settings()));
    public static final Item PURE_NETHERITE_INGOT = registerItem("pure_netherite_ingot",new CustoModItem(new Item.Settings()));
    public static final Item PURE_EMERALD = registerItem("pure_emerald",new CustoModItem(new Item.Settings()));
    public static final Item PURE_DIAMOND = registerItem("pure_diamond",new CustoModItem(new Item.Settings()));

    public static final Item ENCHANTED_GOLDEN_CARROT = registerItem("enchanted_golden_carrot",
            new CustomCarrot(new Item.Settings().food(
                    new FoodComponent(4,0.3f,false,1.6f,List.of())
            )));

    public static final Item SPEED_TOTEM_OF_UNDYING = registerItem("speed_totem_of_undying",new CustoModItem(new Item.Settings().maxCount(1)));

    public static final Item BASIC_HARDCORE_HEART = registerItem("basic_hardcore_heart",
            new CustomHeart(new Item.Settings().food(
                    new FoodComponent(0,0.0f,false,1.6f,List.of())
            )));

    public static final Item OVERWORLD_ESSENCE = registerItem("overworld_essence",new CustoModItem(new Item.Settings()));
    public static final Item BLAZE_MAIN_ROD = registerItem("blaze_main_rod",new CustoModItem(new Item.Settings()));
    public static final Item MAGMA_CUBE_CORE = registerItem("magma_cube_core",new CustoModItem(new Item.Settings()));

    public static final Item NETHER_INFUSED_INGOT = registerItem("nether_infused_ingot",new CustoModItem(new Item.Settings()));



    public static final Item NETHER_INFUSED_HELMET = registerItem(
            "nether_infused_helmet",
            new ArmorItem(
                    ModArmorMaterials.NETHER_INFUSED_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Settings().maxCount(1).fireproof()
            )
    );
    public static final Item NETHER_INFUSED_CHESTPLATE = registerItem(
            "nether_infused_chestplate",
            new ArmorItem(
                    ModArmorMaterials.NETHER_INFUSED_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxCount(1).fireproof()
            )
    );
    public static final Item NETHER_INFUSED_LEGGINGS = registerItem(
            "nether_infused_leggings",
            new ArmorItem(
                    ModArmorMaterials.NETHER_INFUSED_ARMOR_MATERIAL,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxCount(1).fireproof()
            )
    );
    public static final Item NETHER_INFUSED_BOOTS = registerItem(
            "nether_infused_boots",
            new ArmorItem(
                    ModArmorMaterials.NETHER_INFUSED_ARMOR_MATERIAL,
                    ArmorItem.Type.BOOTS,
                    new Item.Settings().maxCount(1).fireproof()
            )
    );



    private static Item registerItem(String itemid, Item item){
        return Registry.register(Registries.ITEM, new Identifier(HellMod.MODID, itemid), item);
    }

    public static void registerItems(){
        HellMod.LOGGER.info("Registering items");
    }
}