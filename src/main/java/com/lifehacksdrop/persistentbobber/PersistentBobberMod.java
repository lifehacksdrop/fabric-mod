package com.lifehacksdrop.persistentbobber;

import net.fabricmc.api.ModInitializer;

public class PersistentBobberMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ModEntities.register();
        RodEventHandler.register();
    }
}