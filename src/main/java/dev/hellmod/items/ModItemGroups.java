package dev.hellmod.items;

import dev.hellmod.HellMod;
import dev.hellmod.blocks.ModBlocks;
import dev.hellmod.items.custom.ModPotions;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup HELL = registerItemGroup("hell_group",
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.hellmod.hell_group"))
                    .icon(() -> new ItemStack(ModItems.PURE_IRON_INGOT))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PURE_IRON_INGOT);
                        entries.add(ModItems.PURE_GOLD_INGOT);
                        entries.add(ModItems.PURE_DIAMOND);
                        entries.add(ModItems.PURE_EMERALD);
                        entries.add(ModItems.PURE_NETHERITE_INGOT);
                        entries.add(ModItems.ENCHANTED_GOLDEN_CARROT);
                        entries.add(ModItems.SPEED_TOTEM_OF_UNDYING);
                        entries.add(ModItems.BASIC_HARDCORE_HEART);
                        entries.add(ModItems.OVERWORLD_ESSENCE);
                        entries.add(ModBlocks.PURE_IRON_BLOCK.getLeft());
                        entries.add(ModBlocks.PURE_GOLD_BLOCK.getLeft());
                        entries.add(ModBlocks.PURE_DIAMOND_BLOCK.getLeft());
                        entries.add(ModBlocks.PURE_EMERALD_BLOCK.getLeft());
                        entries.add(ModBlocks.PURE_NETHERITE_BLOCK.getLeft());
                        entries.add(ModBlocks.STAGE_BLOCK.getLeft());
                        entries.add(ModItems.BLAZE_MAIN_ROD);
                        entries.add(ModItems.MAGMA_CUBE_CORE);
                        entries.add(ModItems.NETHER_INFUSED_INGOT);
                        entries.add(ModItems.NETHER_INFUSED_HELMET);
                        entries.add(ModItems.NETHER_INFUSED_CHESTPLATE);
                        entries.add(ModItems.NETHER_INFUSED_LEGGINGS);
                        entries.add(ModItems.NETHER_INFUSED_BOOTS);
                        entries.add(ModItems.NETHER_INFUSED_SWORD);
                        entries.add(ModItems.NETHER_INFUSED_HOE);
                        entries.add(ModItems.NETHER_INFUSED_SHOVEL);
                        entries.add(ModItems.NETHER_INFUSED_PICKAXE);
                        entries.add(ModItems.NETHER_INFUSED_AXE);
                        entries.add(ModItems.NETHER_INFUSED_TEMPLATE);
                        entries.add(ModItems.GOLDEN_APPLE_RUNE);
                        entries.add(ModItems.UNCOMMON_HARDCORE_HEART);
                        entries.add(ModItems.BARRIER_TOTEM_OF_UNDYING);
                        entries.add(ModItems.NETHER_ESSENCE);
                        entries.add(ModItems.GHAST_APPENDIX);
                        entries.add(ModItems.EMPOWERED_COAL);
                        entries.add(ModBlocks.EMPOWERED_COAL_BLOCK.getLeft());
                        entries.add(ModItems.TRUE_AMETHYST_HELMET);
                        entries.add(ModItems.TRUE_AMETHYST_CHESTPLATE);
                        entries.add(ModItems.TRUE_AMETHYST_LEGGINGS);
                        entries.add(ModItems.TRUE_AMETHYST_BOOTS);
                        entries.add(ModItems.TRUE_AMETHYST_AXE);
                        entries.add(ModItems.TRUE_AMETHYST_PICKAXE);
                        entries.add(ModItems.TRUE_AMETHYST_SHOVEL);
                        entries.add(ModItems.TRUE_AMETHYST_SWORD);
                        entries.add(ModItems.TRUE_AMETHYST_HOE);
                        entries.add(ModItems.CREEPER_ESSENCE);
                        entries.add(ModItems.ZOMBIE_ESSENCE);
                        entries.add(ModItems.PHANTOM_ESSENCE);
                        entries.add(ModItems.TRUE_AMETHYST_SHARD);
                        entries.add(ModItems.TRUE_AMETHYST_INGOT);
                        entries.add(ModItems.TRUE_AMETHYST_TEMPLATE);
                        entries.add(ModItems.TRUE_AMETHYST_SHIELD);
                        entries.add(ModItems.TRUE_AMETHYST_BOW);
                        entries.add(ModItems.INVENCIBILITY_RUNE);
                        ItemStack stack = new ItemStack(Items.POTION);

                        stack.set(
                                DataComponentTypes.POTION_CONTENTS,
                                new PotionContentsComponent(
                                        Registries.POTION.getEntry(ModPotions.PANIC_POTION)
                                )
                        );

                        entries.add(stack);

                    })
                    .build()
    );

    private static ItemGroup registerItemGroup(String itemGroupid, ItemGroup itemGroup){
        return Registry.register(Registries.ITEM_GROUP, new Identifier(HellMod.MODID, itemGroupid), itemGroup);
    }

    public static void registerItemsGroups(){
        HellMod.LOGGER.info("Registing items");
    }

}
