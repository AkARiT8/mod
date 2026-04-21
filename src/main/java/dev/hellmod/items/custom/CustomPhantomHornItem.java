package dev.hellmod.items.custom;

import dev.hellmod.entity.BossPhantomEntity;
import dev.hellmod.entity.ModEntities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class CustomPhantomHornItem extends Item {

    public CustomPhantomHornItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.TOOT_HORN;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);

        world.playSound(
                null,
                user.getBlockPos(),
                SoundEvents.ENTITY_WITHER_SPAWN,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f
        );

        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {

        if (world.isClient) return;
        if (!(user instanceof PlayerEntity player)) return;

        int usedTicks = this.getMaxUseTime(stack) - remainingUseTicks;


        if (usedTicks < this.getMaxUseTime(stack)) {
            player.getItemCooldownManager().set(this, 200);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        if (!world.isClient && user instanceof PlayerEntity player) {
            ServerWorld serverWorld = (ServerWorld) world;

            BossPhantomEntity boss = ModEntities.BOSS_PHANTOM.create(serverWorld);

            if (boss != null) {
                boss.refreshPositionAndAngles(
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        0,
                        0
                );

                boss.initialize(
                        serverWorld,
                        serverWorld.getLocalDifficulty(boss.getBlockPos()),
                        SpawnReason.EVENT,
                        null
                );

                serverWorld.spawnEntity(boss);
            }
        }

        stack.decrement(1);

        return stack;
    }
}