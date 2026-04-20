package dev.hellmod.client;

import dev.hellmod.entity.BossPhantomEntity;
import dev.hellmod.entity.ModEntities;
import dev.hellmod.items.ModItems;
import dev.hellmod.network.ShowTotemPayload;
import dev.hellmod.registry.ModScreenHandlers;
import dev.hellmod.client.screen.StageScreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class HellModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		ModelPredicateProviderRegistry.register(
				ModItems.TRUE_AMETHYST_BOW,
				new Identifier("pulling"),
				(stack, world, entity, seed) -> {
					return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
				}
		);

		ModelPredicateProviderRegistry.register(
				ModItems.TRUE_AMETHYST_BOW,
				new Identifier("pull"),
				(stack, world, entity, seed) -> {
					if (entity == null) return 0.0F;
					if (entity.getActiveItem() != stack) return 0.0F;
					return (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
				}
		);

		ModelPredicateProviderRegistry.register(
				ModItems.TRUE_AMETHYST_SHIELD,
				new Identifier("blocking"),
				(stack, world, entity, seed) -> {
					return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
				}
		);
		ModelPredicateProviderRegistry.register(
				ModItems.TRUE_AMETHYST_SHIELD,
				new Identifier("blocking"),
				(stack, world, entity, seed) -> {
					return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
				}
		);


		EntityRendererRegistry.register(
				ModEntities.BOSS_ZOMBIE,
				BossZombieRenderer::new
		);

		EntityRendererRegistry.register(
				ModEntities.BOSS_CREEPER,
				BossCreeperRenderer::new
		);

		EntityRendererRegistry.register(
				ModEntities.BOSS_PHANTOM,
				BossPhantomRenderer::new
		);


		HandledScreens.register(
				ModScreenHandlers.STAGE_SCREEN_HANDLER,
				StageScreen::new
		);

		ClientPlayNetworking.registerGlobalReceiver(
				ShowTotemPayload.ID,
				(payload, context) -> {

					context.client().execute(() -> {

						Item item = Registries.ITEM.get(payload.itemId());

						MinecraftClient.getInstance().gameRenderer.showFloatingItem(
								new ItemStack(item)
						);

					});

				}
		);
	}
}