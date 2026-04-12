package dev.hellmod.datagen;

import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class DatagenModLanguageProvider extends FabricLanguageProvider {

    public DatagenModLanguageProvider(
            FabricDataOutput dataOutput,
            CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup
    ) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup lookup, TranslationBuilder builder) {

        //Items
        builder.add(ModItems.PURE_IRON_INGOT, "Pure Iron Ingot");
        builder.add("tooltip.hellmod.pure_iron_ingot.line1", "Stage 1");
        builder.add(ModItems.PURE_GOLD_INGOT, "Pure Gold Ingot");
        builder.add("tooltip.hellmod.pure_gold_ingot.line1", "Stage 1");
        builder.add(ModItems.PURE_DIAMOND, "Pure Diamond");
        builder.add("tooltip.hellmod.pure_diamond.line1", "Stage 1");
        builder.add(ModItems.PURE_EMERALD, "Pure Emerald");
        builder.add("tooltip.hellmod.pure_emerald.line1", "Stage 1");
        builder.add(ModItems.PURE_NETHERITE_INGOT, "Pure Netherite Ingot");
        builder.add("tooltip.hellmod.pure_netherite_ingot.line1", "Stage 1");
        builder.add(ModItems.ENCHANTED_GOLDEN_CARROT, "Enchanted Golden Carrot");
        builder.add("tooltip.hellmod.enchanted_golden_carrot.line1", "Stage 1");
        builder.add(ModItems.SPEED_TOTEM_OF_UNDYING, "Speedy Totem of Undying");
        builder.add("tooltip.hellmod.speed_totem_of_undying.line1", "Stage 1");
        builder.add("tooltip.hellmod.speed_totem_of_undying.line2", "+20% speed");
        builder.add(ModItems.BASIC_HARDCORE_HEART, "Basic Hardcore Heart");
        builder.add("tooltip.hellmod.basic_hardcore_heart.line1", "Stage 1");
        builder.add(ModItems.OVERWORLD_ESSENCE, "Overworld Essence");
        builder.add("tooltip.hellmod.overworld_essence.line1", "Stage 1");
        builder.add(ModItems.BLAZE_MAIN_ROD, "Blaze Main Rod");
        builder.add("tooltip.hellmod.blaze_main_rod.line1", "Stage 2");
        builder.add("tooltip.hellmod.blaze_main_rod.line3", "Rare");
        builder.add(ModItems.MAGMA_CUBE_CORE, "Magma Cube Core");
        builder.add("tooltip.hellmod.magma_cube_core.line1", "Stage 2");
        builder.add("tooltip.hellmod.magma_cube_core.line3", "Rare");

        //NETHER INFUSED
        builder.add(ModItems.NETHER_INFUSED_INGOT, "Nether Infused Ingot");
        builder.add("tooltip.hellmod.nether_infused_ingot.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_ingot.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_HELMET, "Nether Infused Helmet");
        builder.add("tooltip.hellmod.nether_infused_helmet.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_helmet.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_CHESTPLATE, "Nether Infused Chestplate");
        builder.add("tooltip.hellmod.nether_infused_chestplate.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_chestplate.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_LEGGINGS, "Nether Infused Leggings");
        builder.add("tooltip.hellmod.nether_infused_leggings.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_leggings.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_BOOTS, "Nether Infused Boots");
        builder.add("tooltip.hellmod.nether_infused_boots.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_boots.line3", "Rare");

//        builder.add(ModItems.NETHER_INFUSED_SWORD, "Nether Infused Sword");
//        builder.add("tooltip.hellmod.nether_infused_sword.line1", "Stage 2");
//        builder.add("tooltip.hellmod.nether_infused_sword.line3", "Rare");
//        builder.add(ModItems.NETHER_INFUSED_PICKAXE, "Nether Infused Pickaxe");
//        builder.add("tooltip.hellmod.nether_infused_pickaxe.line1", "Stage 2");
//        builder.add("tooltip.hellmod.nether_infused_pickaxe.line3", "Rare");
//        builder.add(ModItems.NETHER_INFUSED_AXE, "Nether Infused Axe");
//        builder.add("tooltip.hellmod.nether_infused_axe.line1", "Stage 2");
//        builder.add("tooltip.hellmod.nether_infused_axe.line3", "Rare");
//        builder.add(ModItems.NETHER_INFUSED_HOE, "Nether Infused Hoe");
//        builder.add("tooltip.hellmod.nether_infused_hoe.line1", "Stage 2");
//        builder.add("tooltip.hellmod.nether_infused_hoe.line3", "Rare");
//        builder.add(ModItems.NETHER_INFUSED_SHOVEL, "Nether Infused Shovel");
//        builder.add("tooltip.hellmod.nether_infused_shovel.line1", "Stage 2");
//        builder.add("tooltip.hellmod.nether_infused_shovel.line3", "Rare");
//        builder.add(ModItems.NETHER_INFUSED_TEMPLATE, "Nether Infused Template");
//        builder.add("tooltip.hellmod.nether_infused_template.line1", "Stage 2");
//        builder.add("tooltip.hellmod.nether_infused_template.line3", "Rare");

        //Blocks
        builder.add(ModBlocks.PURE_IRON_BLOCK.getLeft(), "Block of Pure Iron");
        builder.add("tooltip.hellmod.pure_iron_block.line1", "Stage 1");
        builder.add(ModBlocks.PURE_GOLD_BLOCK.getLeft(), "Block of Pure Gold");
        builder.add("tooltip.hellmod.pure_gold_block.line1", "Stage 1");
        builder.add(ModBlocks.PURE_DIAMOND_BLOCK.getLeft(), "Block of Pure Diamond");
        builder.add("tooltip.hellmod.pure_diamond_block.line1", "Stage 1");
        builder.add(ModBlocks.PURE_EMERALD_BLOCK.getLeft(), "Block of Pure Emerald");
        builder.add("tooltip.hellmod.pure_emerald_block.line1", "Stage 1");
        builder.add(ModBlocks.PURE_NETHERITE_BLOCK.getLeft(), "Block of Pure Netherite");
        builder.add("tooltip.hellmod.pure_netherite_block.line1", "Stage 1");
        builder.add(ModBlocks.STAGE_BLOCK.getLeft(), "Stage Block");
        builder.add("tooltip.hellmod.stage_block.line1", "Stage 0");

        //gm1Tab
        builder.add("itemGroup.hellmod.hell_group", "Hell items");
    }
}