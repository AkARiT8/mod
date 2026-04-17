package dev.hellmod;

import dev.hellmod.BlockedRecipes.BlockedItemsLoader;
import dev.hellmod.BlockedRecipes.BlockedItemsManager;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.blocks.custom.StageData;
import dev.hellmod.command.HeartCommand;
import dev.hellmod.command.StageCommand;
import dev.hellmod.custom.TotemManager;
import dev.hellmod.entity.ModEntities;
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
import dev.hellmod.util.VariantHolder;
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
import net.minecraft.entity.mob.CreeperEntity;
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
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.hellmod.util.PlayerUtils.syncHealth;


public class HellMod implements ModInitializer {
	public static final String MOD_ID = "hellmod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final String MODID = "hellmod";

	private static MinecraftServer SERVER = null;

	public static final BlockedItemsManager manager = new BlockedItemsManager();

	@Override
	public void onInitialize() {

		ModArmorMaterials.registerArmorMaterials();

		ModItems.registerItems();
		ModItemGroups.registerItemsGroups();
		ModBlocks.registerBlocks();
		ModBlockEntities.register();
		ModEntities.register();
		ModEntities.registerAttributes();

		manager.resetBlockedItems();;

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

			if (entityData.has("variants") && entity instanceof CreeperEntity creeper) {

				VariantHolder holder = (VariantHolder) creeper;
				var variants = entityData.getAsJsonArray("variants");

				if (holder.hellmod$getVariant() == null || holder.hellmod$getVariant().isEmpty()) {

					int totalWeight = 0;
					for (var v : variants) {
						totalWeight += v.getAsJsonObject().get("weight").getAsInt();
					}

					int roll = serverWorld.getRandom().nextInt(totalWeight);
					int current = 0;

					for (var v : variants) {
						var obj = v.getAsJsonObject();
						current += obj.get("weight").getAsInt();

						if (roll < current) {
							entityData = obj;

							String tag = obj.get("tag").getAsString();
							holder.hellmod$setVariant(tag);

							if (obj.has("fuse_time")) {
								int fuse = obj.get("fuse_time").getAsInt();
								holder.hellmod$setFuseTime(fuse);
							}

							break;
						}
					}

				} else {
					String currentVariant = holder.hellmod$getVariant();

					for (var v : variants) {
						var obj = v.getAsJsonObject();

						if (obj.get("tag").getAsString().equals(currentVariant)) {
							entityData = obj;
							break;
						}
					}
				}
			}

			if (entity.isRemoved()) return;

			if (entity.age <= 1) {
				StageModifierApplier.apply(living, entityData);
			}
		});


		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (var player : server.getPlayerManager().getPlayerList()) {
				ModItemEffect.tick(player);
			}

			boolean active = false;

			for (ServerWorld world : server.getWorlds()) {

				int stage = StageData.get(world).getStage();

				if (stage >= 3) {
					active = true;
					break;
				}
			}

			dev.hellmod.util.SpawnController.STAGE_3_ACTIVE = active;
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

			ItemStack off = player.getOffHandStack();
			if (TotemManager.isTotem(off)) {
				TotemManager.useTotem(player, off);
				return false;
			}

			ItemStack main = player.getMainHandStack();
			if (TotemManager.isTotem(main)) {
				TotemManager.useTotem(player, main);
				return false;
			}

			return true;
		});


		ServerTickEvents.END_SERVER_TICK.register(server -> {

			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

				ItemStack main = player.getMainHandStack();
				ItemStack off = player.getOffHandStack();

				boolean hasTotem =
						main.isOf(ModItems.BARRIER_TOTEM_OF_UNDYING) ||
								off.isOf(ModItems.BARRIER_TOTEM_OF_UNDYING);

				if (!hasTotem) continue;

				if (player.age % 300 == 0) {

					player.addStatusEffect(new StatusEffectInstance(
							StatusEffects.ABSORPTION,
							200,
							0
					));
				}
			}
		});

		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			for (ServerWorld world : server.getWorlds()) {
				world.getGameRules().get(GameRules.DO_INSOMNIA).set(false, server);
			}
		});

		ServerLifecycleEvents.SERVER_STARTING.register(server -> SERVER  = server);
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> SERVER  = null);

	}
}