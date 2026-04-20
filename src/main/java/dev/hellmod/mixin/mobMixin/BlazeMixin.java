package dev.hellmod.mixin.mobMixin;

import dev.hellmod.access.BlazeAccessor;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.BlazeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlazeEntity.class)
public abstract class BlazeMixin implements BlazeAccessor {

    @Unique
    private static final TrackedData<Float> BLAZE_DAMAGE =
            DataTracker.registerData(BlazeEntity.class, TrackedDataHandlerRegistry.FLOAT);

    @Unique
    private static final TrackedData<Integer> BLAZE_SHOTS =
            DataTracker.registerData(BlazeEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Inject(method = "initDataTracker", at = @At("RETURN"))
    private void init(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(BLAZE_DAMAGE, 1.0f);
        builder.add(BLAZE_SHOTS, 1);
    }

    @Override
    public void hellmod$setBlazeDamage(float damage) {
        ((BlazeEntity)(Object)this).getDataTracker().set(BLAZE_DAMAGE, damage);
    }

    @Override
    public float hellmod$getBlazeDamage() {
        return ((BlazeEntity)(Object)this).getDataTracker().get(BLAZE_DAMAGE);
    }

    @Override
    public void hellmod$setBlazeShots(int shots) {
        ((BlazeEntity)(Object)this).getDataTracker().set(BLAZE_SHOTS, shots);
    }

    @Override
    public int hellmod$getBlazeShots() {
        return ((BlazeEntity)(Object)this).getDataTracker().get(BLAZE_SHOTS);
    }
}