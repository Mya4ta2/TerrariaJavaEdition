package ru.terraria.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.terraria.content.Items;
import ru.terraria.type.Item;

public class ItemSlot extends Actor {
    private final Texture slot;

    private Item item = Items.air;

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
        setWidth(42);
        setHeight(42);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(slot,getX(),getY(), getWidth(), getHeight());

        if (item != Items.air) {
            batch.draw(item.getTexture(),
                    getX() + item.getTexture().getRegionWidth() / 2f,
                    getY() + item.getTexture().getRegionHeight() / 2f,
                    getWidth() - item.getTexture().getRegionWidth(),
                    getHeight() -item.getTexture().getRegionHeight());
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
