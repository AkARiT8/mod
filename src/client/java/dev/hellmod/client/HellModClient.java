package dev.hellmod.client;

import dev.hellmod.entity.ModEntities;
import dev.hellmod.network.ShowTotemPayload;
import dev.hellmod.registry.ModScreenHandlers;
import dev.hellmod.client.screen.StageScreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

public class HellModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {


		EntityRendererRegistry.register(
				ModEntities.BOSS_ZOMBIE,
				BossZombieRenderer::new
		);

		EntityRendererRegistry.register(
				ModEntities.BOSS_CREEPER,
				BossCreeperRenderer::new
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