package com.lifehacksdrop.persistentbobber;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.UUID;

public class RodEventHandler {

    public static void register() {
        // Optional hooks for rod break/removal
    }

    public static UUID getOrCreateRodUuid(ItemStack rod) {
        if (!rod.hasNbt()) rod.getOrCreateNbt();
        if (!rod.getNbt().containsUuid("RodUUID")) {
            rod.getNbt().putUuid("RodUUID", UUID.randomUUID());
        }
        return rod.getNbt().getUuid("RodUUID");
    }

    public static void spawnBobberOnFirstCast(PlayerEntity player, ItemStack rod) {
        World world = player.world;
        UUID rodUuid = getOrCreateRodUuid(rod);
        BobberWorldState state = BobberWorldState.get(world);

        if (state.getBobberPos(rodUuid) != null) return; // Already exists

        UniqueFishingBobberEntity bobber = new UniqueFishingBobberEntity(ModEntities.UNIQUE_BOBBER, world);
        bobber.setPosition(player.getX(), player.getEyeY(), player.getZ());
        bobber.setRodUuid(rodUuid);
        world.spawnEntity(bobber);

        state.addBobber(rodUuid, bobber.getBlockPos());
    }

    public static void removeBobber(ItemStack rod, World world) {
        if (!rod.hasNbt()) return;
        UUID uuid = rod.getNbt().getUuid("RodUUID");
        world.getEntitiesByClass(UniqueFishingBobberEntity.class,
            e -> e.getRodUuid().equals(uuid))
            .forEach(e -> e.kill());
        BobberWorldState.get(world).removeBobber(uuid);
    }
}