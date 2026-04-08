package dev.hellmod.datagen;

import dev.hellmod.HellMod;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
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

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PURE_IRON_INGOT)
                .pattern("III")
                .pattern("IOI")
                .pattern("III")
                .input('I', Items.IRON_INGOT)
                .input('O', Items.IRON_BLOCK)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .offerTo(recipeExporter, new Identifier(HellMod.MODID, "pure_iron_ingot_alt"));
    }
}
