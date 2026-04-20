package dev.hellmod.mixin.Accessor;

import net.minecraft.entity.projectile.SmallFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SmallFireballEntity.class)
public abstract class FireballFlagAccessor {

    @Unique
    private boolean hellmod$fromMultishot = false;

    @Unique
    public boolean hellmod$isFromMultishot() {
        return hellmod$fromMultishot;
    }

    @Unique
    public void hellmod$setFromMultishot(boolean value) {
        this.hellmod$fromMultishot = value;
    }
}