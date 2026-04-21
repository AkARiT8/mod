package dev.hellmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<BossZombieEntity> BOSS_ZOMBIE =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    new Identifier("hellmod", "boss_zombie"),
                    FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BossZombieEntity::new)
                            .dimensions(EntityDimensions.fixed(1.2f, 3.5f))
                            .trackRangeBlocks(128)
                            .trackedUpdateRate(1)
                            .build()
            );

    public static final EntityType<BossCreeperEntity> BOSS_CREEPER =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    new Identifier("hellmod", "boss_creeper"),
                    FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BossCreeperEntity::new)
                            .dimensions(EntityDimensions.fixed(2.4f, 7f))
                            .trackRangeBlocks(128)
                            .trackedUpdateRate(1)
                            .build()
            );

    public static final EntityType<BossPhantomEntity> BOSS_PHANTOM =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    new Identifier("hellmod", "boss_phantom"),
                    FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BossPhantomEntity::new)
                            .dimensions(EntityDimensions.fixed(2.4f, 7f))
                            .trackRangeBlocks(128)
                            .trackedUpdateRate(1)
                            .build()
            );

    public static void register() {}

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(
                BOSS_ZOMBIE,
                BossZombieEntity.createBossAttributes()
        );

        FabricDefaultAttributeRegistry.register(
                BOSS_CREEPER,
                BossCreeperEntity.createAttributes()
        );

        FabricDefaultAttributeRegistry.register(
                BOSS_PHANTOM,
                BossPhantomEntity.createAttributes()
        );
    }
}