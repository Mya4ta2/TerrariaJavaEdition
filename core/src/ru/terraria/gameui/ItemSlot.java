package ru.terraria.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ItemSlot extends Actor {
    private final Texture slot;

    //private Item item;

    public ItemSlot(Texture slot) {
        this.slot = slot;

        setDefaultPos();
        setDefaultSize();
    }

    public void setDefaultPos() {
        setX(100);
        setY(100);
    }

    public void setDefaultSize() {
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(slot,getX(),getY(), getWidth(), getHeight());
    }

}
