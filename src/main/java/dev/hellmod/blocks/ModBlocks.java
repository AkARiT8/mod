package dev.hellmod.blocks;

import dev.hellmod.blocks.custom.StageBlock;
import dev.hellmod.blocks.entity.StageBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import dev.hellmod.HellMod;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public class ModBlocks {

    public static final Pair<Block, Item> PURE_IRON_BLOCK = registerBlock("pure_iron_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    public static final Pair<Block, Item> STAGE_BLOCK = registerBlock("stage_block", new StageBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)));

    private static Pair<Block, Item> registerBlock(String name, Block block){
        return new Pair<>(
                Registry.register(Registries.BLOCK, new Identifier(HellMod.MODID, name), block),
                Registry.register(Registries.ITEM, new Identifier(HellMod.MODID, name), new BlockItem(block, new Item .Settings()))
        );
    }

    public static void registerBlocks(){
        HellMod.LOGGER.info("Registing items");
    }

}
