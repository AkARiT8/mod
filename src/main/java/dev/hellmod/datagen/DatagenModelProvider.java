package dev.hellmod.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.hellmod.HellMod;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;


public class DatagenModelProvider extends FabricModelProvider {

    public DatagenModelProvider(FabricDataOutput output) {
        super(output);
    }

    private Identifier id(String path) {
        return new Identifier(HellMod.MODID, path);
    }

    private void registerSlab(
            BlockStateModelGenerator generator,
            Block slab,
            String name,
            Identifier texture,
            Identifier fullBlockModel
    ) {

        TextureMap textures = TextureMap.all(texture);

        Identifier bottomModel = Models.SLAB.upload(
                id("block/" + name),
                textures,
                generator.modelCollector
        );

        Identifier topModel = Models.SLAB_TOP.upload(
                id("block/" + name + "_top"),
                textures,
                generator.modelCollector
        );

        generator.blockStateCollector.accept(
                new BlockStateSupplier() {

                    @Override
                    public Block getBlock() {
                        return slab;
                    }

                    @Override
                    public com.google.gson.JsonElement get() {

                        JsonObject root = new JsonObject();
                        JsonObject variants = new JsonObject();

                        addVariant(variants, "type=bottom,waterlogged=false", bottomModel);
                        addVariant(variants, "type=bottom,waterlogged=true", bottomModel);
                        addVariant(variants, "type=top,waterlogged=false", topModel);
                        addVariant(variants, "type=top,waterlogged=true", topModel);
                        addVariant(variants, "type=double,waterlogged=false", fullBlockModel);
                        addVariant(variants, "type=double,waterlogged=true", fullBlockModel);
                        root.add("variants", variants);
                        return root;
                    }
                }
        );

        generator.registerParentedItemModel(
                slab,
                bottomModel
        );
    }

    private void addVariant(
            JsonObject variants,
            String state,
            Identifier model
    ) {

        JsonObject variant = new JsonObject();

        variant.addProperty("model", model.toString());
        variants.add(state, variant);
    }

    private void registerStairs(
            BlockStateModelGenerator generator,
            Block block,
            String name,
            Identifier texture
    ) {
        TextureMap textures = TextureMap.all(texture);

        Identifier normal = Models.STAIRS.upload(
                id("block/" + name),
                textures,
                generator.modelCollector
        );

        Identifier inner = Models.INNER_STAIRS.upload(
                id("block/" + name + "_inner"),
                textures,
                generator.modelCollector
        );

        Identifier outer = Models.OUTER_STAIRS.upload(
                id("block/" + name + "_outer"),
                textures,
                generator.modelCollector
        );

        JsonObject variants = new JsonObject();

        String[] facings = {"east", "south", "west", "north"};
        int[] rotations = {0, 90, 180, 270};

        String[] shapes = {
                "straight",
                "inner_right",
                "inner_left",
                "outer_right",
                "outer_left"
        };

        for (int i = 0; i < facings.length; i++) {

            String facing = facings[i];
            int y = rotations[i];

            for (String shape : shapes) {

                Identifier model = shape.equals("straight")
                        ? normal
                        : shape.startsWith("inner")
                        ? inner
                        : outer;

                int shapeY = shape.endsWith("left")
                        ? (y + 270) % 360
                        : y;

                addStairVariant(
                        variants,
                        "facing=" + facing + ",half=bottom,shape=" + shape,
                        model,
                        0,
                        shapeY
                );

                addStairVariant(
                        variants,
                        "facing=" + facing + ",half=top,shape=" + shape,
                        model,
                        180,
                        shapeY
                );
            }
        }

        JsonObject root = new JsonObject();
        root.add("variants", variants);

        generator.blockStateCollector.accept(
                new BlockStateSupplier() {
                    @Override
                    public Block getBlock() {
                        return block;
                    }

                    @Override
                    public com.google.gson.JsonElement get() {
                        return root;
                    }
                }
        );

        generator.registerParentedItemModel(block, normal);
    }

