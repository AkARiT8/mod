package dev.hellmod.datagen;

import dev.hellmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

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

        RegistryKey<LootTable> creeperBombLoot = RegistryKey.of(
                RegistryKeys.LOOT_TABLE,
                new Identifier("hellmod", "entities/boss_creeper")
        );

        RegistryKey<LootTable> berserkZombieLoot = RegistryKey.of(
                RegistryKeys.LOOT_TABLE,
                new Identifier("hellmod", "entities/boss_zombie")
        );

        RegistryKey<LootTable> gigaPhantomLoot = RegistryKey.of(
                RegistryKeys.LOOT_TABLE,
                new Identifier("hellmod", "entities/boss_phantom")
        );

        exporter.accept(creeperBombLoot,
                LootTable.builder()

                        .pool(LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(6,12))
                                .with(ItemEntry.builder(ModItems.CREEPER_ESSENCE))
                        )

                        .pool(LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1, 3))
                                .with(ItemEntry.builder(Blocks.GOLD_BLOCK.asItem()))
                        )

                        .pool(LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2,4))
                                .with(ItemEntry.builder(Items.DIAMOND))
                        )
        );

        exporter.accept(berserkZombieLoot,
                LootTable.builder()

                        .pool(LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(6,12))
                                .with(ItemEntry.builder(ModItems.ZOMBIE_ESSENCE))
                        )

                        .pool(LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1, 3))
                                .with(ItemEntry.builder(Blocks.GOLD_BLOCK.asItem()))
                        )

                        .pool(LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2,4))
                                .with(ItemEntry.builder(Items.DIAMOND))
                        )
        );

        exporter.accept(gigaPhantomLoot,
                LootTable.builder()

                        .pool(LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(6,12))
                                .with(ItemEntry.builder(ModItems.PHANTOM_ESSENCE))
                        )

                        .pool(LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1, 3))
                                .with(ItemEntry.builder(Blocks.GOLD_BLOCK.asItem()))
                        )

                        .pool(LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2,4))
                                .with(ItemEntry.builder(Items.DIAMOND))
                        )
        );
    }
}