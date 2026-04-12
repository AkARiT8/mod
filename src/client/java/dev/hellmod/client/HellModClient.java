package dev.hellmod.client;

import dev.hellmod.items.ModItems;
import dev.hellmod.network.ShowTotemPayload;
import dev.hellmod.registry.ModScreenHandlers;
import dev.hellmod.client.screen.StageScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.item.ItemStack;

public class HellModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		HandledScreens.register(
				ModScreenHandlers.STAGE_SCREEN_HANDLER,
				StageScreen::new
		);
		ClientPlayNetworking.registerGlobalReceiver(
				ShowTotemPayload.ID,
				(payload, context) -> {

					context.client().execute(() -> {
						MinecraftClient.getInstance().gameRenderer.showFloatingItem(
								new ItemStack(ModItems.SPEED_TOTEM_OF_UNDYING)
						);
					});

				}
		);

	}
}