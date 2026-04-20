package dev.hellmod.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.hellmod.HellMod;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;


public class DatagenModelProvider extends FabricModelProvider {

    public DatagenModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {


        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_IRON_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_GOLD_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_DIAMOND_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_EMERALD_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURE_NETHERITE_BLOCK.getLeft());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EMPOWERED_COAL_BLOCK.getLeft());

        TextureMap textureMap = new TextureMap()
                .put(TextureKey.UP, new Identifier(HellMod.MODID, "block/stageblock/stage_block_top"))
                .put(TextureKey.DOWN, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.NORTH, new Identifier(HellMod.MODID, "block/stageblock/stage_block_front"))
                .put(TextureKey.SOUTH, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.EAST, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.WEST, new Identifier(HellMod.MODID, "block/stageblock/stage_block_side"))
                .put(TextureKey.PARTICLE, new Identifier(HellMod.MODID, "block/stageblock/stage_block_top"));

        Identifier modelId = Models.CUBE.upload(
                new Identifier(HellMod.MODID, "stage_block"),
                textureMap,
                blockStateModelGenerator.modelCollector
        );

        blockStateModelGenerator.blockStateCollector.accept(
                BlockStateModelGenerator.createSingletonBlockState(ModBlocks.STAGE_BLOCK.getLeft(), modelId)
        );
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.STAGE_BLOCK.getLeft(), modelId);

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
