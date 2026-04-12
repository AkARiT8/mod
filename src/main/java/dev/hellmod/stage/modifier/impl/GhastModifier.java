package dev.hellmod.stage.modifier.impl;

import com.google.gson.JsonObject;
import dev.hellmod.access.GhastAccessor;
import dev.hellmod.stage.modifier.EntityModifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.nbt.NbtCompound;

public class GhastModifier implements EntityModifier {

    private static final String KEY = "hellmod_fireball_power";

    @Override
    public void apply(LivingEntity entity, JsonObject data) {

        if (!(entity instanceof GhastEntity ghast)) return;
        if (!(ghast instanceof GhastAccessor accessor)) return;

        if (!data.has("fireball")) return;

        JsonObject fireball = data.getAsJsonObject("fireball");

        float power = 0;

        if (fireball.has("fireball_power")) {
            power = fireball.get("fireball_power").getAsFloat();
            accessor.hellmod$setFireballPower(power);
        }

        System.out.println("APLICANDO GHAST MODIFIER: " + power);
    }

    public static float getFireballPower(GhastEntity ghast) {

        NbtCompound nbt = new NbtCompound();
        ghast.writeNbt(nbt);

        if (nbt.contains(KEY)) {
            return nbt.getFloat(KEY);
        }

        return 1.0f;
    }
}