package dev.hellmod.client;

import dev.hellmod.datagen.*;
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
		pack.addProvider(DatagenModBlockTagProvider::new);
		pack.addProvider(DatagenModLanguageProvider::new);

	}
}
