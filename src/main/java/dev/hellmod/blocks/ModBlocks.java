package dev.hellmod.blocks;

import dev.hellmod.blocks.custom.CustomBlockItem;
import dev.hellmod.blocks.custom.StageBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import dev.hellmod.HellMod;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public class ModBlocks {

    public static final Pair<Block, Item> PURE_IRON_BLOCK = registerBlock("pure_iron_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).requiresTool()));
    public static final Pair<Block, Item> PURE_GOLD_BLOCK = registerBlock("pure_gold_block", new Block(AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK).requiresTool()));
    public static final Pair<Block, Item> PURE_DIAMOND_BLOCK = registerBlock("pure_diamond_block", new Block(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).requiresTool()));
    public static final Pair<Block, Item> PURE_EMERALD_BLOCK = registerBlock("pure_emerald_block", new Block(AbstractBlock.Settings.copy(Blocks.EMERALD_BLOCK).requiresTool()));
    public static final Pair<Block, Item> PURE_NETHERITE_BLOCK = registerBlock("pure_netherite_block", new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK).requiresTool()));
    public static final Pair<Block, Item> STAGE_BLOCK = registerBlock("stage_block", new StageBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)));

    private static Pair<Block, Item> registerBlock(String name, Block block){
        return new Pair<>(
                Registry.register(Registries.BLOCK, new Identifier(HellMod.MODID, name), block),
                (Item) Registry.register(
                        Registries.ITEM,
                        new Identifier(HellMod.MODID, name),
                        new CustomBlockItem(block, new Item.Settings())
                )
        );
    }

    public static void registerBlocks(){
        HellMod.LOGGER.info("Registing items");
    }

}
