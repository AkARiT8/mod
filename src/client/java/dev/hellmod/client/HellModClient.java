package dev.hellmod.client;

import dev.hellmod.client.Renderer.*;
import dev.hellmod.client.model.AirshipModel;
import dev.hellmod.client.model.SnotProjectileModel;
import dev.hellmod.client.model.SnoterModel;
import dev.hellmod.client.model.SnoterModelTier2;
import dev.hellmod.entity.ModEntities;
import dev.hellmod.items.ModItems;
import dev.hellmod.network.AirshipAscendPayload;
import dev.hellmod.network.EventSyncPayload;
import dev.hellmod.network.ShowTotemPayload;
import dev.hellmod.registry.ModScreenHandlers;
import dev.hellmod.client.screen.StageScreen;

import dev.hellmod.stage.manager.StageManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.concurrent.atomic.AtomicInteger;

public class HellModClient implements ClientModInitializer {

	public static KeyBinding ASCEND_KEY;

	@Override
	public void onInitializeClient() {


		ASCEND_KEY = KeyBindingHelper.registerKeyBinding(
				new KeyBinding(
						"key.airship.ascend",
						InputUtil.Type.KEYSYM,
						GLFW.GLFW_KEY_SPACE,
						"category.airship"
				)
		);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {

			if (client.player == null) return;

			boolean pressed = InputUtil.isKeyPressed(
					client.getWindow().getHandle(),
					GLFW.GLFW_KEY_SPACE
			);

			ClientPlayNetworking.send(
					new AirshipAscendPayload(
							client.options.jumpKey.isPressed(),
							client.options.useKey.isPressed()
					)
			);
		});


		PayloadTypeRegistry.playS2C().register(

				EventSyncPayload.ID,

				EventSyncPayload.CODEC
		);

		dev.hellmod.client.ClientPacketHandler.init();

		ModClientNetworking.register();

		AtomicInteger shaderReloadTimer = new AtomicInteger();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {

			if (client.world == null) {
				return;
			}

			int stage =
					StageManager.getCurrentStage();

			if (stage == 1){
				CameraRoll.ROLL = 0f;
			}
//			if (stage == 2) {
//
//				CameraRoll.ROLL = 20f;
//
//				shaderReloadTimer.getAndIncrement();
//
//
//				if (shaderReloadTimer.get() >= 0) {
//
//					shaderReloadTimer.set(0);
//
//					try {
//
//						client.gameRenderer
//								.disablePostProcessor();
//
//						client.gameRenderer
//								.loadPostProcessor(
//
//										new Identifier(
//												"hellmod",
//												"shaders/post/grayscale.json"
//										)
//								);
//
//					} catch (Exception e) {
//
//						e.printStackTrace();
//					}
//				}
//			}

			else {

				shaderReloadTimer.set(0);

				client.gameRenderer
						.disablePostProcessor();
			}
		});
		ModelPredicateProviderRegistry.register(
				ModItems.TRUE_AMETHYST_BOW,
				new Identifier("pulling"),
				(stack, world, entity, seed) -> {
					return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
				}
		);

		ModTooltipHandler.register();

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
				ModEntities.AIRSHIP,
				AirshipRenderer::new
		);

		EntityRendererRegistry.register(
				ModEntities.SNOTER,
				SnoterRenderer::new
		);

		EntityRendererRegistry.register(
				ModEntities.SNOTER_TIER_2,
				SnoterTier2Renderer::new
		);

		EntityRendererRegistry.register(
				ModEntities.SNOT_PROJECTILE,
				SnotProjectileRenderer::new
		);

		EntityRendererRegistry.register(
				ModEntities.SNOT_PROJECTILE_TIER2,
				SnotProjectileTier2Renderer::new
		);

		EntityRendererRegistry.register(
				ModEntities.INFERNAL_KNIGHT,
				InfernalKnightRenderer::new
		);

		EntityRendererRegistry.register(
				ModEntities.INFERNAL_ARCHER,
				InfernalArcherRenderer::new
		);


		HandledScreens.register(
				ModScreenHandlers.STAGE_SCREEN_HANDLER,
				StageScreen::new
		);

		EntityModelLayerRegistry.registerModelLayer(
				AirshipModel.LAYER_LOCATION,
				AirshipModel::getTexturedModelData
		);

		EntityModelLayerRegistry.registerModelLayer(
				SnoterModel.LAYER_LOCATION,
				SnoterModel::getTexturedModelData
		);

		EntityModelLayerRegistry.registerModelLayer(
				SnotProjectileModel.LAYER_LOCATION,
				SnotProjectileModel::getTexturedModelData
		);

		EntityModelLayerRegistry.registerModelLayer(
				SnoterModelTier2.LAYER_LOCATION,
				SnoterModelTier2::getTexturedModelData
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