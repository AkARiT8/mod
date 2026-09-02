package dev.hellmod.blocks.entity;

import dev.hellmod.structures.doomCylinder.DoomCylinderController;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;

public class CustomButtonBlockEntity extends BlockEntity {

    private DoomCylinderController.ButtonType buttonType =
            DoomCylinderController.ButtonType.ENTRY;

    public CustomButtonBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CUSTOM_BUTTON, pos, state);
    }

    public DoomCylinderController.ButtonType getButtonType() {
        return buttonType;
    }

    public void setButtonType(DoomCylinderController.ButtonType buttonType) {
        this.buttonType = buttonType;
        markDirty();
    }

    @Override
    protected void writeNbt(
            NbtCompound nbt,
            RegistryWrapper.WrapperLookup registries
    ) {
        super.writeNbt(nbt, registries);

        nbt.putString(
                "ButtonType",
                buttonType.name()
        );
    }

    @Override
    protected void readNbt(
            NbtCompound nbt,
            RegistryWrapper.WrapperLookup registries
    ) {
        super.readNbt(nbt, registries);

        if (nbt.contains("ButtonType")) {
            buttonType = DoomCylinderController.ButtonType.valueOf(
                    nbt.getString("ButtonType")
            );
        }
    }
}