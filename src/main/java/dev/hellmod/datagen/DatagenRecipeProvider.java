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
    }
}
