package com.lifehacksdrop.persistentbobber;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BobberWorldState extends PersistentState {
    private final Map<UUID, BlockPos> bobbers = new HashMap<>();

    public static BobberWorldState get(net.minecraft.world.World world) {
        return world.getServer().getOverworld().getPersistentStateManager()
            .getOrCreate(BobberWorldState::new, "persistent_bobbers");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        for (Map.Entry<UUID, BlockPos> entry : bobbers.entrySet()) {
            NbtCompound bobberData = new NbtCompound();
            BlockPos pos = entry.getValue();
            bobberData.putInt("x", pos.getX());
            bobberData.putInt("y", pos.getY());
            bobberData.putInt("z", pos.getZ());
            nbt.put(entry.getKey().toString(), bobberData);
        }
        return nbt;
    }

    public void addBobber(UUID rodUuid, BlockPos pos) {
        bobbers.put(rodUuid, pos);
        markDirty();
    }

    public void removeBobber(UUID rodUuid) {
        bobbers.remove(rodUuid);
        markDirty();
    }

    public BlockPos getBobberPos(UUID rodUuid) {
        return bobbers.get(rodUuid);
    }
}