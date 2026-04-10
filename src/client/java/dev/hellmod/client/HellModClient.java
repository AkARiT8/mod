package dev.hellmod.client;

import dev.hellmod.registry.ModScreenHandlers;
import dev.hellmod.client.screen.StageScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class HellModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		HandledScreens.register(
				ModScreenHandlers.STAGE_SCREEN_HANDLER,
				StageScreen::new
		);

	}
}