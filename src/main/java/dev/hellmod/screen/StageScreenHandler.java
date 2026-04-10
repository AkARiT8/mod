package dev.hellmod.screen;

import dev.hellmod.blocks.custom.StageData;
import dev.hellmod.blocks.entity.StageBlockEntity;
import dev.hellmod.custom.OutputSlot;
import dev.hellmod.registry.ModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class StageScreenHandler extends ScreenHandler {

    private final StageBlockEntity blockEntity;
    private final PropertyDelegate propertyDelegate;

    public StageScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ModScreenHandlers.STAGE_SCREEN_HANDLER, syncId);

        this.blockEntity = null;
        this.propertyDelegate = new ArrayPropertyDelegate(2);
        this.addProperties(this.propertyDelegate);

        Inventory inventory = new SimpleInventory(4);

        this.addSlot(new Slot(inventory, 0, 88, 11));
        this.addSlot(new Slot(inventory, 1, 88, 32));
        this.addSlot(new Slot(inventory, 2, 88, 53));
        this.addSlot(new OutputSlot(inventory, 3, 130, 33));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    public StageScreenHandler(int syncId, PlayerInventory playerInventory,
                              StageBlockEntity blockEntity,
                              PropertyDelegate propertyDelegate) {

        super(ModScreenHandlers.STAGE_SCREEN_HANDLER, syncId);

        this.blockEntity = blockEntity;

        this.propertyDelegate = propertyDelegate;
        this.addProperties(this.propertyDelegate);

        Inventory inventory = blockEntity;

        this.addSlot(new Slot(inventory, 0, 88, 11));
        this.addSlot(new Slot(inventory, 1, 88, 32));
        this.addSlot(new Slot(inventory, 2, 88, 53));
        this.addSlot(new OutputSlot(inventory, 3, 130, 33));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    public int getStage() {
        return this.propertyDelegate.get(0);
    }
    public int getProgress() {
        return this.propertyDelegate.get(1);
    }
    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (index < 4) {
                if (!this.insertItem(originalStack, 4, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.insertItem(originalStack, 0, 3, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        int startX = 8;
        int startY = 84;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(
                        playerInventory,
                        j + i * 9 + 9,
                        startX + j * 18,
                        startY + i * 18
                ));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        int startX = 8;
        int startY = 142;

        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(
                    playerInventory,
                    i,
                    startX + i * 18,
                    startY
            ));
        }
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id == 0 && blockEntity != null && !player.getWorld().isClient) {

            if (blockEntity.canAdd()) {
                blockEntity.addResources();
            } else {

                player.sendMessage(
                        Text.literal("Recursos incorrectos").formatted(Formatting.RED),
                        true
                );
            }


            return true;
        }
        return super.onButtonClick(player, id);
    }
    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);

        if (blockEntity != null && !player.getWorld().isClient) {
            for (int i = 0; i < 4; i++) {
                ItemStack stack = blockEntity.removeStack(i);
                if (!stack.isEmpty()) {
                    player.giveItemStack(stack);
                }
            }
        }
    }
}