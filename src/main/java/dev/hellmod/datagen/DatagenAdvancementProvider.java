package dev.hellmod.datagen;

import dev.hellmod.HellMod;
import dev.hellmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class DatagenAdvancementProvider extends FabricAdvancementProvider {

    public DatagenAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry rootAdvancement = Advancement.Builder.create()
                .display(
                        ModItems.PURE_IRON_INGOT,
                        Text.literal("Stage 0"),
                        Text.literal("yet starting"),
                        new Identifier("/assets/hellmod/textures/item/pure_iron_ingot.png"),
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("got_pure_iron_ingot", InventoryChangedCriterion.Conditions.items(ModItems.PURE_IRON_INGOT))
                .build(consumer, HellMod.MODID + "/root");
    }
}
