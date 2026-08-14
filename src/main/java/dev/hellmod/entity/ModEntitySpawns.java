package dev.hellmod.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;

public class ModEntitySpawns {

    public static void init() {

        BiomeModifications.addSpawn(
                BiomeSelectors.foundInOverworld(),
                SpawnGroup.MONSTER,
                ModEntities.SNOTER,
                40,
                1,
                2

        );
        BiomeModifications.addSpawn(
                BiomeSelectors.foundInOverworld(),
                SpawnGroup.MONSTER,
                ModEntities.SNOTER_TIER_2,
                20,
                1,
                2

        );

        SpawnRestriction.register(
                ModEntities.SNOTER,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                SnoterEntity::canSpawn
        );


        SpawnRestriction.register(
                ModEntities.SNOTER_TIER_2,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                SnoterTier2Entity::canSpawnTier2
        );
    }

}