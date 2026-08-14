package dev.hellmod.client;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModTooltipHandler {

    public static void register() {

        ItemTooltipCallback.EVENT.register(
                (stack, tooltipContext, tooltipType, lines) -> {

                    addCustomTooltip(
                            stack,
                            lines
                    );
                }
        );
    }

    private static void addCustomTooltip(
            ItemStack stack,
            List<Text> tooltip
    ) {

        Identifier id;

        PotionContentsComponent potionContents =
                stack.get(DataComponentTypes.POTION_CONTENTS);

        if (potionContents != null && potionContents.potion().isPresent()) {

            RegistryEntry<Potion> potionEntry =
                    potionContents.potion().get();

            id = Registries.POTION.getId(
                    potionEntry.value()
            );

        } else {

            id = Registries.ITEM.getId(
                    stack.getItem()
            );
        }

        String baseKey =
                "tooltip."
                        + id.getNamespace()
                        + "."
                        + id.getPath();

        List<Text> customLines =
                new ArrayList<>();

        for (int i = 1; i <= 10; i++) {

            String lineKey =
                    baseKey + ".line" + i;

            MutableText text =
                    Text.translatable(lineKey);

            if (text.getString().equals(lineKey)) {
                continue;
            }

            Formatting color =
                    Formatting.GRAY;

            if (i == 1) {
                color = Formatting.BLUE;
            }

            if (i == 2) {
                color = Formatting.DARK_RED;
            }

            if (i == 3) {
                color = Formatting.LIGHT_PURPLE;
            }

            if (i == 4) {
                color = Formatting.GREEN;
            }

            if (i == 7) {
                color = Formatting.YELLOW;
            }

            customLines.add(
                    text.formatted(color)
            );
        }

        if (!customLines.isEmpty()) {

            tooltip.addAll(
                    1,
                    customLines
            );
        }
    }
}