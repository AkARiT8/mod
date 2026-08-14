package dev.hellmod.structures;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;

public class ModStructure {

    public static final RegistryKey<Structure> INFERNAL_MANOR =
            RegistryKey.of(
                    RegistryKeys.STRUCTURE,
                    Identifier.of("hellmod", "infernal_manor")
            );
}