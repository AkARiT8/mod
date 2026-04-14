package dev.hellmod.items.custom;

import dev.hellmod.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public class NetherInfusedModMaterial implements ToolMaterial {

    public static final NetherInfusedModMaterial INSTANCE = new NetherInfusedModMaterial();

    @Override
    public int getDurability() {
        return 3131;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 11;
    }

    @Override
    public float getAttackDamage() {
        return 6;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
    }

    @Override
    public int getEnchantability() {
        return 30;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.NETHER_INFUSED_INGOT);
    }
}
