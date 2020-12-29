package ru.terraria.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AccessoryPanel extends Actor {
    private final int width;
    private final int height;
    private final Texture slotTexture;

    private AccessorySlot[][] accessories;

    public AccessoryPanel(int width, int height, Texture slotTexture) {
        this.width = width;
        this.height = height;
        this.slotTexture = slotTexture;

        accessories = new AccessorySlot[width][height];

        fill();
        setDefaultPos();
        setDefaultSize();
        setSlotsPosition();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                accessories[j][i].draw(batch, parentAlpha);
            }
        }
    }

    public void fill() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                accessories[j][i] = new AccessorySlot(slotTexture);
            }
        }
    }

    @Override
    public void act(float delta) {
        setSlotsPosition();
        super.act(delta);
    }

    public void setSlotsPosition() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                accessories[j][i].setPosition(
                        getX() + j * (accessories[j][i].getWidth() + 4),
                        (getY() + getHeight()) - i * (accessories[j][i].getHeight() + 4));
            }
        }

        setWidth((accessories[0][0].getWidth() + 4) * width);
        setHeight(accessories[0][0].getHeight() * height);
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
}
