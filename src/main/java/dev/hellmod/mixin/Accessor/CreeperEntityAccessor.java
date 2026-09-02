package dev.hellmod.mixin.Accessor;

import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreeperEntity.class)
public interface CreeperEntityAccessor {

    @Accessor("fuseTime")
    int hellmod$getFuseTime();

    @Accessor("fuseTime")
    void hellmod$setFuseTime(int value);
}