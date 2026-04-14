package dev.hellmod.items.custom;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;

public class CustomPickaxeItem extends CustomMiningToolItem {
    public CustomPickaxeItem(ToolMaterial material, float damage, float speed, Settings settings) {
        super(material, BlockTags.PICKAXE_MINEABLE, damage, speed, settings);
    }
}