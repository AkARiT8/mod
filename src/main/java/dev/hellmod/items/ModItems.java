package dev.hellmod.items;

import dev.hellmod.HellMod;
import dev.hellmod.items.custom.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.UUID;

public class ModItems {

    public static final Item PURE_IRON_INGOT = registerItem("pure_iron_ingot", new CustoModItem(new Item.Settings()));
    public static final Item PURE_GOLD_INGOT = registerItem("pure_gold_ingot", new CustoModItem(new Item.Settings()));
    public static final Item PURE_NETHERITE_INGOT = registerItem("pure_netherite_ingot", new CustoModItem(new Item.Settings()));
    public static final Item PURE_EMERALD = registerItem("pure_emerald", new CustoModItem(new Item.Settings()));
    public static final Item PURE_DIAMOND = registerItem("pure_diamond", new CustoModItem(new Item.Settings()));

    public static final Item ENCHANTED_GOLDEN_CARROT = registerItem("enchanted_golden_carrot",
            new CustomCarrot(new Item.Settings().food(
                    new FoodComponent(4, 0.3f, false, 1.6f, List.of())
            )));

    public static final Item SPEED_TOTEM_OF_UNDYING = registerItem("speed_totem_of_undying", new CustoModItem(new Item.Settings().maxCount(1)));

    public static final Item BASIC_HARDCORE_HEART = registerItem(
            "basic_hardcore_heart",
            new CustomBasicHeart(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .saturationModifier(0.0f)
                            .alwaysEdible()
                            .build()
            ))
    );

    public static final Item OVERWORLD_ESSENCE = registerItem("overworld_essence", new CustoModItem(new Item.Settings()));
    public static final Item BLAZE_MAIN_ROD = registerItem("blaze_main_rod", new CustoModItem(new Item.Settings()));
    public static final Item MAGMA_CUBE_CORE = registerItem("magma_cube_core", new CustoModItem(new Item.Settings()));


    public static final Item NETHER_INFUSED_INGOT = registerItem("nether_infused_ingot", new CustoModItem(new Item.Settings()));

    public static final Item NETHER_INFUSED_SWORD = registerItem(
            "nether_infused_sword",
            new SwordItem(
                    NetherInfusedModMaterial.INSTANCE,
                    new Item.Settings()
                            .maxCount(1)
                            .fireproof()
                            .component(
                                    DataComponentTypes.ATTRIBUTE_MODIFIERS,
                                    SwordItem.createAttributeModifiers(
                                            NetherInfusedModMaterial.INSTANCE,
                                            3,
                                            -2.4F
                                    )
                            )
            )
    );

    public static final Item NETHER_INFUSED_PICKAXE = registerItem("nether_infused_pickaxe", new CustomPickaxeItem(NetherInfusedModMaterial.INSTANCE,1,-2.8F, new Item.Settings().maxCount(1).fireproof()));


    public static final Item NETHER_INFUSED_AXE = registerItem("nether_infused_axe",new CustomAxeItem(
            NetherInfusedModMaterial.INSTANCE,
            6.0F,
            -3.0F,
            new Item.Settings().maxCount(1).fireproof()
    ));
    public static final Item NETHER_INFUSED_SHOVEL = registerItem("nether_infused_shovel", new CustomShovelItem(NetherInfusedModMaterial.INSTANCE,1.5F,-3F, new Item.Settings().maxCount(1).fireproof()));
    public static final Item NETHER_INFUSED_HOE = registerItem("nether_infused_hoe", new CustomHoeItem(NetherInfusedModMaterial.INSTANCE,0,-2.5F, new Item.Settings().maxCount(1).fireproof()));

    public static final Item NETHER_INFUSED_HELMET = registerItem(
            "nether_infused_helmet",
            new ArmorItem(
                    ModArmorMaterials.NETHER_INFUSED_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Settings().maxCount(1).fireproof().maxDamage(1264)
            )
    );
    public static final Item NETHER_INFUSED_CHESTPLATE = registerItem(
            "nether_infused_chestplate",
            new ArmorItem(
                    ModArmorMaterials.NETHER_INFUSED_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxCount(1).fireproof().maxDamage(1264)
            )
    );

    public static final Item NETHER_INFUSED_LEGGINGS = registerItem(
            "nether_infused_leggings",
            new ArmorItem(
                    ModArmorMaterials.NETHER_INFUSED_ARMOR_MATERIAL,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxCount(1).fireproof().maxDamage(1264)
            )
    );
    public static final Item NETHER_INFUSED_BOOTS = registerItem(
            "nether_infused_boots",
            new ArmorItem(
                    ModArmorMaterials.NETHER_INFUSED_ARMOR_MATERIAL,
                    ArmorItem.Type.BOOTS,
                    new Item.Settings().maxCount(1).fireproof().maxDamage(1264)


            )
    );

    public static final Item NETHER_INFUSED_TEMPLATE = registerItem(
            "nether_infused_template",
            new SmithingTemplateItem(
                    Text.literal("Netherite Equipment"),
                    Text.literal("Nether infused Ingot"),
                    Text.literal("Nether Infused Upgrade"),
                    Text.literal("Base"),
                    Text.literal("Addition"),
                    List.of(),
                    List.of()
            )
    );

    public static final Item GOLDEN_APPLE_RUNE = registerItem(
            "golden_apple_rune",
            new GoldenAppleRune(new Item.Settings().maxCount(1))
    );

    public static final Item UNCOMMON_HARDCORE_HEART = registerItem(
            "uncommon_hardcore_heart",
            new CustomUncommonHeart(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .saturationModifier(0.0f)
                            .alwaysEdible()
                            .build()
            ))
    );

    public static final Item BARRIER_TOTEM_OF_UNDYING = registerItem("barrier_totem_of_undying", new CustoModItem(new Item.Settings().maxCount(1)));

    public static final Item GHAST_APPENDIX = registerItem("ghast_appendix", new CustoModItem(new Item.Settings()));
    public static final Item NETHER_ESSENCE = registerItem("nether_essence", new CustoModItem(new Item.Settings()));
    public static final Item EMPOWERED_COAL = registerItem("empowered_coal", new CustoModItem(new Item.Settings()));

    private static Item registerItem(String itemid, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(HellMod.MODID, itemid), item);
    }

    public static void registerItems() {
        HellMod.LOGGER.info("Registering items");
    }
}