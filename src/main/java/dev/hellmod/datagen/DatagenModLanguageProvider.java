package dev.hellmod.datagen;

import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.effects.ModEffects;
import dev.hellmod.enchantment.ModEnchantments;
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
        builder.add("tooltip.hellmod.pure_netherite_ingot.line2", "Stage 2");
        builder.add(ModItems.ENCHANTED_GOLDEN_CARROT, "Enchanted Golden Carrot");
        builder.add("tooltip.hellmod.enchanted_golden_carrot.line1", "Stage 1");
        builder.add(ModItems.SPEED_TOTEM_OF_UNDYING, "Speedy Totem of Undying");
        builder.add("tooltip.hellmod.speed_totem_of_undying.line1", "Stage 1");
        builder.add("tooltip.hellmod.speed_totem_of_undying.line2", "+20% speed");
        builder.add(ModItems.BASIC_HARDCORE_HEART, "Basic Hardcore Heart");
        builder.add("tooltip.hellmod.basic_hardcore_heart.line1", "Stage 1");
        builder.add("tooltip.hellmod.basic_hardcore_heart.line7", "Up to 14 Max HP");
        builder.add(ModItems.OVERWORLD_ESSENCE, "Overworld Essence");
        builder.add("tooltip.hellmod.overworld_essence.line1", "Stage 1");
        builder.add(ModItems.BLAZE_MAIN_ROD, "Blaze Main Rod");
        builder.add("tooltip.hellmod.blaze_main_rod.line2", "Stage 2");
        builder.add(ModItems.MAGMA_CUBE_CORE, "Magma Cube Core");
        builder.add("tooltip.hellmod.magma_cube_core.line2", "Stage 2");
        builder.add(ModItems.BARRIER_TOTEM_OF_UNDYING, "Barrier Totem of Undying");
        builder.add("tooltip.hellmod.barrier_totem_of_undying.line2", "Stage 2");
        builder.add("tooltip.hellmod.barrier_totem_of_undying.line7", "Periodically Absorption");
        builder.add(ModItems.NETHER_ESSENCE, "Nether Essence");
        builder.add("tooltip.hellmod.nether_essence.line2", "Stage 2");
        builder.add(ModItems.GHAST_APPENDIX, "Ghast Appendix");
        builder.add("tooltip.hellmod.ghast_appendix.line2", "Stage 2");
        builder.add(ModItems.EMPOWERED_COAL, "Empowered Coal");
        builder.add("tooltip.hellmod.empowered_coal.line2", "Stage 2");

        //NETHER INFUSED
        builder.add(ModItems.NETHER_INFUSED_INGOT, "Nether Infused Ingot");
        builder.add("tooltip.hellmod.nether_infused_ingot.line2", "Stage 2");
        builder.add(ModItems.NETHER_INFUSED_HELMET, "Nether Infused Helmet");
        builder.add("tooltip.hellmod.nether_infused_helmet.line2", "Stage 2");
        builder.add(ModItems.NETHER_INFUSED_CHESTPLATE, "Nether Infused Chestplate");
        builder.add("tooltip.hellmod.nether_infused_chestplate.line2", "Stage 2");
        builder.add(ModItems.NETHER_INFUSED_LEGGINGS, "Nether Infused Leggings");
        builder.add("tooltip.hellmod.nether_infused_leggings.line2", "Stage 2");
        builder.add(ModItems.NETHER_INFUSED_BOOTS, "Nether Infused Boots");
        builder.add("tooltip.hellmod.nether_infused_boots.line2", "Stage 2");

        builder.add(ModItems.NETHER_INFUSED_SWORD, "Nether Infused Sword");
        builder.add("tooltip.hellmod.nether_infused_sword.line2", "Stage 2");
        builder.add(ModItems.NETHER_INFUSED_PICKAXE, "Nether Infused Pickaxe");
        builder.add("tooltip.hellmod.nether_infused_pickaxe.line2", "Stage 2");
        builder.add(ModItems.NETHER_INFUSED_AXE, "Nether Infused Axe");
        builder.add("tooltip.hellmod.nether_infused_axe.line2", "Stage 2");
        builder.add(ModItems.NETHER_INFUSED_HOE, "Nether Infused Hoe");
        builder.add("tooltip.hellmod.nether_infused_hoe.line2", "Stage 2");
        builder.add(ModItems.NETHER_INFUSED_SHOVEL, "Nether Infused Shovel");
        builder.add("tooltip.hellmod.nether_infused_shovel.line2", "Stage 2");
        builder.add(ModItems.NETHER_INFUSED_TEMPLATE, "Nether Infused Template");
        builder.add("tooltip.hellmod.nether_infused_template.line2", "Stage 2");

        builder.add(ModItems.GOLDEN_APPLE_RUNE, "Golden Apple Rune");
        builder.add("tooltip.hellmod.golden_apple_rune.line2", "Stage 2");

        builder.add(ModItems.UNCOMMON_HARDCORE_HEART, "Uncommon Hardcore Heart");
        builder.add("tooltip.hellmod.uncommon_hardcore_heart.line2", "Stage 2");
        builder.add("tooltip.hellmod.uncommon_hardcore_heart.line7", "Up to 16 Max HP");


        //STAGE 3

        builder.add(ModItems.TRUE_AMETHYST_HELMET, "True Amethyst Helmet");
        builder.add("tooltip.hellmod.true_amethyst_helmet.line3", "Stage 3");
        builder.add(ModItems.TRUE_AMETHYST_CHESTPLATE, "True Amethyst Chestplate");
        builder.add("tooltip.hellmod.true_amethyst_chestplate.line3", "Stage 3");
        builder.add(ModItems.TRUE_AMETHYST_LEGGINGS, "True Amethyst Leggings");
        builder.add("tooltip.hellmod.true_amethyst_leggings.line3", "Stage 3");
        builder.add(ModItems.TRUE_AMETHYST_BOOTS, "True Amethyst Boots");
        builder.add("tooltip.hellmod.true_amethyst_boots.line3", "Stage 3");

        builder.add(ModItems.TRUE_AMETHYST_SWORD, "True Amethyst Sword");
        builder.add("tooltip.hellmod.true_amethyst_sword.line3", "Stage 3");
        builder.add(ModItems.TRUE_AMETHYST_SHOVEL, "True Amethyst Shovel");
        builder.add("tooltip.hellmod.true_amethyst_shovel.line3", "Stage 3");
        builder.add(ModItems.TRUE_AMETHYST_AXE, "True Amethyst Axe");
        builder.add("tooltip.hellmod.true_amethyst_axe.line3", "Stage 3");
        builder.add(ModItems.TRUE_AMETHYST_PICKAXE, "True Amethyst Pickaxe");
        builder.add("tooltip.hellmod.true_amethyst_pickaxe.line3", "Stage 3");
        builder.add(ModItems.TRUE_AMETHYST_HOE, "True Amethyst Hoe");
        builder.add("tooltip.hellmod.true_amethyst_hoe.line3", "Stage 3");


        builder.add(ModItems.CREEPER_ESSENCE, "Creeper Essence");
        builder.add("tooltip.hellmod.creeper_essence.line3", "Stage 3");
        builder.add(ModItems.ZOMBIE_ESSENCE, "Zombie Essence");
        builder.add("tooltip.hellmod.zombie_essence.line3", "Stage 3");
        builder.add(ModItems.PHANTOM_ESSENCE, "Phantom Essence");
        builder.add("tooltip.hellmod.phantom_essence.line3", "Stage 3");


        builder.add(ModItems.TRUE_AMETHYST_SHARD, "True Amethyst Shard");
        builder.add("tooltip.hellmod.true_amethyst_shard.line3", "Stage 3");
        builder.add(ModItems.TRUE_AMETHYST_INGOT, "True Amethyst Ingot");
        builder.add("tooltip.hellmod.true_amethyst_ingot.line3", "Stage 3");
        builder.add(ModItems.TRUE_AMETHYST_TEMPLATE, "True Amethyst Template");
        builder.add("tooltip.hellmod.true_amethyst_template.line3", "Stage 3");

        builder.add(ModItems.TRUE_AMETHYST_SHIELD, "True Amethyst Shield");
        builder.add("tooltip.hellmod.true_amethyst_shield.line3", "Stage 3");

        builder.add(ModItems.TRUE_AMETHYST_BOW, "True Amethyst Bow");
        builder.add("tooltip.hellmod.true_amethyst_bow.line3", "Stage 3");

        builder.add(ModItems.INVENCIBILITY_RUNE, "Invencibility Rune");
        builder.add("tooltip.hellmod.invencibility_rune.line3", "Stage 3");

        builder.add(ModItems.PANIC_BALL, "Panic Ball");
        builder.add("tooltip.hellmod.panic_ball.line3", "Stage 3");

        builder.add(ModItems.PANIC_TOTEM_OF_UNDYING, "Panic Totem of Undying");
        builder.add("tooltip.hellmod.panic_totem_of_undying.line3", "Stage 3");

        builder.add(ModItems.RARE_HARDCORE_HEART, "Rare Hardcore Heart");
        builder.add("tooltip.hellmod.rare_hardcore_heart.line3", "Stage 3");
        builder.add("tooltip.hellmod.rare_hardcore_heart.line7", "Up to 18 Max HP");

        builder.add("item.minecraft.potion.effect.panic_potion", "Panic Potion");
        builder.add("tooltip.hellmod.panic_potion.line3", "Stage 3");

        builder.add(ModEnchantments.ENLIGHTENMENT, "Enlightenment");
        builder.add(ModEnchantments.KNOWLEDGE, "knowledge");

        builder.add(ModItems.LOOT_CUBE_T1, "Loot Cube T1");
        builder.add("tooltip.hellmod.loot_cube_t1.line10", "Stage 0");

        builder.add(ModItems.LOOT_CUBE_T2, "Loot Cube T2");
        builder.add("tooltip.hellmod.loot_cube_t2.line10", "Stage 0");

        builder.add(ModItems.LOOT_CUBE_T3, "Loot Cube T3");
        builder.add("tooltip.hellmod.loot_cube_t3.line1", "Stage 1");

        builder.add(ModItems.LOOT_CUBE_T4, "Loot Cube T4");
        builder.add("tooltip.hellmod.loot_cube_t4.line2", "Stage 2");

        builder.add(ModItems.LOOT_CUBE_T5, "Loot Cube T5");
        builder.add("tooltip.hellmod.loot_cube_t5.line3", "Stage 3");

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
        builder.add("tooltip.hellmod.stage_block.line10", "Stage 0");

        builder.add(ModBlocks.EMPOWERED_COAL_BLOCK.getLeft(), "Empowered Coal Block");
        builder.add("tooltip.hellmod.empowered_coal_block.line2", "Stage 2");

        builder.add(ModBlocks.DARK_OBSIDIAN.getLeft(), "Dark Obsidian");
        builder.add("tooltip.hellmod.dark_obsidian.line3", "Stage 3");
        builder.add(ModBlocks.CRYING_DARK_OBSIDIAN.getLeft(), "Crying Dark Obsidian");
        builder.add("tooltip.hellmod.crying_dark_obsidian.line3", "Stage 3");
        builder.add(ModBlocks.CRACKED_DARK_OBSIDIAN.getLeft(), "Cracked Dark Obsidian");
        builder.add("tooltip.hellmod.cracked_dark_obsidian.line3", "Stage 3");
        builder.add(ModBlocks.DARK_OBSIDIAN_PILAR.getLeft(), "Dark Obsidian Pilar");
        builder.add("tooltip.hellmod.dark_obsidian_pilar.line3", "Stage 3");
        builder.add(ModBlocks.DARK_OBSIDIAN_NODE.getLeft(), "Dark Obsidian Node");
        builder.add("tooltip.hellmod.dark_obsidian_node.line3", "Stage 3");

        builder.add(ModBlocks.DARK_OBSIDIAN_SLAB.getLeft(), "Dark Obsidian Slab");
        builder.add("tooltip.hellmod.dark_obsidian_slab.line3", "Stage 3");
        builder.add(ModBlocks.DARK_OBSIDIAN_STAIRS.getLeft(), "Dark Obsidian Stairs");
        builder.add("tooltip.hellmod.dark_obsidian_stairs.line3", "Stage 3");

        builder.add(ModBlocks.CUSTOM_BUTTON.getLeft(), "Custom Button");
        builder.add("tooltip.hellmod.custom_button.line3", "Stage 3");

        //gm1Tab
        builder.add("itemGroup.hellmod.hell_group", "Hell items");

        //Effects
        builder.add(ModEffects.STICKY, "Sticky");
        builder.add(ModEffects.CONTAMINATION, "Contamination");
        builder.add(ModEffects.FRAGILITY, "Fragility");
    }
}