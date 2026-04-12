package dev.hellmod.stage.recipe;

import net.minecraft.item.Item;
import java.util.List;

public class StageRecipe {

    private final List<Item> inputs;

    public StageRecipe(List<Item> inputs) {
        this.inputs = inputs;
    }

    public List<Item> getInputs() {
        return inputs;
    }
}