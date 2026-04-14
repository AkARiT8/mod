package dev.hellmod.mixin;

import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreeperEntity.class)
public interface CreeperAccessor {

    @Accessor("explosionRadius")
    void setExplosionRadius(int radius);

    @Accessor("fuseTime")
    void setFuseTime(int time);

    @Accessor("currentFuseTime")
    void setCurrentFuseTime(int time);

    @Accessor("fuseTime")
    int getFuseTime();

    @Accessor("currentFuseTime")
    int getCurrentFuseTime();


}