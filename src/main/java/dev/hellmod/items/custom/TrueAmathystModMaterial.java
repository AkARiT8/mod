package dev.hellmod.items.custom;

import dev.hellmod.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public class TrueAmathystModMaterial implements ToolMaterial {

    public static final TrueAmathystModMaterial INSTANCE = new TrueAmathystModMaterial();

    @Override
    public int getDurability() {
        return 5252;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 13;
    }

    @Override
    public float getAttackDamage() {
        return 7;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
    }

    @Override
    public int getEnchantability() {
        return 40;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.TRUE_AMETHYST_INGOT);
    }
}
