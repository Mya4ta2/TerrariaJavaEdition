package ru.terraria.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpriteBar extends Actor {

    private Texture texture;
    private int max;
    private int min;
    private int value = 2;
    private  int spriteSize;

    public SpriteBar(float min, float max, Texture texture) {
        this.min = (int) min;
        this.max = (int) max;
        this.texture = texture;

        spriteSize = texture.getWidth();

        setDefaultWH();
        setDefaultXY();
    }

    public void setDefaultWH() {
        setWidth(spriteSize*5);
        setHeight(spriteSize);
    }

    public void setDefaultXY() {
        setX(0);
        setY(0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if (value > max) value = max;

        for (int i = 0; i < value; i++) {
            batch.draw(texture, getX() + i * spriteSize, getY(), spriteSize, spriteSize);
        }
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
