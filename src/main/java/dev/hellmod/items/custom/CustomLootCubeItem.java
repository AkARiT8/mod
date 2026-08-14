package dev.hellmod.items.custom;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class CustomLootCubeItem extends Item {

    private final RegistryKey<LootTable> commonLoot;
    private final RegistryKey<LootTable> uncommonLoot;
    private final RegistryKey<LootTable> rareLoot;
    private final RegistryKey<LootTable> legendaryLoot;

    public CustomLootCubeItem(
            Settings settings,
            String lootPrefix
    ) {
        super(settings);

        this.commonLoot = createLootKey(
                lootPrefix + "/common"
        );

        this.uncommonLoot = createLootKey(
                lootPrefix + "/uncommon"
        );

        this.rareLoot = createLootKey(
                lootPrefix + "/rare"
        );

        this.legendaryLoot = createLootKey(
                lootPrefix + "/legendary"
        );
    }

    private RegistryKey<LootTable> createLootKey(String name) {

        return RegistryKey.of(
                RegistryKeys.LOOT_TABLE,
                Identifier.of(
                        "hellmod",
                        name
                )
        );
    }

    private Rarity chooseRarity(
            net.minecraft.util.math.random.Random random
    ) {

        int roll = random.nextInt(100);

        if (roll < 60) {
            return Rarity.COMMON;
        }

        if (roll < 85) {
            return Rarity.UNCOMMON;
        }

        if (roll < 95) {
            return Rarity.RARE;
        }

        return Rarity.LEGENDARY;
    }

    private RegistryKey<LootTable> getLootTable(
            Rarity rarity
    ) {

        return switch (rarity) {

            case COMMON -> commonLoot;
            case UNCOMMON -> uncommonLoot;
            case RARE -> rareLoot;
            case LEGENDARY -> legendaryLoot;
        };
    }

    private void spawnRarityParticles(
            ServerWorld world,
            PlayerEntity player,
            Rarity rarity
    ) {

        Vector3f color;

        int count;

        switch (rarity) {

            case COMMON -> {
                color = new Vector3f(
                        0.667F,
                        0.667F,
                        0.667F
                );
                count = 15;
            }

            case UNCOMMON -> {
                color = new Vector3f(
                        0.333F,
                        1.0F,
                        0.333F
                );
                count = 20;
            }

            case RARE -> {
                color = new Vector3f(
                        0.333F,
                        0.333F,
                        1.0F
                );
                count = 25;
            }

            case LEGENDARY -> {
                color = new Vector3f(
                        1.0F,
                        0.667F,
                        0.0F
                );
                count = 40;
            }

            default -> {
                color = new Vector3f(
                        1.0F,
                        1.0F,
                        1.0F
                );
                count = 15;
            }
        }

        DustParticleEffect dust =
                new DustParticleEffect(
                        color,
                        1.0F
                );

        world.spawnParticles(
                dust,
                player.getX(),
                player.getY() + 1.0,
                player.getZ(),
                count,
                0.4,
                0.5,
                0.4,
                0.05
        );
    }

    @Override
    public TypedActionResult<ItemStack> use(
            World world,
            PlayerEntity player,
            Hand hand
    ) {

        ItemStack stack =
                player.getStackInHand(hand);

        if (player.getItemCooldownManager().isCoolingDown(this)) {
            return TypedActionResult.fail(stack);
        }

        if (!world.isClient()) {

            ServerWorld serverWorld =
                    (ServerWorld) world;

            Rarity rarity =
                    chooseRarity(
                            world.getRandom()
                    );

            RegistryKey<LootTable> selectedTable =
                    getLootTable(rarity);

            spawnRarityParticles(
                    serverWorld,
                    player,
                    rarity
            );

            world.playSound(
                    null,
                    player.getBlockPos(),
                    SoundEvents.ENTITY_CHICKEN_EGG,
                    SoundCategory.PLAYERS,
                    1.0F,
                    1.0F
            );

            LootTable lootTable =
                    serverWorld
                            .getServer()
                            .getReloadableRegistries()
                            .getLootTable(selectedTable);

            LootContextParameterSet parameters =
                    new LootContextParameterSet.Builder(
                            serverWorld
                    )
                            .add(
                                    LootContextParameters.ORIGIN,
                                    player.getPos()
                            )
                            .add(
                                    LootContextParameters.THIS_ENTITY,
                                    player
                            )
                            .build(
                                    LootContextTypes.CHEST
                            );

            lootTable.generateLoot(
                    parameters,
                    lootStack -> {

                        ItemEntity itemEntity =
                                new ItemEntity(
                                        serverWorld,
                                        player.getX(),
                                        player.getY() + 0.5,
                                        player.getZ(),
                                        lootStack
                                );

                        itemEntity.setVelocity(
                                (serverWorld.random.nextDouble() - 0.5)
                                        * 0.2,
                                0.15,
                                (serverWorld.random.nextDouble() - 0.5)
                                        * 0.2
                        );

                        serverWorld.spawnEntity(
                                itemEntity
                        );
                    }
            );


            if (!player.isCreative()) {
                stack.decrement(1);
            }
        }

        player.getItemCooldownManager().set(this, 20);

        return TypedActionResult.success(stack);
    }

    private enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        LEGENDARY
    }
}