package ru.terraria.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.Vars;
import ru.terraria.ctype.MappableContent;

public class Player extends MappableContent {
    private final String name;
    private float WIDTH = 1; // need merge to Content/unit
    private float HEIGHT = 2;

    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Rectangle bounds = new Rectangle();

    private float speed = 8;
    private float jumpHeight = 256;
    private boolean grounded;

    public Player(String name) {
        this.position = Vector2.Zero;
        this.name = name;

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = WIDTH * Vars.TILE_SIZE;
        bounds.height = HEIGHT * Vars.TILE_SIZE;
    }

    public Player(String name, Vector2 position) {
        this.position = position;
        this.name = name;

        bounds.x = position.x;
        bounds.y = position.y;
        bounds.width = WIDTH * Vars.TILE_SIZE;
        bounds.height = HEIGHT * Vars.TILE_SIZE;
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

        bounds.x = position.x;
        bounds.y = position.y;
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

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(float jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public void jump() {
        velocity.y += jumpHeight;
    }
}
