package dev.hellmod.datagen;

import dev.hellmod.HellMod;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class DatagenModelProvider extends FabricModelProvider {

    public DatagenModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {


        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_IRON_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_GOLD_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_DIAMOND_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_EMERALD_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_NETHERITE_BLOCK.getLeft());

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
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.STAGE_BLOCK.getLeft(), modelId);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PURE_IRON_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PURE_GOLD_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PURE_DIAMOND,Models.GENERATED);
        itemModelGenerator.register(ModItems.PURE_EMERALD,Models.GENERATED);
        itemModelGenerator.register(ModItems.PURE_NETHERITE_INGOT,Models.GENERATED);
        itemModelGenerator.register(ModItems.ENCHANTED_GOLDEN_CARROT,Models.GENERATED);
        itemModelGenerator.register(ModItems.SPEED_TOTEM_OF_UNDYING,Models.GENERATED);
        itemModelGenerator.register(ModItems.BASIC_HARDCORE_HEART,Models.GENERATED);
        itemModelGenerator.register(ModItems.OVERWORLD_ESSENCE,Models.GENERATED);
        itemModelGenerator.register(ModItems.BLAZE_MAIN_ROD,Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGMA_CUBE_CORE,Models.GENERATED);

        itemModelGenerator.register(ModItems.NETHER_INFUSED_INGOT, Models.GENERATED);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.NETHER_INFUSED_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.NETHER_INFUSED_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.NETHER_INFUSED_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.NETHER_INFUSED_BOOTS);


    }
}
