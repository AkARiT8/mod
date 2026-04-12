package dev.hellmod.registry;

import dev.hellmod.screen.StageScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static final ScreenHandlerType<StageScreenHandler> STAGE_SCREEN_HANDLER =
            Registry.register(
                    Registries.SCREEN_HANDLER,
                    new Identifier("hellmod", "stage"),
                    new ScreenHandlerType<>(StageScreenHandler::new, FeatureFlags.VANILLA_FEATURES)
            );

    public static void register() {
    }
}