package dev.hellmod.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.hellmod.structures.BunkerDoor;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class BunkerCommand {

    public static void register(
            CommandDispatcher<ServerCommandSource> dispatcher
    ) {

        dispatcher.register(

                literal("bunker")

                        .requires(source ->
                                source.hasPermissionLevel(2)
                        )

                        .then(

                                literal("open")

                                        .executes(ctx -> {

                                            BunkerDoor.open();

                                            ctx.getSource()
                                                    .sendFeedback(

                                                            () -> Text.literal(
                                                                    "Bunker door opening..."
                                                            ),

                                                            true
                                                    );

                                            return 1;
                                        })
                        )

                        .then(

                                literal("close")

                                        .executes(ctx -> {

                                            BunkerDoor.close();

                                            ctx.getSource()
                                                    .sendFeedback(

                                                            () -> Text.literal(
                                                                    "Bunker door closing..."
                                                            ),

                                                            true
                                                    );

                                            return 1;
                                        })
                        )
        );
    }
}