package com.lifehacksdrop.persistentbobber;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

import java.util.UUID;

public class RodEventHandler {

    public static void handleFirstCast(PlayerEntity player, ItemStack rod) {

        if (!(player.getWorld() instanceof ServerWorld world)) return;

        UUID rodUUID;

        if (!rod.hasNbt() || !rod.getNbt().containsUuid("RodUUID")) {

            rodUUID = UUID.randomUUID();
            rod.getOrCreateNbt().putUuid("RodUUID", rodUUID);

        } else {

            rodUUID = rod.getNbt().getUuid("RodUUID");

        }

        BobberWorldState state = BobberWorldState.get(world);

        if (state.hasBobber(rodUUID)) return;

        UniqueFishingBobberEntity bobber =
                new UniqueFishingBobberEntity(ModEntities.UNIQUE_BOBBER, world);

        bobber.setPosition(
                player.getX(),
                player.getEyeY(),
                player.getZ()
        );

        bobber.setRodUUID(rodUUID);

        world.spawnEntity(bobber);

        state.addBobber(rodUUID, bobber.getUuid());
    }
}