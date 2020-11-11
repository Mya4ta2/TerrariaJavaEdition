package ru.terraria.gameui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.terraria.content.Items;
import ru.terraria.type.Item;
import ru.terraria.type.ItemStack;

public class ItemSlot extends Actor {
    private final Texture slot;

    private ItemStack items;
    private BitmapFont font;

    public ItemSlot(Texture slot) {
        this.slot = slot;
        items = new ItemStack();
        font = new BitmapFont();

        setDefaultPos();
        setDefaultSize();

        addListener(new ItemSlotInputListener(this));
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

        if (items.getItemType() != Items.air) {
            batch.draw(items.getItemType().getTexture(),
                    getX() + items.getItemType().getTexture().getRegionWidth() / 2f,
                    getY() + items.getItemType().getTexture().getRegionHeight() / 2f,
                    getWidth() -items.getItemType().getTexture().getRegionWidth(),
                    getHeight() -items.getItemType().getTexture().getRegionHeight());

            font.draw(batch,String.valueOf(items.getItemsContains()), getX() + 8, getY() + 16);
        }
    }

    public Item getItem() {
        return items.getItemType();
    }

    public void setItem(Item item) {
        this.items.setItemType(item);
    }

    public ItemStack getItemStack() {
        return items;
    }

    public void setItemStack(ItemStack items) {
        this.items = items;
    }
}