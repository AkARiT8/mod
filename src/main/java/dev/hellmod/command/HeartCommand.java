package dev.hellmod.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.nbt.NbtCompound;

import static net.minecraft.server.command.CommandManager.*;
import net.minecraft.command.argument.EntityArgumentType;

public class HeartCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {

        dispatcher.register(
                literal("heart")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(argument("target", EntityArgumentType.player())
                                .then(literal("reset")
                                        .executes(ctx -> {

                                            ServerPlayerEntity player =
                                                    EntityArgumentType.getPlayer(ctx, "target");

                                            NbtCompound nbt = player.writeNbt(new NbtCompound());
                                            nbt.putInt("extra_hearts", 0);
                                            player.readNbt(nbt);

                                            var attr = player.getAttributeInstance(
                                                    net.minecraft.entity.attribute.EntityAttributes.GENERIC_MAX_HEALTH
                                            );

                                            if (attr != null) {
                                                attr.setBaseValue(20.0);
                                            }

                                            ctx.getSource().sendFeedback(
                                                    () -> Text.literal("Hearts reset for " + player.getName().getString()),
                                                    true
                                            );

                                            return 1;
                                        })
                                )
                        )
        );
    }
}