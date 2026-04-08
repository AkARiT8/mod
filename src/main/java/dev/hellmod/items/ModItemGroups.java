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
                        entries.add(ModBlocks.PURE_IRON_BLOCK.getLeft());
                        entries.add(ModBlocks.STAGE_BLOCK.getLeft());

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
