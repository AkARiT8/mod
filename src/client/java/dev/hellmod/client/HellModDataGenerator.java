package dev.hellmod.client;

import dev.hellmod.datagen.DatagenAdvancementProvider;
import dev.hellmod.datagen.DatagenLootTableProvider;
import dev.hellmod.datagen.DatagenModelProvider;
import dev.hellmod.datagen.DatagenRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class HellModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(DatagenModelProvider::new);
		pack.addProvider(DatagenLootTableProvider::new);
		pack.addProvider(DatagenRecipeProvider::new);
		pack.addProvider(DatagenAdvancementProvider::new);

	}
}
