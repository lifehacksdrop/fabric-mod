package com.lifehacksdrop.persistentbobber;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {

    public static final EntityType<UniqueFishingBobberEntity> UNIQUE_BOBBER =
        Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("persistentbobber", "unique_bobber"),
            FabricEntityTypeBuilder.<UniqueFishingBobberEntity>create(SpawnGroup.MISC, UniqueFishingBobberEntity::new)
                .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                .build()
        );

    public static void register() {}
}