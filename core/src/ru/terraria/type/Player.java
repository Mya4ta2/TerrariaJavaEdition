package ru.terraria.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.ctype.MappableContent;

public class Player extends MappableContent {
    private final String name;
    private float WIDTH = 1; // need merge to Content/unit
    private float HEIGHT = 2;

    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Rectangle bounds;

    private float speed = 65;

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

    public void update(float delta) {
        position.add(velocity.scl(delta));
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public String getName() {
        return name;
    }

    public float getSpeed() {
        return speed;
    }
}
