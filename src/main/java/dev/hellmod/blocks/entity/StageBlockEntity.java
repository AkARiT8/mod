package dev.hellmod.blocks.entity;

import dev.hellmod.blocks.custom.StageData;
import dev.hellmod.items.ModItems;
import dev.hellmod.registry.ModBlockEntities;
import dev.hellmod.screen.StageScreenHandler;
import dev.hellmod.stage.modifier.StageModifierApplier;
import dev.hellmod.stage.modifier.StageModifierManager;
import dev.hellmod.stage.recipe.StageRecipeManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class StageBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, Inventory {


    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(41, ItemStack.EMPTY);

    private final PropertyDelegate propertyDelegate = new ArrayPropertyDelegate(2);

    public StageBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STAGE_BLOCK_ENTITY, pos, state);
    }

    public void updateStage() {
        if (world instanceof ServerWorld serverWorld) {

            StageData data = StageData.get(serverWorld);

            int stage = data.getStage();
            int progress = data.getProgress();

            propertyDelegate.set(0, stage);
            propertyDelegate.set(1, progress);
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, StageBlockEntity blockEntity) {
        if (!world.isClient) {
            blockEntity.updateStage();
        }
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.hellmod.stage_block");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {

        updateStage();

        return new StageScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public boolean canAdd() {
        if (world == null || world.isClient) return false;

        int stage = StageData.get((ServerWorld) world).getStage();

        var recipe = StageRecipeManager.get(stage);
        if (recipe == null) return false;

        var inputs = recipe.getInputs();

        for (int i = 0; i < inputs.size(); i++) {
            if (!items.get(i).isOf(inputs.get(i))) {
                return false;
            }
        }

        return true;
    }

    public void addResources() {
        if (world instanceof ServerWorld serverWorld) {
            StageData data = StageData.get(serverWorld);
            items.get(0).decrement(1);
            items.get(1).decrement(1);
            items.get(2).decrement(1);


            data.addStageStep();

            if (data.getStageStep() == data.getStageEnd()){
                data.setStageStepTo0();
                advanceStage();
            }

            world.playSound(
                    null,
                    pos,
                    net.minecraft.sound.SoundEvents.BLOCK_ANVIL_USE,
                    net.minecraft.sound.SoundCategory.BLOCKS,
                    1.0f,
                    1.0f
            );

            updateStage();
            markDirty();
        }
    }

    public void advanceStage() {
        if (world instanceof ServerWorld serverWorld) {

            StageData data = StageData.get(serverWorld);
            int newStage = data.getStage() + 1;

            StageSync.syncStageToAllDimensions(serverWorld, newStage);

            if (newStage == 1) {
                items.set(3, new ItemStack(ModItems.SPEED_TOTEM_OF_UNDYING));
                markDirty();
            }

            world.playSound(
                    null,
                    pos,
                    SoundEvents.ENTITY_WITHER_SPAWN,
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f
            );

            serverWorld.setTimeOfDay(14000);
        }
    }
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }


    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = items.get(slot).split(amount);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack stack = items.get(slot);
        items.set(slot, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        items.set(slot, stack);
        markDirty();
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    public class StageSync {

        public static void syncStageToAllDimensions(ServerWorld sourceWorld, int stage) {

            var server = sourceWorld.getServer();

            for (ServerWorld world : server.getWorlds()) {

                StageData data = StageData.get(world);

                data.setStage(stage);
                data.setStageStepTo0();

                System.out.println("Sync stage " + stage + " a " + world.getRegistryKey().getValue());
            }
        }
    }
}