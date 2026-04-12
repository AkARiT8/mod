package dev.hellmod.stage.recipe;

import dev.hellmod.stage.recipe.StageRecipeManager;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class StageRecipeReloadListener implements SimpleSynchronousResourceReloadListener {

    @Override
    public Identifier getFabricId() {
        return new Identifier("hellmod", "stage_recipes");
    }

    @Override
    public void reload(ResourceManager manager) {
        StageRecipeManager.load(manager);
    }
}