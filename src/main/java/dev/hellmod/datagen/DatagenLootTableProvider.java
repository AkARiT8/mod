package dev.hellmod.datagen;

import dev.hellmod.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class DatagenLootTableProvider extends FabricBlockLootTableProvider {

    public DatagenLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.PURE_IRON_BLOCK.getLeft(), drops(ModBlocks.PURE_IRON_BLOCK.getRight()));
        addDrop(ModBlocks.PURE_GOLD_BLOCK.getLeft(), drops(ModBlocks.PURE_GOLD_BLOCK.getRight()));
        addDrop(ModBlocks.PURE_DIAMOND_BLOCK.getLeft(), drops(ModBlocks.PURE_DIAMOND_BLOCK.getRight()));
        addDrop(ModBlocks.PURE_EMERALD_BLOCK.getLeft(), drops(ModBlocks.PURE_EMERALD_BLOCK.getRight()));
        addDrop(ModBlocks.PURE_NETHERITE_BLOCK.getLeft(), drops(ModBlocks.PURE_NETHERITE_BLOCK.getRight()));
        addDrop(ModBlocks.EMPOWERED_COAL_BLOCK.getLeft(), drops(ModBlocks.EMPOWERED_COAL_BLOCK.getRight()));
    }
}
