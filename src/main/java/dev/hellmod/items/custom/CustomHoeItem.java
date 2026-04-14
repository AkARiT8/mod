package dev.hellmod.items.custom;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;

public class CustomHoeItem extends CustomMiningToolItem {
    public CustomHoeItem(ToolMaterial material, float damage, float speed, Settings settings) {
        super(material, BlockTags.HOE_MINEABLE, damage, speed, settings);
    }
}