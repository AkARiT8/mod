package dev.hellmod.entity;

import dev.hellmod.HellMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<AirshipEntity> AIRSHIP =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of("hellmod", "airship"),
                    FabricEntityTypeBuilder
                            .create(SpawnGroup.MISC, AirshipEntity::new)
                            .dimensions(EntityDimensions.fixed(3f, 2f))
                            .trackRangeBlocks(128)
                            .trackedUpdateRate(40)
                            .build()
            );

    public static final EntityType<SnoterEntity> SNOTER =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of("hellmod", "snoter"),
                    FabricEntityTypeBuilder.create(
                                    SpawnGroup.MONSTER,
                                    SnoterEntity::new
                            )
                            .dimensions(EntityDimensions.fixed(0.8f, 0.8f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(3)
                            .build()
            );

    public static final EntityType<SnotProjectileEntity> SNOT_PROJECTILE =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of("hellmod", "snot_projectile"),
                    FabricEntityTypeBuilder.<SnotProjectileEntity>create()
                            .entityFactory(SnotProjectileEntity::new)
                            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    public static final EntityType<InfernalKnightEntity> INFERNAL_KNIGHT =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of(HellMod.MOD_ID, "infernal_knight"),
                    FabricEntityTypeBuilder.create(
                                    SpawnGroup.MONSTER,
                                    InfernalKnightEntity::new
                            )
                            .dimensions(EntityDimensions.fixed(0.9f, 2.9f))
                            .build()
            );

    public static final EntityType<InfernalArcherEntity> INFERNAL_ARCHER =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of(HellMod.MOD_ID, "infernal_archer"),
                    FabricEntityTypeBuilder.create(
                                    SpawnGroup.MONSTER,
                                    InfernalArcherEntity::new
                            )
                            .dimensions(EntityDimensions.fixed(0.6f, 1.8f))
                            .build()
            );

    public static final EntityType<SnoterTier2Entity> SNOTER_TIER_2 =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of(HellMod.MOD_ID, "snoter_tier_2"),
                    FabricEntityTypeBuilder.create(
                                    SpawnGroup.MONSTER,
                                    SnoterTier2Entity::new)
                            .dimensions(EntityDimensions.fixed(1.0f, 1.0f))
                            .build()
            );

    public static final EntityType<SnotProjectileTier2Entity> SNOT_PROJECTILE_TIER2 =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of("hellmod", "snot_projectile_tier2"),
                    FabricEntityTypeBuilder.<SnotProjectileTier2Entity>create()
                            .entityFactory(SnotProjectileTier2Entity::new)
                            .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build()
            );

    public static final EntityType<DoomSkeletonEntity> DOOM_SKELETON =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of(HellMod.MOD_ID, "doom_skeleton"),
                    FabricEntityTypeBuilder.createMob()
                            .entityFactory(DoomSkeletonEntity::new)
                            .spawnGroup(SpawnGroup.MONSTER)
                            .dimensions(EntityDimensions.fixed(0.6f, 1.99f))
                            .build()
            );

    public static final EntityType<DoomCreeperEntity> DOOM_CREEPER =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of("hellmod", "doom_creeper"),
                    FabricEntityTypeBuilder.create(
                                    SpawnGroup.MONSTER,
                                    DoomCreeperEntity::new
                            )
                            .dimensions(EntityDimensions.fixed(0.6F, 1.7F))
                            .build()
            );

    public static final EntityType<DoomZombieEntity> DOOM_ZOMBIE =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of("hellmod", "doom_zombie"),
                    FabricEntityTypeBuilder.create(
                                    SpawnGroup.MONSTER,
                                    DoomZombieEntity::new
                            )
                            .dimensions(EntityDimensions.fixed(0.6F, 1.95F))
                            .build()
            );


    public static void register() {}

    public static void registerAttributes() {

        FabricDefaultAttributeRegistry.register(
                SNOTER,
                SnoterEntity.createAttributes()
        );

        FabricDefaultAttributeRegistry.register(
                SNOTER_TIER_2,
                SnoterEntity.createAttributes()
        );

        FabricDefaultAttributeRegistry.register(
                ModEntities.INFERNAL_KNIGHT,
                InfernalKnightEntity.createAttributes()
        );

        FabricDefaultAttributeRegistry.register(
                ModEntities.INFERNAL_ARCHER,
                InfernalArcherEntity.createAttributes()
        );

        FabricDefaultAttributeRegistry.register(
                ModEntities.DOOM_SKELETON,
                DoomSkeletonEntity.createAttributes()
        );

        FabricDefaultAttributeRegistry.register(
                ModEntities.DOOM_CREEPER,
                DoomCreeperEntity.createAttributes()
        );

        FabricDefaultAttributeRegistry.register(
                ModEntities.DOOM_ZOMBIE,
                DoomZombieEntity.createAttributes()
        );
    }
}