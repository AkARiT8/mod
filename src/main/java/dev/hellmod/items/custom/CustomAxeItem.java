package dev.hellmod.items.custom;

import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.item.AxeItem;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;

public class CustomAxeItem extends AxeItem {

    private final AttributeModifiersComponent attributes;

    public CustomAxeItem(
            ToolMaterial material,
            float damage,
            float speed,
            Settings settings
    ) {
        super(material, settings);

        this.attributes =
                MiningToolItem.createAttributeModifiers(
                        material,
                        damage,
                        speed
                );
    }

    @Override
    public AttributeModifiersComponent getAttributeModifiers() {
        return attributes;
    }
}