package dev.hellmod.items.custom;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;

public class CustomShovelItem extends CustomMiningToolItem {
    public CustomShovelItem(ToolMaterial material, float damage, float speed, Settings settings) {
        super(material, BlockTags.SHOVEL_MINEABLE, damage, speed, settings);
    }
}