package dev.hellmod.blocks.custom;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

public class StageData extends PersistentState {

    private int stage = 0;
    private final int stageEnd = 10;
    private int stageStep = 0;

    public StageData() {}

    public StageData(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        this.stage = nbt.getInt("stage");
        this.stageStep = nbt.getInt("stageStep");
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
        markDirty();
    }

    public int getStageEnd() {
        return stageEnd;
    }

    public int getStageStep() {
        return stageStep;
    }

    public void addStageStep() {
        this.stageStep++;
        markDirty();
    }
    public void setStageStepTo0(){
        this.stageStep = 0;
        markDirty();
    }
    public int getProgress(){
        return (int)((this.stageStep / (float)this.stageEnd) * 100);
    }


    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        nbt.putInt("stage", stage);
        nbt.putInt("stageStep", stageStep);
        return nbt;
    }

    public static final Type<StageData> TYPE = new Type<>(
            StageData::new,
            StageData::new,
            null
    );

    public static StageData get(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(TYPE, "hellmod_stage");
    }
}