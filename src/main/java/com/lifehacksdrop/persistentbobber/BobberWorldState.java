// Class to manage world state for bobbers
package com.lifehacksdrop.persistentbobber;

public boolean hasBobber(UUID rodUUID) {
    return bobbers.containsKey(rodUUID);
}