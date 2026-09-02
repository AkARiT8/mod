package dev.hellmod.blocks;

import dev.hellmod.blocks.custom.CustomButton;
import dev.hellmod.blocks.custom.DarkObsidianPilar;
import dev.hellmod.blocks.custom.StageBlock;
import net.minecraft.block.*;
import dev.hellmod.HellMod;
import net.minecraft.item.BlockItem;
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
    public static final Pair<Block, Item> EMPOWERED_COAL_BLOCK = registerBlock("empowered_coal_block", new Block(AbstractBlock.Settings.copy(Blocks.COAL_BLOCK).requiresTool()));
    public static final Pair<Block, Item> DARK_OBSIDIAN = registerBlock("dark_obsidian", new Block(AbstractBlock.Settings.copy(Blocks.BEDROCK).requiresTool()));
    public static final Pair<Block, Item> CRYING_DARK_OBSIDIAN = registerBlock("crying_dark_obsidian", new Block(AbstractBlock.Settings.copy(Blocks.BEDROCK).requiresTool()));
    public static final Pair<Block, Item> CRACKED_DARK_OBSIDIAN = registerBlock("cracked_dark_obsidian", new Block(AbstractBlock.Settings.copy(Blocks.BEDROCK).requiresTool()));
    public static final Pair<Block, Item> DARK_OBSIDIAN_NODE = registerBlock("dark_obsidian_node", new Block(AbstractBlock.Settings.copy(Blocks.BEDROCK).luminance(state -> 12).requiresTool()));

    public static final Pair<Block, Item> DARK_OBSIDIAN_PILAR =
            registerBlock(
                    "dark_obsidian_pilar",
                    new DarkObsidianPilar(
                            AbstractBlock.Settings.copy(Blocks.BEDROCK)
                    )
            );

    public static final Pair<Block, Item> DARK_OBSIDIAN_SLAB =
            registerBlock(
                    "dark_obsidian_slab",
                    new SlabBlock(
                            AbstractBlock.Settings.copy(ModBlocks.DARK_OBSIDIAN.getLeft())
                    )
            );

    public static final Pair<Block, Item> DARK_OBSIDIAN_STAIRS =
            registerBlock(
                    "dark_obsidian_stairs",
                    new StairsBlock(
                            ModBlocks.DARK_OBSIDIAN.getLeft().getDefaultState(),
                            AbstractBlock.Settings.copy(
                                    ModBlocks.DARK_OBSIDIAN.getLeft()
                            )
                    )
            );

    public static final Pair<Block, Item> CUSTOM_BUTTON =
            registerBlock(
                    "custom_button",
                    new CustomButton(
                            AbstractBlock.Settings.copy(Blocks.STONE)
                                    .nonOpaque()
                    )
            );

    private static Pair<Block, Item> registerBlock(String name, Block block){
        return new Pair<>(
                Registry.register(Registries.BLOCK, new Identifier(HellMod.MODID, name), block),
                (Item) Registry.register(
                        Registries.ITEM,
                        new Identifier(HellMod.MODID, name),
                        new BlockItem(block, new Item.Settings())
                )
        );
    }

    public static void registerBlocks(){
        HellMod.LOGGER.info("Registing items");
    }

}
