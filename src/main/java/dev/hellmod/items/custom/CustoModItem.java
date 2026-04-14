package dev.hellmod.items.custom;

import net.minecraft.client.item.TooltipType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class CustoModItem extends Item {

    public CustoModItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        String key = this.getTranslationKey().replace("item.hellmod.", "");

        String baseKey = "tooltip.hellmod." + key;

        for (int i = 1; i <= 10; i++) {

            String lineKey = baseKey + ".line" + i;

            MutableText text = Text.translatable(lineKey);

            if (!text.getString().equals(lineKey)) {

                Formatting color = Formatting.GRAY;

                if (i == 2) color = Formatting.GREEN;
                if (i == 3) color = Formatting.BLUE;
                if (i == 7) color = Formatting.YELLOW;

                tooltip.add(text.formatted(color));
            }
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}