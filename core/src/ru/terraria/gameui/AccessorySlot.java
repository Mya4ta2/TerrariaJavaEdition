package ru.terraria.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.terraria.content.Items;
import ru.terraria.type.Item;
import ru.terraria.type.ItemStack;

public class AccessorySlot extends Actor {
    private final Texture slot;

    private Item accessory;

    public AccessorySlot(Texture slot) {
        this.slot = slot;
        accessory = Items.air;

        setDefaultPos();
        setDefaultSize();

        addListener(new AccessoryInputListener(this));
    }

    public void setDefaultPos() {
        setX(100);
        setY(100);
    }

    public void setDefaultSize() {
        setWidth(42);
        setHeight(42);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(slot,getX(),getY(), getWidth(), getHeight());

        if (accessory != Items.air) {
            batch.draw(accessory.getTexture(),
                    getX() + accessory.getTexture().getRegionWidth() / 2f,
                    getY() + accessory.getTexture().getRegionHeight() / 2f,
                    getWidth() - accessory.getTexture().getRegionWidth(),
                    getHeight() - accessory.getTexture().getRegionHeight());

        }
    }
}