    private void addStairVariant(
            JsonObject variants,
            String state,
            Identifier model,
            int x,
            int y
    ) {
        JsonObject variant = new JsonObject();

        variant.addProperty("model", model.toString());
        variant.addProperty("uvlock", true);

        if (x != 0) variant.addProperty("x", x);
        if (y != 0) variant.addProperty("y", y);

        variants.add(state, variant);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {


        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_IRON_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_GOLD_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_DIAMOND_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_EMERALD_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_NETHERITE_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EMPOWERED_COAL_BLOCK.getLeft());

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DARK_OBSIDIAN.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_DARK_OBSIDIAN.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRYING_DARK_OBSIDIAN.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DARK_OBSIDIAN_NODE.getLeft());

        TextureMap textureMapStage = new TextureMap()
                .put(TextureKey.UP, new Identifier(HellMod.MODID, "block/stageblock/stage_block_top"))
                .put(TextureKey.DOWN, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.NORTH, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.SOUTH, new Identifier(HellMod.MODID, "block/stageblock/stage_block_front"))
                .put(TextureKey.EAST, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.WEST, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.PARTICLE, new Identifier(HellMod.MODID, "block/stageblock/stage_block_top"));

        Identifier modelIdStage = Models.CUBE.upload(
                new Identifier(HellMod.MODID, "stage_block"),
                textureMapStage,
                blockStateModelGenerator.modelCollector
        );

