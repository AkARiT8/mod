package dev.hellmod.mixin.mobMixin;

import dev.hellmod.access.GhastAccessor;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.GhastEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GhastEntity.class)
public abstract class GhastMixin implements GhastAccessor {

    @Unique
    private static final TrackedData<Float> FIREBALL_POWER =
            DataTracker.registerData(GhastEntity.class, TrackedDataHandlerRegistry.FLOAT);

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void init(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(FIREBALL_POWER, 1.0f);
    }

    @Override
    public void hellmod$setFireballPower(float power) {
        ((GhastEntity)(Object)this).getDataTracker().set(FIREBALL_POWER, power);
    }

    @Override
    public float hellmod$getFireballPower() {
        return ((GhastEntity)(Object)this).getDataTracker().get(FIREBALL_POWER);
    }
}