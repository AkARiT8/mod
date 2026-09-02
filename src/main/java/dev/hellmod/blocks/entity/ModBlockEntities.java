package dev.hellmod.blocks.entity;

import dev.hellmod.blocks.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<CustomButtonBlockEntity> CUSTOM_BUTTON =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of("hellmod", "custom_button"),
                    FabricBlockEntityTypeBuilder.create(
                            CustomButtonBlockEntity::new,
                            ModBlocks.CUSTOM_BUTTON.getLeft()
                    ).build()
            );
}