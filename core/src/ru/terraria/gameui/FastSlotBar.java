package ru.terraria.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FastSlotBar extends Actor {
    private final int size;
    private final Texture slotTexture;
    private final Texture activeSlotTexture;

    private ItemSlot[] slots;
    private int selectedSlot;
    private float scroll;

    public FastSlotBar(int size, Texture slotTexture, Texture activeSlotTexture) {
        this.size = size;
        this.slotTexture = slotTexture;

        slots = new ItemSlot[size];
        this.activeSlotTexture = activeSlotTexture;

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

    public void setSelectedSlot(int selectedSlot) {
        for (int i = 0; i < slots.length; i++) {
            slots[i].setSelected(false);
        }
        this.selectedSlot = selectedSlot;
        slots[selectedSlot].setSelected(true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (scroll > 0 && selectedSlot < slots.length-1) {
            scroll = 0;
            selectedSlot += 1;
        } else if (scroll > 0 && selectedSlot < slots.length) {
            selectedSlot = 0;
        }

        if (scroll < 0 && selectedSlot > 0) {
            scroll = 0;
            selectedSlot -= 1;
        } else if (scroll < 0 && selectedSlot == 0) {
            selectedSlot = slots.length-1;
        }

        setSelectedSlot(selectedSlot);
    }

    public float getScroll() {
        return scroll;
    }

    public void setScroll(float scroll) {
        this.scroll = scroll;
    }

    public void fill() {
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new ItemSlot(slotTexture, activeSlotTexture);
        }
    }

    public void setSlotsPosition() {
        for (int i = 0; i < slots.length; i++) {
            slots[i].setPosition(getX() + i * (slots[i].getWidth() + 4) ,getY());
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
