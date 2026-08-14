package dev.hellmod.structures.InfernalManor;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.structure.Structure;

public class InfernalManorUtil {

    public static boolean isInsideInfernalManor(
            ServerWorld world,
            BlockPos pos
    ) {

        RegistryEntry<Structure> structureEntry =
                world.getRegistryManager()
                        .get(RegistryKeys.STRUCTURE)
                        .getEntry(
                                Identifier.of(
                                        "hellmod",
                                        "infernal_manor"
                                )
                        )
                        .orElse(null);

        if (structureEntry == null) {
            return false;
        }

        Structure structure = structureEntry.value();

        StructureStart start =
                world.getStructureAccessor()
                        .getStructureContaining(
                                pos,
                                structure
                        );

        return start != StructureStart.DEFAULT;
    }
}