        blockStateModelGenerator.blockStateCollector.accept(
                BlockStateModelGenerator.createSingletonBlockState(ModBlocks.STAGE_BLOCK.getLeft(), modelIdStage)
        );
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.STAGE_BLOCK.getLeft(), modelIdStage);

        TextureMap textureMapPilar = new TextureMap()
                .put(
                        TextureKey.END,
                        id("block/dark_obsidian_pilar/dark_obsidian_pilar_topbot")
                )
                .put(
                        TextureKey.SIDE,
                        id("block/dark_obsidian_pilar/dark_obsidian_pilar_side")
                );

        Identifier modelIdPilar = Models.CUBE_COLUMN.upload(
                id("dark_obsidian_pilar"),
                textureMapPilar,
                blockStateModelGenerator.modelCollector
        );

        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(
                        ModBlocks.DARK_OBSIDIAN_PILAR.getLeft()
                ).coordinate(
                        BlockStateVariantMap.create(
                                        PillarBlock.AXIS
                                )
                                .register(
                                        Direction.Axis.Y,
                                        BlockStateVariant.create()
                                                .put(
                                                        VariantSettings.MODEL,
                                                        modelIdPilar
                                                )
                                )

                                .register(
                                        Direction.Axis.X,
                                        BlockStateVariant.create()
                                                .put(
                                                        VariantSettings.MODEL,
                                                        modelIdPilar
                                                )
                                                .put(
                                                        VariantSettings.X,
                                                        VariantSettings.Rotation.R90
                                                )
                                                .put(
                                                        VariantSettings.Y,
                                                        VariantSettings.Rotation.R90
                                                )
                                )

                                .register(
                                        Direction.Axis.Z,
                                        BlockStateVariant.create()
                                                .put(
                                                        VariantSettings.MODEL,
                                                        modelIdPilar
                                                )
                                                .put(
                                                        VariantSettings.X,
                                                        VariantSettings.Rotation.R90
                                                )
                                )
                )
        );

        blockStateModelGenerator.registerParentedItemModel(
                ModBlocks.DARK_OBSIDIAN_PILAR.getLeft(),
                modelIdPilar
        );

        registerSlab(
                blockStateModelGenerator,
                ModBlocks.DARK_OBSIDIAN_SLAB.getLeft(),
                "dark_obsidian_slab",
                id("block/dark_obsidian"),
                id("block/dark_obsidian")
        );

        registerStairs(
                blockStateModelGenerator,
                ModBlocks.DARK_OBSIDIAN_STAIRS.getLeft(),
                "dark_obsidian_stairs",
                id("block/dark_obsidian")
        );



    }



    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PURE_IRON_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PURE_GOLD_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PURE_DIAMOND,Models.GENERATED);
        itemModelGenerator.register(ModItems.PURE_EMERALD,Models.GENERATED);
        itemModelGenerator.register(ModItems.PURE_NETHERITE_INGOT,Models.GENERATED);
        itemModelGenerator.register(ModItems.ENCHANTED_GOLDEN_CARROT,Models.GENERATED);
        itemModelGenerator.register(ModItems.SPEED_TOTEM_OF_UNDYING,Models.GENERATED);
        itemModelGenerator.register(ModItems.BASIC_HARDCORE_HEART,Models.GENERATED);
        itemModelGenerator.register(ModItems.OVERWORLD_ESSENCE,Models.GENERATED);
        itemModelGenerator.register(ModItems.BLAZE_MAIN_ROD,Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGMA_CUBE_CORE,Models.GENERATED);

        itemModelGenerator.register(ModItems.NETHER_INFUSED_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHER_INFUSED_TEMPLATE, Models.GENERATED);

        itemModelGenerator.registerArmor((ArmorItem) ModItems.NETHER_INFUSED_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.NETHER_INFUSED_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.NETHER_INFUSED_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.NETHER_INFUSED_BOOTS);

        itemModelGenerator.registerArmor((ArmorItem) ModItems.TRUE_AMETHYST_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.TRUE_AMETHYST_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.TRUE_AMETHYST_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.TRUE_AMETHYST_BOOTS);

        itemModelGenerator.register(ModItems.NETHER_INFUSED_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHER_INFUSED_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHER_INFUSED_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHER_INFUSED_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHER_INFUSED_HOE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.TRUE_AMETHYST_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TRUE_AMETHYST_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TRUE_AMETHYST_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TRUE_AMETHYST_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TRUE_AMETHYST_HOE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.GOLDEN_APPLE_RUNE,Models.GENERATED);
        itemModelGenerator.register(ModItems.BARRIER_TOTEM_OF_UNDYING,Models.GENERATED);
        itemModelGenerator.register(ModItems.UNCOMMON_HARDCORE_HEART,Models.GENERATED);
        itemModelGenerator.register(ModItems.GHAST_APPENDIX,Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHER_ESSENCE,Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPOWERED_COAL,Models.GENERATED);

        itemModelGenerator.register(ModItems.CREEPER_ESSENCE,Models.GENERATED);
        itemModelGenerator.register(ModItems.ZOMBIE_ESSENCE,Models.GENERATED);
        itemModelGenerator.register(ModItems.PHANTOM_ESSENCE,Models.GENERATED);

        itemModelGenerator.register(ModItems.TRUE_AMETHYST_SHARD,Models.GENERATED);
        itemModelGenerator.register(ModItems.TRUE_AMETHYST_INGOT,Models.GENERATED);
        itemModelGenerator.register(ModItems.TRUE_AMETHYST_TEMPLATE,Models.GENERATED);

        itemModelGenerator.register(ModItems.INVENCIBILITY_RUNE,Models.GENERATED);
        itemModelGenerator.register(ModItems.PANIC_BALL,Models.GENERATED);
        itemModelGenerator.register(ModItems.PANIC_TOTEM_OF_UNDYING,Models.GENERATED);
        itemModelGenerator.register(ModItems.RARE_HARDCORE_HEART,Models.GENERATED);



        Identifier id = new Identifier(HellMod.MODID, "item/true_amethyst_shield");

        JsonObject json = new JsonObject();
        json.addProperty("parent", "item/shield");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", "hellmod:item/true_amethyst_shield");
        json.add("textures", textures);

        JsonArray overrides = new JsonArray();

        JsonObject override = new JsonObject();

        JsonObject predicate = new JsonObject();
        predicate.addProperty("blocking", 1.0f);
        override.add("predicate", predicate);

        override.addProperty("model", "hellmod:item/true_amethyst_shield_blocking");

        overrides.add(override);
        json.add("overrides", overrides);

        itemModelGenerator.writer.accept(id, () -> json);


        Identifier blockingId = new Identifier(HellMod.MODID, "item/true_amethyst_shield_blocking");

        JsonObject blockingJson = new JsonObject();
        blockingJson.addProperty("parent", "item/shield_blocking");

        JsonObject textures2 = new JsonObject();
        textures2.addProperty("particle", "hellmod:item/true_amethyst_shield");

        blockingJson.add("textures", textures2);

        itemModelGenerator.writer.accept(blockingId, () -> blockingJson);

    }

}

