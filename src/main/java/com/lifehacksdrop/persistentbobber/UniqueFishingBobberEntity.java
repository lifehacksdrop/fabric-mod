// Unique fishing bobber entity class
package com.lifehacksdrop.persistentbobber;

public UniqueFishingBobberEntity(EntityType<? extends FishingBobberEntity> type, World world) {
    super(type, world);
    this.setPersistent();
}