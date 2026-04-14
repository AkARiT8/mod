package dev.hellmod.items;

import dev.hellmod.HellMod;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

public class ModArmorMaterials {


    public static final RegistryEntry<ArmorMaterial> NETHER_INFUSED_ARMOR_MATERIAL =
            Registry.registerReference(
                    Registries.ARMOR_MATERIAL,
                    new Identifier(HellMod.MODID, "nether_infused"),
                    new ArmorMaterial(
                            Map.of(
                                    ArmorItem.Type.HELMET, 4,
                                    ArmorItem.Type.CHESTPLATE, 10,
                                    ArmorItem.Type.LEGGINGS, 8,
                                    ArmorItem.Type.BOOTS, 4
                            ),
                            25,
                            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
                            () -> Ingredient.ofItems(ModItems.NETHER_INFUSED_INGOT),

                            List.of(
                                    new ArmorMaterial.Layer(
                                            new Identifier(HellMod.MODID, "nether_infused"),
                                            "",
                                            false
                                    )
                            ),

                            4f,
                            0.2f
                    )
            );

    private static RegistryEntry<ArmorMaterial> registerArmorMaterial(String id, ArmorMaterial armorMaterial){
        return Registry.registerReference(Registries.ARMOR_MATERIAL, new Identifier(HellMod.MODID, id), armorMaterial);
    }

    public static void registerArmorMaterials(){
        HellMod.LOGGER.info("registrando armor material");
    }
}
