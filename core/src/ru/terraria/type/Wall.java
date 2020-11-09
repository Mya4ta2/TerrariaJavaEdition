package ru.terraria.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.Vars;
import ru.terraria.ctype.MappableContent;

public class Wall extends MappableContent {
    private final String name;

    private Vector2 position = new Vector2();
    private Rectangle bounds = new Rectangle();

    private float WIDTH;
    private float HEIGHT;

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

    public Wall(String name) {
        this.name = name;
        bounds.width = WIDTH;
        bounds.height = HEIGHT;
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
}
