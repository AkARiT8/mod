package dev.hellmod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.hellmod.screen.StageScreenHandler;
import dev.hellmod.stage.recipe.StageRecipeManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;


public class StageScreen extends HandledScreen<StageScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("hellmod", "textures/gui/stage_block_gui.png");

    public StageScreen(StageScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

    }


    @Override
    protected void init() {
        super.init();

        addDrawableChild(ButtonWidget.builder(Text.literal("ADD"), button -> {
            client.interactionManager.clickButton(handler.syncId, 0);
        }).dimensions(x + 111, y + 55, 54, 19).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        this.renderBackground(context,mouseX,mouseY,delta);

        super.render(context, mouseX, mouseY, delta);

        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

    }
    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {

        int stage = handler.getStage();
        int centerX = 46;

        String title = "Stage";
        int titleX = centerX - textRenderer.getWidth(title) / 2;
        context.drawText(textRenderer, title, titleX - 1, 15, 0xFFFFFF, false);

        var items = handler.getRequiredItems();

        for (int i = 0; i < items.size(); i++) {
            if (!handler.getSlot(i).hasStack()) {

                int[] xs = {88, 88, 88};
                int[] ys = {11, 32, 53};

                drawGhostItem(context, items.get(i), xs[i], ys[i]);
            }
        }

        String stageText = String.valueOf(stage);
        float scale = 2.0f;

        int textWidth = textRenderer.getWidth(stageText);
        int scaledWidth = (int)(textWidth * scale);
        int stageX = centerX - scaledWidth / 2;

        context.getMatrices().push();
        context.getMatrices().scale(scale, scale, 1.0f);

        context.drawText(
                textRenderer,
                stageText,
                (int)(stageX / scale),
                (int)(30 / scale),
                0x00FFFF,
                false
        );

        context.getMatrices().pop();

        int progress = handler.getProgress();
        String progressText = progress + "%";

        int progressX = centerX - textRenderer.getWidth(progressText) / 2;
        context.drawText(textRenderer, progressText, progressX, 60, 0xAAAAAA, false);
    }

    private void drawGhostItem(DrawContext context, Item item, int x, int y) {

        float scale = 0.75f;

        context.getMatrices().push();

        context.getMatrices().translate(
                x + (16 * (1 - scale) / 2),
                y + (16 * (1 - scale) / 2),
                0
        );

        context.getMatrices().scale(scale, scale, 1);

        context.drawItem(new ItemStack(item), 0, 0);

        context.getMatrices().pop();
    }

}
