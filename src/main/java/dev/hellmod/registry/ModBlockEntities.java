package dev.hellmod.registry;

import dev.hellmod.HellMod;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.blocks.entity.StageBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<StageBlockEntity> STAGE_BLOCK_ENTITY =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    new Identifier(HellMod.MODID, "stage_block_entity"),
                    FabricBlockEntityTypeBuilder.create(
                            StageBlockEntity::new,
                            ModBlocks.STAGE_BLOCK.getLeft()
                    ).build()
            );

    public static void register() {
    }
}