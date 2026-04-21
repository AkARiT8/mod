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
        builder.add("tooltip.hellmod.basic_hardcore_heart.line7", "Up to 14 Max HP");
        builder.add(ModItems.OVERWORLD_ESSENCE, "Overworld Essence");
        builder.add("tooltip.hellmod.overworld_essence.line1", "Stage 1");
        builder.add(ModItems.BLAZE_MAIN_ROD, "Blaze Main Rod");
        builder.add("tooltip.hellmod.blaze_main_rod.line1", "Stage 2");
        builder.add("tooltip.hellmod.blaze_main_rod.line3", "Rare");
        builder.add(ModItems.MAGMA_CUBE_CORE, "Magma Cube Core");
        builder.add("tooltip.hellmod.magma_cube_core.line1", "Stage 2");
        builder.add("tooltip.hellmod.magma_cube_core.line3", "Rare");
        builder.add(ModItems.BARRIER_TOTEM_OF_UNDYING, "Barrier Totem of Undying");
        builder.add("tooltip.hellmod.barrier_totem_of_undying.line1", "Stage 2");
        builder.add("tooltip.hellmod.barrier_totem_of_undying.line3", "Rare");
        builder.add("tooltip.hellmod.barrier_totem_of_undying.line7", "Periodicaly Absortion");
        builder.add(ModItems.NETHER_ESSENCE, "Nether Essence");
        builder.add("tooltip.hellmod.nether_essence.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_essence.line3", "Rare");
        builder.add(ModItems.GHAST_APPENDIX, "Ghast Appendix");
        builder.add("tooltip.hellmod.ghast_appendix.line1", "Stage 2");
        builder.add("tooltip.hellmod.ghast_appendix.line3", "Rare");
        builder.add(ModItems.EMPOWERED_COAL, "Empowered Coal");
        builder.add("tooltip.hellmod.empowered_coal.line1", "Stage 2");
        builder.add("tooltip.hellmod.empowered_coal.line3", "Rare");

        //NETHER INFUSED
        builder.add(ModItems.NETHER_INFUSED_INGOT, "Nether Infused Ingot");
        builder.add("tooltip.hellmod.nether_infused_ingot.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_ingot.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_HELMET, "Nether Infused Helmet");
        builder.add("tooltip.hellmod.nether_infused_helmet.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_helmet.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_CHESTPLATE, "Nether Infused Chestplate");
        builder.add("tooltip.hellmod.nether_infused_chestplate.line10", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_chestplate.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_LEGGINGS, "Nether Infused Leggings");
        builder.add("tooltip.hellmod.nether_infused_leggings.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_leggings.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_BOOTS, "Nether Infused Boots");
        builder.add("tooltip.hellmod.nether_infused_boots.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_boots.line3", "Rare");

        builder.add(ModItems.NETHER_INFUSED_SWORD, "Nether Infused Sword");
        builder.add("tooltip.hellmod.nether_infused_sword.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_sword.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_PICKAXE, "Nether Infused Pickaxe");
        builder.add("tooltip.hellmod.nether_infused_pickaxe.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_pickaxe.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_AXE, "Nether Infused Axe");
        builder.add("tooltip.hellmod.nether_infused_axe.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_axe.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_HOE, "Nether Infused Hoe");
        builder.add("tooltip.hellmod.nether_infused_hoe.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_hoe.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_SHOVEL, "Nether Infused Shovel");
        builder.add("tooltip.hellmod.nether_infused_shovel.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_shovel.line3", "Rare");
        builder.add(ModItems.NETHER_INFUSED_TEMPLATE, "Nether Infused Template");
        builder.add("tooltip.hellmod.nether_infused_template.line1", "Stage 2");
        builder.add("tooltip.hellmod.nether_infused_template.line3", "Rare");

        builder.add(ModItems.GOLDEN_APPLE_RUNE, "Golden Apple Rune");
        builder.add("tooltip.hellmod.golden_apple_rune.line1", "Stage 2");
        builder.add("tooltip.hellmod.golden_apple_rune.line3", "Rare");

        builder.add(ModItems.UNCOMMON_HARDCORE_HEART, "Uncommon Hardcore Heart");
        builder.add("tooltip.hellmod.uncommon_hardcore_heart.line1", "Stage 2");
        builder.add("tooltip.hellmod.uncommon_hardcore_heart.line3", "Rare");
        builder.add("tooltip.hellmod.uncommon_hardcore_heart.line7", "Up to 16 Max HP");


        //STAGE 3

        builder.add(ModItems.TRUE_AMETHYST_HELMET, "True Amethyst Helmet");
        builder.add("tooltip.hellmod.true_amethyst_helmet.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_helmet.line5", "epic");
        builder.add(ModItems.TRUE_AMETHYST_CHESTPLATE, "True Amethyst Chestplate");
        builder.add("tooltip.hellmod.true_amethyst_chestplate.line10", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_chestplate.line5", "epic");
        builder.add(ModItems.TRUE_AMETHYST_LEGGINGS, "True Amethyst Leggings");
        builder.add("tooltip.hellmod.true_amethyst_leggings.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_leggings.line5", "epic");
        builder.add(ModItems.TRUE_AMETHYST_BOOTS, "True Amethyst Boots");
        builder.add("tooltip.hellmod.true_amethyst_boots.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_boots.line5", "epic");

        builder.add(ModItems.TRUE_AMETHYST_SWORD, "True Amethyst Sword");
        builder.add("tooltip.hellmod.true_amethyst_sword.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_sword.line5", "epic");
        builder.add(ModItems.TRUE_AMETHYST_SHOVEL, "True Amethyst Shovel");
        builder.add("tooltip.hellmod.true_amethyst_shovel.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_shovel.line5", "epic");
        builder.add(ModItems.TRUE_AMETHYST_AXE, "True Amethyst Axe");
        builder.add("tooltip.hellmod.true_amethyst_axe.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_axe.line5", "epic");
        builder.add(ModItems.TRUE_AMETHYST_PICKAXE, "True Amethyst Pickaxe");
        builder.add("tooltip.hellmod.true_amethyst_pickaxe.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_pickaxe.line5", "epic");
        builder.add(ModItems.TRUE_AMETHYST_HOE, "True Amethyst Hoe");
        builder.add("tooltip.hellmod.true_amethyst_hoe.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_hoe.line5", "epic");


        builder.add(ModItems.CREEPER_ESSENCE, "Creeper Essence");
        builder.add("tooltip.hellmod.creeper_essence.line1", "Stage 3");
        builder.add("tooltip.hellmod.creeper_essence.line3", "Epic");
        builder.add(ModItems.ZOMBIE_ESSENCE, "Zombie Essence");
        builder.add("tooltip.hellmod.zombie_essence.line1", "Stage 3");
        builder.add("tooltip.hellmod.zombie_essence.line3", "Epic");
        builder.add(ModItems.PHANTOM_ESSENCE, "Phantom Essence");
        builder.add("tooltip.hellmod.phantom_essence.line1", "Stage 3");
        builder.add("tooltip.hellmod.phantom_essence.line3", "Epic");

        builder.add(ModItems.TRUE_AMETHYST_SHARD, "True Amethyst Shard");
        builder.add("tooltip.hellmod.true_amethyst_shard.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_shard.line3", "Epic");
        builder.add(ModItems.TRUE_AMETHYST_INGOT, "True Amethyst Ingot");
        builder.add("tooltip.hellmod.true_amethyst_ingot.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_ingot.line3", "Epic");
        builder.add(ModItems.TRUE_AMETHYST_TEMPLATE, "True Amethyst Template");
        builder.add("tooltip.hellmod.true_amethyst_template.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_template.line3", "Epic");

        builder.add(ModItems.TRUE_AMETHYST_SHIELD, "True Amethyst Shield");
        builder.add("tooltip.hellmod.true_amethyst_shield.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_shield.line5", "Epic");

        builder.add(ModItems.TRUE_AMETHYST_BOW, "True Amethyst Bow");
        builder.add("tooltip.hellmod.true_amethyst_bow.line1", "Stage 3");
        builder.add("tooltip.hellmod.true_amethyst_bow.line5", "Epic");

        builder.add(ModItems.INVENCIBILITY_RUNE, "Invencibility Rune");
        builder.add("tooltip.hellmod.invencibility_rune.line1", "Stage 3");
        builder.add("tooltip.hellmod.invencibility_rune.line3", "Epic");

        builder.add(ModItems.PANIC_BALL, "Panic Ball");
        builder.add("tooltip.hellmod.panic_ball.line1", "Stage 3");
        builder.add("tooltip.hellmod.panic_ball.line3", "Epic");

        builder.add(ModItems.PANIC_TOTEM_OF_UNDYING, "Panic Totem of Undying");
        builder.add("tooltip.hellmod.panic_totem_of_undying.line1", "Stage 3");
        builder.add("tooltip.hellmod.panic_totem_of_undying.line3", "Epic");

        builder.add(ModItems.CREEPER_BOMB_HORN, "Creeper Bomb Horn");
        builder.add("tooltip.hellmod.creeper_bomb_horn.line1", "Stage 3");
        builder.add("tooltip.hellmod.creeper_bomb_horn.line3", "Epic");

        builder.add(ModItems.ZOMBIE_BERSERK_HORN, "Berserk Zombie Horn");
        builder.add("tooltip.hellmod.berserk_zombie_horn.line1", "Stage 3");
        builder.add("tooltip.hellmod.berserk_zombie_horn.line3", "Epic");

        builder.add(ModItems.GIGAPHANTOM_HORN, "Gigaphantom Horn");
        builder.add("tooltip.hellmod.gigaphantom_horn.line1", "Stage 3");
        builder.add("tooltip.hellmod.gigaphantom_horn.line3", "Epic");

        builder.add(ModItems.RARE_HARDCORE_HEART, "Rare Hardcore Heart");
        builder.add("tooltip.hellmod.rare_hardcore_heart.line1", "Stage 1");
        builder.add("tooltip.hellmod.rare_hardcore_heart.line7", "Up to 18 Max HP");


        builder.add("item.minecraft.potion.effect.panic_potion", "Panic Potion");




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

        builder.add(ModBlocks.EMPOWERED_COAL_BLOCK.getLeft(), "Empowered Coal Block");
        builder.add("tooltip.hellmod.empowered_coal_block.line1", "Stage 2");
        builder.add("tooltip.hellmod.empowered_coal_block.line3", "Rare");

        //gm1Tab
        builder.add("itemGroup.hellmod.hell_group", "Hell items");

        //bossName
        builder.add("entity.hellmod.boss_zombie", "§4☠ BERSERK ZOMBIE ☠");
        builder.add("entity.hellmod.boss_creeper", "§2☠ CREEPER BOMB ☠");
        builder.add("entity.hellmod.boss_phantom", "§5☠ GIGA PHANTOM ☠");
    }
}