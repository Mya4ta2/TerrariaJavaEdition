package ru.terraria.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.terraria.Vars;

public class ItemSlots extends Actor {
    private final int size;
    private final Texture slotTexture;

    private ItemSlot[] slots;

    public ItemSlots(int size, Texture slotTexture) {
        this.size = size;
        this.slotTexture = slotTexture;

        slots = new ItemSlot[size];

        fill();
        setSlotsPosition();
        setDefaultPos();
        setDefaultSize();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < slots.length; i++) {
            slots[i].draw(batch,parentAlpha);
        }
    }

    public void fill() {
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new ItemSlot(slotTexture);
        }
    }

    public void setSlotsPosition() {
        for (int i = 0; i < slots.length; i++) {
            slots[i].setPosition(getX() + i * slots[i].getWidth() + 5 ,getY());
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
        setSlotsPosition();
        super.setX(x);
    }

    @Override
    public void setX(float x, int alignment) {
        setSlotsPosition();
        super.setX(x, alignment);
    }

    @Override
    public void setY(float y) {
        setSlotsPosition();
        super.setY(y);
    }

    @Override
    public void setY(float y, int alignment) {
        setSlotsPosition();
        super.setY(y, alignment);
    }

    @Override
    public void setPosition(float x, float y) {
        setSlotsPosition();
        super.setPosition(x, y);
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        setSlotsPosition();
        super.setPosition(x, y, alignment);
    }

    public int getSize() {
        return size;
    }

    public ItemSlot[] getSlots() {
        return slots;
    }

    public void setSlots(ItemSlot[] slots) {
        this.slots = slots;
    }
}
