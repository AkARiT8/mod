package dev.hellmod.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.hellmod.events.LightningStormEvent;
import dev.hellmod.events.WorldEventManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class EventCommand {

    public static void register(
            CommandDispatcher<ServerCommandSource> dispatcher
    ) {

        dispatcher.register(

                literal("event")

                        .requires(source ->
                                source.hasPermissionLevel(2)
                        )

                        // =====================================
                        // LIGHTNING STORM
                        // =====================================

                        .then(

                                literal("lightning")

                                        .executes(ctx -> {

                                            WorldEventManager
                                                    .forceStartEvent(

                                                            new LightningStormEvent(),

                                                            ctx.getSource()
                                                                    .getWorld()
                                                    );

                                            ctx.getSource()
                                                    .sendFeedback(

                                                            () -> Text.literal(
                                                                    "Lightning storm started"
                                                            ),

                                                            true
                                                    );

                                            return 1;
                                        })
                        )
        );
    }
}