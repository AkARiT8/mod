package dev.hellmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import dev.hellmod.blocks.custom.StageData;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class StageCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {

        dispatcher.register(
                CommandManager.literal("stage")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(CommandManager.literal("set")
                                .then(CommandManager.argument("value", IntegerArgumentType.integer(0))
                                        .executes(ctx -> {

                                            int value = IntegerArgumentType.getInteger(ctx, "value");

                                            var server = ctx.getSource().getServer();

                                            for (ServerWorld world : server.getWorlds()) {
                                                StageData data = StageData.get(world);
                                                data.setStage(value);
                                                data.setStageStepTo0();
                                            }

                                            ctx.getSource().sendFeedback(
                                                    () -> Text.literal("Stage cambiado a: " + value),
                                                    false
                                            );

                                            return 1;
                                        })
                                )
                        )

                        .then(CommandManager.literal("reset")
                                .executes(ctx -> {

                                    ServerWorld world = ctx.getSource().getWorld();
                                    StageData.get(world).setStage(0);
                                    StageData.get(world).setStageStepTo0();

                                    ctx.getSource().sendFeedback(
                                            () -> Text.literal("Stage reseteado a 0"),
                                            false
                                    );

                                    return 1;
                                })
                        )
        );
    }
}