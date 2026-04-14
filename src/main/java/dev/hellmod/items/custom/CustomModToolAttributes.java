package dev.hellmod.items.custom;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ToolMaterial;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.AttributeModifierSlot;

import java.util.UUID;

public class CustomModToolAttributes {

    private static final UUID ATTACK_DAMAGE_UUID = UUID.fromString("cb3f55d3-645c-4f38-a497-9c13a33db5cf");
    private static final UUID ATTACK_SPEED_UUID  = UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca3");

    public static AttributeModifiersComponent create(ToolMaterial material, float damage, float speed) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                ATTACK_DAMAGE_UUID,
                                "Tool modifier",
                                damage + material.getAttackDamage(),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                ATTACK_SPEED_UUID,
                                "Tool modifier",
                                speed,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }
}