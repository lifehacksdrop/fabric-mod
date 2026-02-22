package com.lifehacksdrop.persistentbobber;

import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.UUID;

public class UniqueFishingBobberEntity extends FishingBobberEntity {

    private UUID rodUuid;

    public UniqueFishingBobberEntity(EntityType<? extends FishingBobberEntity> type, World world) {
        super(type, world);
    }

    public void setRodUuid(UUID uuid) { this.rodUuid = uuid; }
    public UUID getRodUuid() { return rodUuid; }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (rodUuid != null) nbt.putUuid("RodUUID", rodUuid);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.containsUuid("RodUUID")) this.rodUuid = nbt.getUuid("RodUUID");
    }
}