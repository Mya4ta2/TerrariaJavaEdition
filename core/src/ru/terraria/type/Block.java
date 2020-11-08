package ru.terraria.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.ctype.MappableContent;

public class Block extends MappableContent {
    private final String name;

    private float WIDTH;
    private float HEIGHT;

    private Vector2 position;
    private Rectangle bounds;

    public Block(String name) {
        this.name = name;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public String getName() {
        return name;
    }

    public float getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(float WIDTH) {
        this.WIDTH = WIDTH;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(float HEIGHT) {
        this.HEIGHT = HEIGHT;
    }
}
