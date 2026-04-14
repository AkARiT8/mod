package dev.hellmod.items.custom;

import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.block.Block;
import net.minecraft.component.type.AttributeModifiersComponent;

public abstract class CustomMiningToolItem extends MiningToolItem {

    private final AttributeModifiersComponent attributes;

    public CustomMiningToolItem(ToolMaterial material, TagKey<Block> effectiveBlocks, float damage, float speed, Settings settings) {
        super(material, effectiveBlocks, settings);
        this.attributes = CustomModToolAttributes.create(material, damage, speed);
    }

    @Override
    public AttributeModifiersComponent getAttributeModifiers() {
        return attributes;
    }
}