package dev.hellmod.datagen;

import dev.hellmod.HellMod;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class DatagenRecipeProvider extends FabricRecipeProvider {

    public DatagenRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BREWING, ModItems.PURE_IRON_INGOT, RecipeCategory.DECORATIONS, ModBlocks.PURE_IRON_BLOCK.getRight());
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BREWING, ModItems.PURE_GOLD_INGOT, RecipeCategory.DECORATIONS, ModBlocks.PURE_GOLD_BLOCK.getRight());
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BREWING, ModItems.PURE_DIAMOND, RecipeCategory.DECORATIONS, ModBlocks.PURE_DIAMOND_BLOCK.getRight());
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BREWING, ModItems.PURE_EMERALD, RecipeCategory.DECORATIONS, ModBlocks.PURE_EMERALD_BLOCK.getRight());
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BREWING, ModItems.PURE_NETHERITE_INGOT, RecipeCategory.DECORATIONS, ModBlocks.PURE_NETHERITE_BLOCK.getRight());
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BREWING, ModItems.EMPOWERED_COAL, RecipeCategory.DECORATIONS, ModBlocks.EMPOWERED_COAL_BLOCK.getRight());

        //STAGE1
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PURE_IRON_INGOT)
                .pattern("III")
                .pattern("IOI")
                .pattern("III")
                .input('I', Items.IRON_INGOT)
                .input('O', Items.IRON_BLOCK)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "pure_iron_ingot_alt"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PURE_GOLD_INGOT)
                .pattern("III")
                .pattern("IOI")
                .pattern("III")
                .input('I', Items.GOLD_INGOT)
                .input('O', Items.GOLD_BLOCK)
                .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "pure_gold_ingot_alt"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PURE_DIAMOND)
                .pattern("III")
                .pattern("IOI")
                .pattern("III")
                .input('I', Items.DIAMOND)
                .input('O', Items.DIAMOND_BLOCK)
                .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "pure_diamond_alt"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PURE_EMERALD)
                .pattern("III")
                .pattern("IOI")
                .pattern("III")
                .input('I', Items.EMERALD)
                .input('O', Items.EMERALD_BLOCK)
                .criterion(hasItem(Items.EMERALD),conditionsFromItem(Items.EMERALD))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "pure_emerald_alt"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PURE_NETHERITE_INGOT)
                .pattern("III")
                .pattern("IOI")
                .pattern("III")
                .input('I', Items.NETHERITE_INGOT)
                .input('O', Items.NETHERITE_BLOCK)
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "pure_netherite_ingot_alt"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SPEED_TOTEM_OF_UNDYING)
                .pattern("IOI")
                .pattern("PLP")
                .pattern("IOI")
                .input('I', ModItems.PURE_IRON_INGOT)
                .input('O', Items.DIAMOND)
                .input('P', Items.SUGAR)
                .input('L', Items.TOTEM_OF_UNDYING)
                .criterion(hasItem(Items.TOTEM_OF_UNDYING),conditionsFromItem(Items.TOTEM_OF_UNDYING))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "speed_totem_of_undying"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OVERWORLD_ESSENCE)
                .pattern("IOP")
                .pattern("IOP")
                .pattern("IOP")
                .input('I', Items.BONE)
                .input('O', Items.GUNPOWDER)
                .input('P', Items.ROTTEN_FLESH)
                .criterion(hasItem(Items.ROTTEN_FLESH),conditionsFromItem(Items.ROTTEN_FLESH))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "overworld_essence"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENCHANTED_GOLDEN_CARROT)
                .pattern("IOI")
                .pattern("IPI")
                .pattern("IOI")
                .input('I', Items.GOLD_INGOT)
                .input('O', ModItems.PURE_GOLD_INGOT)
                .input('P', Items.GOLDEN_CARROT)
                .criterion(hasItem(Items.GOLDEN_CARROT),conditionsFromItem(Items.GOLDEN_CARROT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "enchanted_golden_carrot"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.ENCHANTED_GOLDEN_APPLE)
                .pattern("III")
                .pattern("OPO")
                .pattern("III")
                .input('I', Items.DIAMOND)
                .input('O', Items.GOLD_BLOCK)
                .input('P', Items.GOLDEN_APPLE)
                .criterion(hasItem(Items.GOLDEN_APPLE),conditionsFromItem(Items.GOLDEN_APPLE))
                .offerTo(recipeExporter, new Identifier("minecraft", "enchanted_golden_apple"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BASIC_HARDCORE_HEART)
                .pattern("III")
                .pattern("OPO")
                .pattern("LIL")
                .input('I', Items.REDSTONE_BLOCK)
                .input('O', Items.GLISTERING_MELON_SLICE)
                .input('P', Items.ENCHANTED_GOLDEN_APPLE)
                .input('L', Items.GOLDEN_CARROT)
                .criterion(hasItem(Items.ENCHANTED_GOLDEN_APPLE),conditionsFromItem(Items.ENCHANTED_GOLDEN_APPLE))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "basic_hardcore_heart"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.TOTEM_OF_UNDYING)
                .pattern("IOI")
                .pattern("PMP")
                .pattern("LKL")
                .input('I', Items.GOLD_BLOCK)
                .input('O', ModItems.PURE_GOLD_INGOT)
                .input('P', ModItems.PURE_EMERALD)
                .input('L', Items.BLAZE_POWDER)
                .input('K', ModItems.PURE_IRON_INGOT)
                .input('M', ModItems.BASIC_HARDCORE_HEART)
                .criterion(hasItem(ModItems.BASIC_HARDCORE_HEART),conditionsFromItem(ModItems.BASIC_HARDCORE_HEART))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "totem_of_undying"));

        //STAGE2

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.NETHER_INFUSED_TEMPLATE),
                Ingredient.ofItems(Items.NETHERITE_HELMET),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_INGOT),
                RecipeCategory.COMBAT,
                ModItems.NETHER_INFUSED_HELMET
        ).criterion(
                "has_nether_infused_ingot",
                conditionsFromItem(ModItems.NETHER_INFUSED_INGOT)
        ).offerTo(recipeExporter, "nether_infused_helmet");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.NETHER_INFUSED_TEMPLATE),
                Ingredient.ofItems(Items.NETHERITE_CHESTPLATE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_INGOT),
                RecipeCategory.COMBAT,
                ModItems.NETHER_INFUSED_CHESTPLATE
        ).criterion(
                "has_nether_infused_ingot",
                conditionsFromItem(ModItems.NETHER_INFUSED_INGOT)
        ).offerTo(recipeExporter, "nether_infused_chestplate");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.NETHER_INFUSED_TEMPLATE),
                Ingredient.ofItems(Items.NETHERITE_LEGGINGS),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_INGOT),
                RecipeCategory.COMBAT,
                ModItems.NETHER_INFUSED_LEGGINGS
        ).criterion(
                "has_nether_infused_ingot",
                conditionsFromItem(ModItems.NETHER_INFUSED_INGOT)
        ).offerTo(recipeExporter, "nether_infused_leggins");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.NETHER_INFUSED_TEMPLATE),
                Ingredient.ofItems(Items.NETHERITE_BOOTS),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_INGOT),
                RecipeCategory.COMBAT,
                ModItems.NETHER_INFUSED_BOOTS
        ).criterion(
                "has_nether_infused_ingot",
                conditionsFromItem(ModItems.NETHER_INFUSED_INGOT)
        ).offerTo(recipeExporter, "nether_infused_boots");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.NETHER_INFUSED_TEMPLATE),
                Ingredient.ofItems(Items.NETHERITE_SWORD),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_INGOT),
                RecipeCategory.COMBAT,
                ModItems.NETHER_INFUSED_SWORD
        ).criterion(
                "has_nether_infused_ingot",
                conditionsFromItem(ModItems.NETHER_INFUSED_INGOT)
        ).offerTo(recipeExporter, "nether_infused_sword");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.NETHER_INFUSED_TEMPLATE),
                Ingredient.ofItems(Items.NETHERITE_PICKAXE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_INGOT),
                RecipeCategory.COMBAT,
                ModItems.NETHER_INFUSED_PICKAXE
        ).criterion(
                "has_nether_infused_ingot",
                conditionsFromItem(ModItems.NETHER_INFUSED_INGOT)
        ).offerTo(recipeExporter, "nether_infused_pickaxe");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.NETHER_INFUSED_TEMPLATE),
                Ingredient.ofItems(Items.NETHERITE_AXE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_INGOT),
                RecipeCategory.COMBAT,
                ModItems.NETHER_INFUSED_AXE
        ).criterion(
                "has_nether_infused_ingot",
                conditionsFromItem(ModItems.NETHER_INFUSED_INGOT)
        ).offerTo(recipeExporter, "nether_infused_axe");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.NETHER_INFUSED_TEMPLATE),
                Ingredient.ofItems(Items.NETHERITE_SHOVEL),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_INGOT),
                RecipeCategory.COMBAT,
                ModItems.NETHER_INFUSED_SHOVEL
        ).criterion(
                "has_nether_infused_ingot",
                conditionsFromItem(ModItems.NETHER_INFUSED_INGOT)
        ).offerTo(recipeExporter, "nether_infused_shovel");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.NETHER_INFUSED_TEMPLATE),
                Ingredient.ofItems(Items.NETHERITE_HOE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_INGOT),
                RecipeCategory.COMBAT,
                ModItems.NETHER_INFUSED_HOE
        ).criterion(
                "has_nether_infused_ingot",
                conditionsFromItem(ModItems.NETHER_INFUSED_INGOT)
        ).offerTo(recipeExporter, "nether_infused_hoe");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EMPOWERED_COAL)
                .pattern("OIO")
                .pattern("IPI")
                .pattern("OIO")
                .input('I', Items.COAL)
                .input('O', Items.BLAZE_ROD)
                .input('P', Items.COAL_BLOCK)
                .criterion(hasItem(Items.BLAZE_ROD),conditionsFromItem(Items.BLAZE_ROD))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "empowered_coal_alt"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NETHER_INFUSED_INGOT)
                .pattern("OIO")
                .pattern("IPI")
                .pattern("KLK")
                .input('I', ModItems.NETHER_ESSENCE)
                .input('O', ModItems.MAGMA_CUBE_CORE)
                .input('P', Items.NETHERITE_INGOT)
                .input('K', ModItems.BLAZE_MAIN_ROD)
                .input('L', ModItems.EMPOWERED_COAL)
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "nether_infused_ingot"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NETHER_ESSENCE)
                .pattern("IOP")
                .pattern("IOP")
                .pattern("IOP")
                .input('I', Items.MAGMA_CREAM)
                .input('O', Items.BLAZE_ROD)
                .input('P', Items.GHAST_TEAR)
                .criterion(hasItem(Items.GHAST_TEAR),conditionsFromItem(Items.GHAST_TEAR))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "nether_essence"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BARRIER_TOTEM_OF_UNDYING)
                .pattern("OKO")
                .pattern("IPI")
                .pattern("IOI")
                .input('I', Items.GOLDEN_APPLE)
                .input('O', ModItems.PURE_GOLD_INGOT)
                .input('P', Items.TOTEM_OF_UNDYING)
                .input('K', Items.ENCHANTED_GOLDEN_APPLE)
                .criterion(hasItem(Items.TOTEM_OF_UNDYING),conditionsFromItem(Items.TOTEM_OF_UNDYING))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "barrier_totem_of_undying"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.UNCOMMON_HARDCORE_HEART)
                .pattern("IKI")
                .pattern("OPO")
                .pattern("IKI")
                .input('I', Items.ENCHANTED_GOLDEN_APPLE)
                .input('O', ModItems.NETHER_ESSENCE)
                .input('P', ModItems.BASIC_HARDCORE_HEART)
                .input('K', ModItems.NETHER_INFUSED_INGOT)
                .criterion(hasItem(ModItems.BASIC_HARDCORE_HEART),conditionsFromItem(ModItems.BASIC_HARDCORE_HEART))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "uncommon_hardcore_heart"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLDEN_APPLE_RUNE)
                .pattern("IKI")
                .pattern("OPO")
                .pattern("IKI")
                .input('I', ModItems.NETHER_ESSENCE)
                .input('O', Items.GOLDEN_APPLE)
                .input('P', Items.NETHERITE_INGOT)
                .input('K', Items.ENCHANTED_GOLDEN_APPLE)
                .criterion(hasItem(Items.NETHERITE_INGOT),conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "golden_apple_rune"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NETHER_INFUSED_TEMPLATE)
                .pattern("OLI")
                .pattern("KPK")
                .pattern("ILO")
                .input('I', ModItems.PURE_GOLD_INGOT)
                .input('O', ModItems.NETHER_INFUSED_INGOT)
                .input('P', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .input('K', ModItems.PURE_IRON_INGOT)
                .input('L', ModItems.EMPOWERED_COAL)
                .criterion(hasItem(ModItems.NETHER_INFUSED_INGOT),conditionsFromItem(ModItems.NETHER_INFUSED_INGOT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "nether_infused_template"));

        //STAGE 3

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_TEMPLATE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_AXE),
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_INGOT),
                RecipeCategory.COMBAT,
                ModItems.TRUE_AMETHYST_AXE
        ).criterion(
                "has_true_amethyst_ingot",
                conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT)
        ).offerTo(recipeExporter, "true_amethyst_axe");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_TEMPLATE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_SWORD),
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_INGOT),
                RecipeCategory.COMBAT,
                ModItems.TRUE_AMETHYST_SWORD
        ).criterion(
                "has_true_amethyst_ingot",
                conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT)
        ).offerTo(recipeExporter, "true_amethyst_sword");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_TEMPLATE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_HOE),
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_INGOT),
                RecipeCategory.COMBAT,
                ModItems.TRUE_AMETHYST_HOE
        ).criterion(
                "has_true_amethyst_ingot",
                conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT)
        ).offerTo(recipeExporter, "true_amethyst_hoe");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_TEMPLATE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_PICKAXE),
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_INGOT),
                RecipeCategory.COMBAT,
                ModItems.TRUE_AMETHYST_PICKAXE
        ).criterion(
                "has_true_amethyst_ingot",
                conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT)
        ).offerTo(recipeExporter, "true_amethyst_pickaxe");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_TEMPLATE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_SHOVEL),
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_INGOT),
                RecipeCategory.COMBAT,
                ModItems.TRUE_AMETHYST_SHOVEL
        ).criterion(
                "has_true_amethyst_ingot",
                conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT)
        ).offerTo(recipeExporter, "true_amethyst_shovel");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_TEMPLATE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_HELMET),
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_INGOT),
                RecipeCategory.COMBAT,
                ModItems.TRUE_AMETHYST_HELMET
        ).criterion(
                "has_true_amethyst_ingot",
                conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT)
        ).offerTo(recipeExporter, "true_amethyst_helmet");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_TEMPLATE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_CHESTPLATE),
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_INGOT),
                RecipeCategory.COMBAT,
                ModItems.TRUE_AMETHYST_CHESTPLATE
        ).criterion(
                "has_true_amethyst_ingot",
                conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT)
        ).offerTo(recipeExporter, "true_amethyst_chestplate");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_TEMPLATE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_LEGGINGS),
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_INGOT),
                RecipeCategory.COMBAT,
                ModItems.TRUE_AMETHYST_LEGGINGS
        ).criterion(
                "has_true_amethyst_ingot",
                conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT)
        ).offerTo(recipeExporter, "true_amethyst_leggings");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_TEMPLATE),
                Ingredient.ofItems(ModItems.NETHER_INFUSED_BOOTS),
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_INGOT),
                RecipeCategory.COMBAT,
                ModItems.TRUE_AMETHYST_BOOTS
        ).criterion(
                "has_true_amethyst_ingot",
                conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT)
        ).offerTo(recipeExporter, "true_amethyst_boots");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_TEMPLATE),
                Ingredient.ofItems(Items.BOW),
                Ingredient.ofItems(ModItems.TRUE_AMETHYST_INGOT),
                RecipeCategory.COMBAT,
                ModItems.TRUE_AMETHYST_BOW
        ).criterion(
                "has_true_amethyst_ingot",
                conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT)
        ).offerTo(recipeExporter, "true_amethyst_bow");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TRUE_AMETHYST_SHIELD)
                .pattern("PIP")
                .pattern("POP")
                .pattern("PIP")
                .input('I', ModItems.TRUE_AMETHYST_INGOT)
                .input('O', Items.SHIELD)
                .input('P', Items.AMETHYST_SHARD)
                .criterion(hasItem(ModItems.TRUE_AMETHYST_INGOT),conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "true_amethyst_shield"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PANIC_BALL)
                .pattern("PIP")
                .pattern("LOL")
                .pattern("PKP")
                .input('I', ModItems.TRUE_AMETHYST_INGOT)
                .input('O', Items.SLIME_BALL)
                .input('P', Items.PHANTOM_MEMBRANE)
                .input('L', ModItems.GHAST_APPENDIX)
                .input('K', ModItems.PURE_EMERALD)
                .criterion(hasItem(ModItems.TRUE_AMETHYST_INGOT),conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "panic_ball"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.INVENCIBILITY_RUNE)
                .pattern("PIP")
                .pattern("LOL")
                .pattern("PKP")
                .input('P', ModItems.PURE_IRON_INGOT)
                .input('O', Items.NETHERITE_INGOT)
                .input('I', Items.IRON_CHESTPLATE)
                .input('L', ModBlocks.PURE_IRON_BLOCK.getRight())
                .input('K', ModItems.TRUE_AMETHYST_INGOT)
                .criterion(hasItem(ModItems.TRUE_AMETHYST_INGOT),conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "invencibility_rune"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PANIC_TOTEM_OF_UNDYING)
                .pattern("PIP")
                .pattern("LOL")
                .pattern("PKP")
                .input('I', ModItems.TRUE_AMETHYST_SHARD)
                .input('P', Items.AMETHYST_SHARD)
                .input('O', Items.TOTEM_OF_UNDYING)
                .input('K', ModItems.PANIC_BALL)
                .input('L', ModItems.PURE_EMERALD)
                .criterion(hasItem(ModItems.TRUE_AMETHYST_SHARD),conditionsFromItem(ModItems.TRUE_AMETHYST_SHARD))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "panic_totem_of_undying"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TRUE_AMETHYST_SHARD)
                .pattern("PIP")
                .pattern("ILI")
                .pattern("PIP")
                .input('I', Items.AMETHYST_SHARD)
                .input('P', ModItems.EMPOWERED_COAL)
                .input('L', ModItems.PURE_DIAMOND)
                .criterion(hasItem(ModItems.EMPOWERED_COAL),conditionsFromItem(ModItems.EMPOWERED_COAL))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "true_amethyst_shard"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TRUE_AMETHYST_TEMPLATE)
                .pattern("IPI")
                .pattern("ILI")
                .pattern("IPI")
                .input('I', Items.AMETHYST_SHARD)
                .input('P', ModItems.TRUE_AMETHYST_INGOT)
                .input('L', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .criterion(hasItem(ModItems.TRUE_AMETHYST_INGOT),conditionsFromItem(ModItems.TRUE_AMETHYST_INGOT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "true_amethyst_template"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TRUE_AMETHYST_INGOT)
                .pattern("OKP")
                .pattern("ILI")
                .pattern("PKO")
                .input('O', ModItems.CREEPER_ESSENCE)
                .input('K', ModItems.ZOMBIE_ESSENCE)
                .input('I', ModItems.PHANTOM_ESSENCE)
                .input('P', ModItems.TRUE_AMETHYST_SHARD)
                .input('L', Items.NETHERITE_INGOT)
                .criterion(hasItem(ModItems.TRUE_AMETHYST_SHARD),conditionsFromItem(ModItems.TRUE_AMETHYST_SHARD))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "true_amethyst_ingot"));



    }
}
