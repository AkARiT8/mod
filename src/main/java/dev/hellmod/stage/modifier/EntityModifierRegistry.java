package dev.hellmod.stage.modifier;

import java.util.HashMap;
import java.util.Map;

public class EntityModifierRegistry {

    private static final Map<String, EntityModifier> MODIFIERS = new HashMap<>();

    public static void register(String key, EntityModifier modifier) {
        MODIFIERS.put(key, modifier);
    }

    public static EntityModifier get(String key) {
        return MODIFIERS.get(key);
    }

    public static Map<String, EntityModifier> getAll() {
        return MODIFIERS;
    }
}