package dev.hellmod.mixin;

import dev.hellmod.util.VariantHolder;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.CreeperEntity;

import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class CreeperVariantMixin implements VariantHolder {

    @Unique
    private static final TrackedData<String> VARIANT =
            DataTracker.registerData(CreeperEntity.class, TrackedDataHandlerRegistry.STRING);

    @Unique
    private static final TrackedData<Float> EXPLOSION_POWER =
            DataTracker.registerData(CreeperEntity.class, TrackedDataHandlerRegistry.FLOAT);

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(VARIANT, "");
        builder.add(EXPLOSION_POWER, 3.0f);
        builder.add(FUSE_TIME, 30);
    }

    @Override
    public void hellmod$setVariant(String variant) {
        ((CreeperEntity)(Object)this).getDataTracker().set(VARIANT, variant);
    }

    @Override
    public String hellmod$getVariant() {
        return ((CreeperEntity)(Object)this).getDataTracker().get(VARIANT);
    }

    @Override
    public void hellmod$setExplosionPower(float power) {
        ((CreeperEntity)(Object)this).getDataTracker().set(EXPLOSION_POWER, power);
    }

    @Override
    public float hellmod$getExplosionPower() {
        return ((CreeperEntity)(Object)this).getDataTracker().get(EXPLOSION_POWER);
    }
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putString("HellmodVariant", hellmod$getVariant());
        nbt.putFloat("HellmodPower", hellmod$getExplosionPower());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("HellmodVariant")) {
            hellmod$setVariant(nbt.getString("HellmodVariant"));
        }
        if (nbt.contains("HellmodPower")) {
            hellmod$setExplosionPower(nbt.getFloat("HellmodPower"));
        }
    }
    @Unique
    private static final TrackedData<Integer> FUSE_TIME =
            DataTracker.registerData(CreeperEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    public void hellmod$setFuseTime(int fuse) {
        ((CreeperEntity)(Object)this).getDataTracker().set(FUSE_TIME, fuse);
    }

    @Override
    public int hellmod$getFuseTime() {
        return ((CreeperEntity)(Object)this).getDataTracker().get(FUSE_TIME);
    }

}