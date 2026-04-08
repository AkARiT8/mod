package dev.hellmod.datagen;

import dev.hellmod.HellMod;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class DatagenModelProvider extends FabricModelProvider {

    public DatagenModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {


        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_IRON_BLOCK.getLeft());

        TextureMap textureMap = new TextureMap()
                .put(TextureKey.UP, new Identifier(HellMod.MODID, "block/stageblock/stage_block_top"))
                .put(TextureKey.DOWN, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.NORTH, new Identifier(HellMod.MODID, "block/stageblock/stage_block_front"))
                .put(TextureKey.SOUTH, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.EAST, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.WEST, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.PARTICLE, new Identifier(HellMod.MODID, "block/stageblock/stage_block_top"));

        Identifier modelId = Models.CUBE.upload(
                new Identifier(HellMod.MODID, "stage_block"),
                textureMap,
                blockStateModelGenerator.modelCollector
        );

        blockStateModelGenerator.blockStateCollector.accept(
                BlockStateModelGenerator.createSingletonBlockState(ModBlocks.STAGE_BLOCK.getLeft(), modelId)
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PURE_IRON_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.STAGE_BLOCK.getRight(), Models.GENERATED);
    }
}
