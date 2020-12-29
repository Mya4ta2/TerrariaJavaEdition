package ru.terraria.gameui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpriteBar extends Actor {

    private Texture texture;
    private int max;
    private int min;
    private boolean inverseDraw;
    private float scale = 1;
    private int value = 2;
    private int spriteSize;

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

        if (!inverseDraw) {
            for (int i = 0; i < value; i++) {
                batch.draw(texture, getX() + i * spriteSize * scale, getY(), spriteSize * scale, spriteSize * scale);
            }
        } else {
            for (int i = 0; i < value; i++) {
                batch.draw(texture, getX() - i * spriteSize * scale, getY(), spriteSize * scale, spriteSize * scale);
            }
        }
    }

    @Override
    public void act(float delta) {
        setWidth(max/(spriteSize*scale) * (spriteSize * scale));
        setHeight(spriteSize * scale);
        super.act(delta);
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

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean isInverseDraw() {
        return inverseDraw;
    }

    public void setInverseDraw(boolean inverseDraw) {
        this.inverseDraw = inverseDraw;
    }
}
