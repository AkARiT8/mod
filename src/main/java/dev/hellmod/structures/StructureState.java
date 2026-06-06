package dev.hellmod.structures;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;

public class StructureState extends PersistentState {

    public boolean generated = false;
    public BlockPos min;
    public BlockPos max;


    @Override
    public NbtCompound writeNbt(
            NbtCompound nbt,
            RegistryWrapper.WrapperLookup registryLookup
    ) {

        nbt.putBoolean(
                "generated",
                generated
        );

        if (min != null) {

            nbt.putInt(
                    "minX",
                    min.getX()
            );

            nbt.putInt(
                    "minY",
                    min.getY()
            );

            nbt.putInt(
                    "minZ",
                    min.getZ()
            );
        }
        if (max != null) {

            nbt.putInt(
                    "maxX",
                    max.getX()
            );

            nbt.putInt(
                    "maxY",
                    max.getY()
            );

            nbt.putInt(
                    "maxZ",
                    max.getZ()
            );
        }

        return nbt;
    }

    public static StructureState fromNbt(
            NbtCompound nbt,
            RegistryWrapper.WrapperLookup registryLookup
    ) {

        StructureState state =
                new StructureState();

        state.generated =
                nbt.getBoolean(
                        "generated"
                );


        if (nbt.contains("minX")) {

            state.min =
                    new BlockPos(

                            nbt.getInt("minX"),
                            nbt.getInt("minY"),
                            nbt.getInt("minZ")
                    );
        }


        if (nbt.contains("maxX")) {

            state.max =
                    new BlockPos(

                            nbt.getInt("maxX"),
                            nbt.getInt("maxY"),
                            nbt.getInt("maxZ")
                    );
        }

        return state;
    }

    public static final Type<StructureState> TYPE =
            new Type<>(
                    StructureState::new,
                    StructureState::fromNbt,
                    null
            );
}