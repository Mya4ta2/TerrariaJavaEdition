package ru.terraria.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ItemPanel extends Actor {
    private final int width;
    private final int height;
    private final Texture slotTexture;
    private final Texture activeSlotTexture;

    private ItemSlot[][] slots;

    public ItemPanel(int width, int height, Texture slotTexture, Texture activeSlotTexture) {
        this.width = width;
        this.height = height;
        this.slotTexture = slotTexture;
        this.activeSlotTexture = activeSlotTexture;

        slots = new ItemSlot[width][height];

        fill();
        setDefaultPos();
        setDefaultSize();
        setSlotsPosition();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                slots[i][j].draw(batch,parentAlpha);
            }
        }
    }

    public void fill() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                slots[j][i] = new ItemSlot(slotTexture, activeSlotTexture);
            }
        }
    }

    public void setSlotsPosition() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                slots[j][i].setPosition(getX() + j * (slots[j][i].getWidth() + 4) ,getY() - i * (slots[j][i].getHeight() + 4));
            }
        }
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
    public void setX(float x) {
        super.setX(x);
        setSlotsPosition();
    }

    @Override
    public void setX(float x, int alignment) {
        super.setX(x, alignment);
        setSlotsPosition();
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        setSlotsPosition();
    }

    @Override
    public void setY(float y, int alignment) {
        super.setY(y, alignment);
        setSlotsPosition();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setSlotsPosition();
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        super.setPosition(x, y, alignment);
        setSlotsPosition();
    }

    public int getSize() {
        return width * height;
    }

    public ItemSlot[][] getSlots() {
        return slots;
    }

    public void setSlots(ItemSlot[][] slots) {
        this.slots = slots;
    }
}