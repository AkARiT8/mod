package dev.hellmod.items;

import dev.hellmod.HellMod;
import dev.hellmod.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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
