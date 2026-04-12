package dev.hellmod.stage.modifier.impl;

import com.google.gson.JsonObject;
import dev.hellmod.access.BlazeAccessor;
import dev.hellmod.stage.modifier.EntityModifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;

public class BlazeModifier implements EntityModifier {

    @Override
    public void apply(LivingEntity entity, JsonObject data) {

        if (!(entity instanceof BlazeEntity blaze)) return;
        if (!(blaze instanceof BlazeAccessor accessor)) return;

        if (!data.has("blaze_fireball")) return;

        JsonObject fireball = data.getAsJsonObject("blaze_fireball");

        if (fireball.has("damage")) {
            float damage = fireball.get("damage").getAsFloat();
            accessor.hellmod$setBlazeDamage(damage);
        }

        if (fireball.has("count")) {
            int count = fireball.get("count").getAsInt();
            accessor.hellmod$setBlazeShots(count);
        }

        System.out.println("BLAZE MODIFIER → damage: "
                + accessor.hellmod$getBlazeDamage()
                + " shots: "
                + accessor.hellmod$getBlazeShots());
    }
}