package dev.hellmod;

import dev.hellmod.BlockedRecipes.BlockedItemsLoader;
import dev.hellmod.BlockedRecipes.BlockedItemsManager;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.blocks.custom.StageData;
import dev.hellmod.command.HeartCommand;
import dev.hellmod.command.StageCommand;
import dev.hellmod.items.ModItemGroups;
import dev.hellmod.items.ModItems;
import dev.hellmod.items.ModArmorMaterials;
import dev.hellmod.network.ShowTotemPayload;
import dev.hellmod.registry.ModBlockEntities;
import dev.hellmod.registry.ModScreenHandlers;
import dev.hellmod.stage.StageEffects;
import dev.hellmod.stage.modifier.EntityModifierRegistry;
import dev.hellmod.stage.modifier.LootDropHandler;
import dev.hellmod.stage.modifier.StageModifierApplier;
import dev.hellmod.stage.modifier.StageModifierManager;
import dev.hellmod.stage.modifier.impl.*;
import dev.hellmod.stage.recipe.StageRecipeReloadListener;
import dev.hellmod.util.ModItemEffect;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.hellmod.util.ModItemEffect.isSpeedTotem;
import static dev.hellmod.util.PlayerUtils.syncHealth;


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



		ModArmorMaterials.registerArmorMaterials();

		System.out.println("ARMOR MATERIALS: " + net.minecraft.registry.Registries.ARMOR_MATERIAL.getIds());

		ModItems.registerItems();
		ModItemGroups.registerItemsGroups();
		ModBlocks.registerBlocks();
		ModBlockEntities.register();

		ModScreenHandlers.register();

		BlockedItemsLoader.load(manager);

		EntityModifierRegistry.register("equipment", new EquipmentModifier());
		EntityModifierRegistry.register("effects", new EffectModifier());
		EntityModifierRegistry.register("charged", new ChargedModifier());
		EntityModifierRegistry.register("fireball", new GhastModifier());
		EntityModifierRegistry.register("blaze_fireball", new BlazeModifier());
		EntityModifierRegistry.register("loot", new LootModifier());
		EntityModifierRegistry.register("health", new HealthModifier());

		LootDropHandler.register();

		ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {

			if (!(entity instanceof LivingEntity living)) return;
			if (!(world instanceof ServerWorld serverWorld)) return;

			int stage = StageData.get(serverWorld).getStage();

			var json = StageModifierManager.get(stage);
			if (json == null) return;

			if (!json.has("difmod")) return;
			var difmod = json.getAsJsonObject("difmod");

			String type = Registries.ENTITY_TYPE.getId(entity.getType()).getPath();

			if (!difmod.has(type)) return;

			var entityData = difmod.getAsJsonObject(type);

			entity.getServer().execute(() -> {
				if (!entity.isRemoved()) {
					StageModifierApplier.apply(living, entityData);
				}
			});
		});


		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (var player : server.getPlayerManager().getPlayerList()) {
				ModItemEffect.tick(player);
			}
		});
		PayloadTypeRegistry.playS2C().register(
				ShowTotemPayload.ID,
				ShowTotemPayload.CODEC
		);

		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(
				new StageRecipeReloadListener()
		);

		ResourceManagerHelper.get(ResourceType.SERVER_DATA)
				.registerReloadListener(new SimpleSynchronousResourceReloadListener() {

					@Override
					public Identifier getFabricId() {
						return new Identifier("hellmod", "stage_modifiers");
					}

					@Override
					public void reload(ResourceManager manager) {
						StageModifierManager.load(manager);
					}
				});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			StageCommand.register(dispatcher);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			HeartCommand.register(dispatcher);
		});

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayerEntity player = handler.player;
			manager.resetBlockedItems();
		});

		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			syncHealth(newPlayer);
		});

		ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, damageAmount) -> {

			if (!(entity instanceof PlayerEntity player)) return true;

			ItemStack main = player.getMainHandStack();
			ItemStack off = player.getOffHandStack();


			if (isSpeedTotem(off)) {
				useTotem(player, off);
				return false;
			}

			if (isSpeedTotem(main)) {
				useTotem(player, main);
				return false;
			}

			return true;
		});

		ServerTickEvents.END_SERVER_TICK.register(server -> {

			if (StageEffects.skyTimer > 0) {
				StageEffects.skyTimer--;

				for (ServerWorld world : server.getWorlds()) {
					world.setTimeOfDay(world.getTimeOfDay() + 200);
				}
			}
		});



		ServerLifecycleEvents.SERVER_STARTING.register(server -> SERVER  = server);
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> SERVER  = null);

	}
	private static void useTotem(PlayerEntity player, ItemStack stack) {
		stack.decrement(1);

		player.setHealth(1.0F);

		player.clearStatusEffects();

		player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));

		player.getWorld().playSound(
				null,
				player.getBlockPos(),
				SoundEvents.ITEM_TOTEM_USE,
				SoundCategory.PLAYERS,
				1.0F,
				1.0F
		);
		if (player.getWorld() instanceof ServerWorld serverWorld) {
			serverWorld.spawnParticles(
					ParticleTypes.TOTEM_OF_UNDYING,
					player.getX(),
					player.getY() + 1,
					player.getZ(),
					30,
					0.5,
					1.0,
					0.5,
					0.1
			);
		}

		if (player instanceof ServerPlayerEntity serverPlayer) {
			ServerPlayNetworking.send(serverPlayer, new ShowTotemPayload());
		}
	}
}