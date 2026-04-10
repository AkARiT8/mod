package dev.hellmod;

import dev.hellmod.BlockedRecipes.BlockedItemsLoader;
import dev.hellmod.BlockedRecipes.BlockedItemsManager;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.items.ModItemGroups;
import dev.hellmod.items.ModItems;
import dev.hellmod.registry.ModBlockEntities;
import dev.hellmod.registry.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v2.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.inventory.LootableInventory;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HellMod implements ModInitializer {
	public static final String MOD_ID = "hellmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final String MODID = "hellmod";

	private static MinecraftServer SERVER = null;

	public static final BlockedItemsManager manager = new BlockedItemsManager();

	@Override
	public void onInitialize() {

		ModItems.registerItems();
		ModItemGroups.registerItemsGroups();
		ModBlocks.registerBlocks();
		ModBlockEntities.register();

		ModScreenHandlers.register();

		BlockedItemsLoader.load(manager);

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayerEntity player = handler.player;
			manager.resetBlockedItems();
		});

		LootTableEvents.MODIFY.register((key, tableBuilder, src)  ->  {
			if(key.getValue().equals(new Identifier("minecraft","entities/zombie"))){

				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.with(ItemEntry.builder(ModItems.PURE_IRON_INGOT));
				tableBuilder.pool(poolBuilder.build());
			}else{return;}
		});

		ServerLifecycleEvents.SERVER_STARTING.register(server -> SERVER  = server);
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> SERVER  = null);

	}
}