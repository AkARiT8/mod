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
    }
}
