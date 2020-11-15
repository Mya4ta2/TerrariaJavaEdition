package ru.terraria.gameui;

import com.badlogic.gdx.scenes.scene2d.InputListener;

public class AccessoryInputListener extends InputListener {
    private AccessorySlot accessory;

    public AccessoryInputListener(AccessorySlot accessory) {
        this.accessory = accessory;
    }
}
