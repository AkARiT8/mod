package dev.hellmod.structures;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.PersistentState;
import net.minecraft.world.biome.BiomeKeys;

import java.util.Optional;

public class WorldStructureGenerator {

    public static BlockPos HELLTREE_MIN;
    public static BlockPos HELLTREE_MAX;

    public static BlockPos INNER_MIN;
    public static BlockPos INNER_MAX;

    public static void init() {

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {

            ServerWorld world = server.getOverworld();

            StructureState state =
                    world.getPersistentStateManager()
                            .getOrCreate(
                                    StructureState.TYPE,
                                    "hellmod_structure"
                            );

            if (state.generated) {

                HELLTREE_MIN = state.min;
                HELLTREE_MAX = state.max;

                INNER_MIN = state.innerMin;
                INNER_MAX = state.innerMax;

                BunkerDoor.setupDoor(
                        HELLTREE_MIN
                );

                return;
            }

            generate(
                    world,
                    state
            );

            state.generated = true;
            state.markDirty();
        });
    }

    private static void generate(
            ServerWorld world,
            StructureState state
    ) {

        Identifier id =
                new Identifier(
                        "hellmod",
                        "helltree"
                );

        Optional<StructureTemplate> optional =
                world.getStructureTemplateManager()
                        .getTemplate(id);

        if (optional.isEmpty()) {

            System.out.println(
                    "Structure not found!"
            );

            return;
        }

        StructureTemplate template =
                optional.get();

        BlockPos spawnPos =
                world.getSpawnPos();

        Pair<BlockPos, ?> result =
                world.locateBiome(

                        biome ->
                                biome.matchesKey(BiomeKeys.PLAINS)
                                        || biome.matchesKey(BiomeKeys.MEADOW)
                                        || biome.matchesKey(BiomeKeys.SUNFLOWER_PLAINS),

                        spawnPos,

                        1500,

                        32,

                        64
                );

        BlockPos foundPos;

        if (result != null) {

            foundPos =
                    result.getFirst();

        } else {

            foundPos = spawnPos;
        }

        int centerX = foundPos.getX();
        int centerZ = foundPos.getZ();

        world.getChunk(
                centerX >> 4,
                centerZ >> 4
        );

        int centerY = world.getTopY(
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                centerX,
                centerZ
        );

        if (centerY <= world.getBottomY() + 5) {

            boolean foundGround = false;

            for (int radius = 0;
                 radius <= 32;
                 radius += 4) {

                for (int x = -radius;
                     x <= radius;
                     x += 4) {

                    for (int z = -radius;
                         z <= radius;
                         z += 4) {

                        int checkX = centerX + x;
                        int checkZ = centerZ + z;

                        world.getChunk(
                                checkX >> 4,
                                checkZ >> 4
                        );

                        int y = world.getTopY(
                                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                                checkX,
                                checkZ
                        );

                        if (y > world.getSeaLevel()) {

                            centerX = checkX;
                            centerZ = checkZ;
                            centerY = y;

                            foundGround = true;
                            break;
                        }
                    }

                    if (foundGround) {
                        break;
                    }
                }

                if (foundGround) {
                    break;
                }
            }
        }

        BlockPos structurePos =
                new BlockPos(
                        centerX,
                        centerY,
                        centerZ
                );

        clearVegetation(
                world,
                structurePos,
                template
        );

        template.place(
                world,
                structurePos,
                structurePos,
                new StructurePlacementData()
                        .setIgnoreEntities(true),
                world.random,
                2
        );

        BunkerDoor.setupDoor(
                structurePos
        );

        var size =
                template.getSize();

        HELLTREE_MIN =
                structurePos;

        HELLTREE_MAX =
                structurePos.add(
                        size.getX() - 1,
                        size.getY() - 1,
                        size.getZ() - 1
                );

        INNER_MIN =
                structurePos.add(
                        7,
                        1,
                        6
                );

        INNER_MAX =
                structurePos.add(
                        11,
                        6,
                        9
                );

        state.min = HELLTREE_MIN;
        state.max = HELLTREE_MAX;

        state.innerMin = INNER_MIN;
        state.innerMax = INNER_MAX;

        state.markDirty();

        generateNaturalSupport(
                world,
                structurePos,
                template
        );

        convertExposedDirtToGrass(
                world,
                structurePos,
                template
        );

        System.out.println(
                "HELLTREE MIN: " + HELLTREE_MIN
        );

        System.out.println(
                "HELLTREE MAX: " + HELLTREE_MAX
        );

        System.out.println(
                "INNER MIN: " + INNER_MIN
        );

        System.out.println(
                "INNER MAX: " + INNER_MAX
        );

        world.getServer()
                .getPlayerManager()
                .broadcast(
                        Text.literal(
                                "Helltree generated at: "
                                        + structurePos
                        ),
                        false
                );
    }

    public static boolean isProtected(
            BlockPos pos
    ) {

        if (
                HELLTREE_MIN == null
                        || HELLTREE_MAX == null
        ) {

            return false;
        }

        return

                pos.getX() >= HELLTREE_MIN.getX()
                        && pos.getX() <= HELLTREE_MAX.getX()

                        && pos.getZ() >= HELLTREE_MIN.getZ()
                        && pos.getZ() <= HELLTREE_MAX.getZ()

                        && pos.getY() >= HELLTREE_MIN.getY();
    }

    public static boolean isInsideInterior(
            BlockPos pos
    ) {

        if (
                INNER_MIN == null
                        || INNER_MAX == null
        ) {

            return false;
        }

        return

                pos.getX() >= INNER_MIN.getX()
                        && pos.getX() <= INNER_MAX.getX()

                        && pos.getY() >= INNER_MIN.getY()
                        && pos.getY() <= INNER_MAX.getY()

                        && pos.getZ() >= INNER_MIN.getZ()
                        && pos.getZ() <= INNER_MAX.getZ();
    }

    private static void clearVegetation(
            ServerWorld world,
            BlockPos structurePos,
            StructureTemplate template
    ) {

        var size = template.getSize();

        for (int x = -2; x <= size.getX() + 2; x++) {
            for (int z = -2; z <= size.getZ() + 2; z++) {

                for (int y = 0; y < 40; y++) {

                    BlockPos pos =
                            structurePos.add(
                                    x,
                                    y,
                                    z
                            );

                    var state =
                            world.getBlockState(pos);

                    if (

                            state.isOf(Blocks.OAK_LOG)
                                    || state.isOf(Blocks.BIRCH_LOG)
                                    || state.isOf(Blocks.JUNGLE_LOG)
                                    || state.isOf(Blocks.SPRUCE_LOG)
                                    || state.isOf(Blocks.DARK_OAK_LOG)
                                    || state.isOf(Blocks.ACACIA_LOG)
                                    || state.isOf(Blocks.MANGROVE_LOG)
                                    || state.isOf(Blocks.CHERRY_LOG)

                                    || state.isOf(Blocks.OAK_LEAVES)
                                    || state.isOf(Blocks.BIRCH_LEAVES)
                                    || state.isOf(Blocks.JUNGLE_LEAVES)
                                    || state.isOf(Blocks.SPRUCE_LEAVES)
                                    || state.isOf(Blocks.DARK_OAK_LEAVES)
                                    || state.isOf(Blocks.ACACIA_LEAVES)
                                    || state.isOf(Blocks.MANGROVE_LEAVES)
                                    || state.isOf(Blocks.CHERRY_LEAVES)

                                    || state.isOf(Blocks.GRASS_BLOCK)
                                    || state.isOf(Blocks.TALL_GRASS)
                                    || state.isOf(Blocks.FERN)
                                    || state.isOf(Blocks.LARGE_FERN)

                    ) {

                        world.breakBlock(
                                pos,
                                false
                        );
                    }
                }
            }
        }
    }

    private static void generateNaturalSupport(
            ServerWorld world,
            BlockPos structurePos,
            StructureTemplate template
    ) {

        var size = template.getSize();

        for (int x = 0; x < size.getX(); x++) {
            for (int z = 0; z < size.getZ(); z++) {

                for (int y = 0; y < 6; y++) {

                    BlockPos pos =
                            structurePos.add(
                                    x,
                                    y,
                                    z
                            );

                    if (world.getBlockState(pos).isAir()) {
                        continue;
                    }

                    BlockPos below =
                            pos.down();

                    if (!world.getBlockState(below)
                            .isAir()) {

                        continue;
                    }

                    for (int depth = 1;
                         depth < 30;
                         depth++) {

                        BlockPos supportPos =
                                pos.down(depth);

                        if (
                                isProtected(supportPos)
                        ) {

                            continue;
                        }

                        if (!world.getBlockState(supportPos)
                                .isAir()) {

                            break;
                        }

                        world.setBlockState(
                                supportPos,

                                depth > 15
                                        ? Blocks.STONE.getDefaultState()
                                        : Blocks.DIRT.getDefaultState()
                        );
                    }
                }
            }
        }
    }

    private static void convertExposedDirtToGrass(
            ServerWorld world,
            BlockPos structurePos,
            StructureTemplate template
    ) {

        var size = template.getSize();

        int radius = Math.max(
                size.getX(),
                size.getZ()
        ) + 20;

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {

                int worldX =
                        structurePos.getX() + x;

                int worldZ =
                        structurePos.getZ() + z;

                int topY = world.getTopY(
                        Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                        worldX,
                        worldZ
                );

                BlockPos pos =
                        new BlockPos(
                                worldX,
                                topY - 1,
                                worldZ
                        );

                if (
                        world.getBlockState(pos)
                                .isOf(Blocks.DIRT)
                ) {

                    BlockPos above = pos.up();

                    if (
                            world.getBlockState(above)
                                    .isAir()
                    ) {

                        world.setBlockState(
                                pos,
                                Blocks.GRASS_BLOCK
                                        .getDefaultState()
                        );
                    }
                }
            }
        }
    }

    public static class StructureState
            extends PersistentState {

        public boolean generated = false;

        public BlockPos min;
        public BlockPos max;

        public BlockPos innerMin;
        public BlockPos innerMax;

        @Override
        public NbtCompound writeNbt(
                NbtCompound nbt,
                RegistryWrapper.WrapperLookup registryLookup
        ) {

            nbt.putBoolean(
                    "generated",
                    generated
            );

            if (min != null) {

                nbt.putInt(
                        "minX",
                        min.getX()
                );

                nbt.putInt(
                        "minY",
                        min.getY()
                );

                nbt.putInt(
                        "minZ",
                        min.getZ()
                );
            }

            if (max != null) {

                nbt.putInt(
                        "maxX",
                        max.getX()
                );

                nbt.putInt(
                        "maxY",
                        max.getY()
                );

                nbt.putInt(
                        "maxZ",
                        max.getZ()
                );
            }

            if (innerMin != null) {

                nbt.putInt(
                        "innerMinX",
                        innerMin.getX()
                );

                nbt.putInt(
                        "innerMinY",
                        innerMin.getY()
                );

                nbt.putInt(
                        "innerMinZ",
                        innerMin.getZ()
                );
            }

            if (innerMax != null) {

                nbt.putInt(
                        "innerMaxX",
                        innerMax.getX()
                );

                nbt.putInt(
                        "innerMaxY",
                        innerMax.getY()
                );

                nbt.putInt(
                        "innerMaxZ",
                        innerMax.getZ()
                );
            }

            return nbt;
        }

        public static StructureState fromNbt(
                NbtCompound nbt,
                RegistryWrapper.WrapperLookup registryLookup
        ) {

            StructureState state =
                    new StructureState();

            state.generated =
                    nbt.getBoolean(
                            "generated"
                    );

            if (nbt.contains("minX")) {

                state.min =
                        new BlockPos(

                                nbt.getInt("minX"),
                                nbt.getInt("minY"),
                                nbt.getInt("minZ")
                        );
            }

            if (nbt.contains("maxX")) {

                state.max =
                        new BlockPos(

                                nbt.getInt("maxX"),
                                nbt.getInt("maxY"),
                                nbt.getInt("maxZ")
                        );
            }

            if (nbt.contains("innerMinX")) {

                state.innerMin =
                        new BlockPos(

                                nbt.getInt("innerMinX"),
                                nbt.getInt("innerMinY"),
                                nbt.getInt("innerMinZ")
                        );
            }

            if (nbt.contains("innerMaxX")) {

                state.innerMax =
                        new BlockPos(

                                nbt.getInt("innerMaxX"),
                                nbt.getInt("innerMaxY"),
                                nbt.getInt("innerMaxZ")
                        );
            }

            return state;
        }

        public static final Type<StructureState> TYPE =
                new Type<>(
                        StructureState::new,
                        StructureState::fromNbt,
                        null
                );
    }
}