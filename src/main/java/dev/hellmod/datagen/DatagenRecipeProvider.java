package dev.hellmod.datagen;

import dev.hellmod.HellMod;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
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

    }
}
