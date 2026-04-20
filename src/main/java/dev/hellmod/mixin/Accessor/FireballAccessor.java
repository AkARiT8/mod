package dev.hellmod.mixin.Accessor;

import net.minecraft.entity.projectile.FireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FireballEntity.class)
public interface FireballAccessor {

    @Accessor("explosionPower")
    void hellmod$setExplosionPower(int power);
}