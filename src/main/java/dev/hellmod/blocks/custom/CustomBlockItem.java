package dev.hellmod.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class CustomBlockItem extends BlockItem {

    public CustomBlockItem(Block block, Settings settings) {
        super(block, settings);


    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        String key = this.getTranslationKey()
                .replace("block.hellmod.", "")
                .replace("item.hellmod.", "");

        String baseKey = "tooltip.hellmod." + key;

        MutableText single = Text.translatable(baseKey);

        if (!single.getString().equals(baseKey)) {
            tooltip.add(single.formatted(Formatting.GRAY));
        } else {
            for (int i = 1; i <= 5; i++) {
                String lineKey = baseKey + ".line" + i;

                MutableText text = Text.translatable(lineKey);

                if (!text.getString().equals(lineKey)) {
                    tooltip.add(text.formatted(Formatting.GRAY));
                }
            }
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}