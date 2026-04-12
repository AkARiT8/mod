package dev.hellmod.datagen;

import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DatagenModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public DatagenModBlockTagProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> lookupFuture
    ) {
        super(output, lookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(
                        ModBlocks.PURE_IRON_BLOCK.getLeft(),
                        ModBlocks.PURE_GOLD_BLOCK.getLeft(),
                        ModBlocks.PURE_DIAMOND_BLOCK.getLeft(),
                        ModBlocks.PURE_EMERALD_BLOCK.getLeft(),
                        ModBlocks.PURE_NETHERITE_BLOCK.getLeft()
                );

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        ModBlocks.PURE_IRON_BLOCK.getLeft(),
                        ModBlocks.PURE_GOLD_BLOCK.getLeft()
                );

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(
                        ModBlocks.PURE_DIAMOND_BLOCK.getLeft(),
                        ModBlocks.PURE_EMERALD_BLOCK.getLeft(),
                        ModBlocks.PURE_NETHERITE_BLOCK.getLeft()
                );
    }
}