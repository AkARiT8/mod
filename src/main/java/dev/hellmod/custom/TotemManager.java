package dev.hellmod.custom;

import dev.hellmod.network.ShowTotemPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import net.minecraft.particle.ParticleTypes;

import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class TotemManager {

    private static final Map<Identifier, TotemEffect> TOTEMS = new HashMap<>();

    static {

        register("hellmod:speed_totem_of_undying", (player) -> {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1));
        });
        register("hellmod:barrier_totem_of_undying", (player) -> {
        });
    }

    public static boolean isTotem(ItemStack stack) {
        return TOTEMS.containsKey(Registries.ITEM.getId(stack.getItem()));
    }

    public static void useTotem(PlayerEntity player, ItemStack stack) {

        Identifier id = Registries.ITEM.getId(stack.getItem());
        TotemEffect effect = TOTEMS.get(id);

        if (effect == null) return;

        stack.decrement(1);

        player.setHealth(1.0F);
        player.clearStatusEffects();

        effect.apply(player);

        player.getWorld().playSound(
                null,
                player.getBlockPos(),
                SoundEvents.ITEM_TOTEM_USE,
                SoundCategory.PLAYERS,
                1.0F,
                1.0F
        );

        if (player.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.TOTEM_OF_UNDYING,
                    player.getX(),
                    player.getY() + 1,
                    player.getZ(),
                    30,
                    0.5,
                    1.0,
                    0.5,
                    0.1
            );
        }

        if (player instanceof ServerPlayerEntity serverPlayer) {
            ServerPlayNetworking.send(
                    serverPlayer,
                    new ShowTotemPayload(id)
            );
        }
    }

    private static void register(String id, TotemEffect effect) {
        TOTEMS.put(new Identifier(id), effect);
    }

    @FunctionalInterface
    private interface TotemEffect {
        void apply(PlayerEntity player);
    }
}