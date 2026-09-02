package dev.hellmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryKey;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class DatagenEntityLootTableProvider extends SimpleFabricLootTableProvider {

    public DatagenEntityLootTableProvider(FabricDataOutput output,
                                          CompletableFuture<RegistryWrapper.WrapperLookup> registries) {
        super(output, registries, LootContextTypes.ENTITY);
    }

    @Override
    public void accept(RegistryWrapper.WrapperLookup registries,
                       BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter) {
    }
}