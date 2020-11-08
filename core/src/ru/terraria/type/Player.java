package ru.terraria.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.ctype.MappableContent;

public class Player extends MappableContent {
    private final String name;

    private Vector2 position = new Vector2();
    private Rectangle bounds;

    private float WIDTH;
    private float HEIGHT;

    public Player(String name) {
        this.position = Vector2.Zero;
        this.name = name;
    }

    public Player(String name, Vector2 position) {
        this.position = position;
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
