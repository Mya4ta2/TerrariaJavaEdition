package ru.terraria.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AccessoryPanel extends Actor {
    private final int width;
    private final int height;
    private final Texture slotTexture;
    private boolean inverse = false;

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
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                accessories[i][j].draw(batch,parentAlpha);
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

    public void setSlotsPosition() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!inverse) {
                    accessories[j][i].setPosition(getX() + j * (accessories[j][i].getWidth() + 4) ,getY() - i * (accessories[j][i].getHeight() + 4));
                } else {
                    accessories[j][i].setPosition(getX() - j * (accessories[j][i].getWidth() + 4) ,getY() - i * (accessories[j][i].getHeight() + 4));
                }
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

    public boolean isInverse() {
        return inverse;
    }

    public void setInverse(boolean inverse) {
        this.inverse = inverse;
    }
}
