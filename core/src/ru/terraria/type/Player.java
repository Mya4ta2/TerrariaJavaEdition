package ru.terraria.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.Vars;
import ru.terraria.ctype.MappableContent;

public class Player extends MappableContent {
    private final String name;
    private float WIDTH = 1.6f; // need merge to Content/unit
    private float HEIGHT = 2.6f;

    private Vector2 position = new Vector2();
    private Vector2 oldPosition = new Vector2();
    private Vector2 velocity = new Vector2();
    private Rectangle bounds = new Rectangle();
    private Rectangle groundHitBox = new Rectangle();

    private int MaxHealth = 100;
    private int health;

    private float speed = 8;
    private float jumpHeight = 1000;
    private float jumpNum;
    private boolean grounded;

    public Player(String name) {
        this.position = Vector2.Zero;
        this.name = name;

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = WIDTH;
        bounds.height = HEIGHT;

        groundHitBox.width = Vars.TILE_SIZE;
        groundHitBox.height = Vars.TILE_SIZE;
    }

    @Override
    public void draw(SpriteBatch batch, Tile tile) {

    }

    public Player(String name, Vector2 position) {
        this.position = position;
        this.name = name;

        bounds.x = position.x;
        bounds.y = position.y;
        bounds.width = WIDTH;
        bounds.height = HEIGHT;

        groundHitBox.x = position.x;
        groundHitBox.y = position.y;
        groundHitBox.width = WIDTH;
        groundHitBox.height = 1;

        heal();
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
        oldPosition.set(position);
        position.add(velocity.scl(delta));

        if (jumpNum > 0) {
            jumpNum -= jumpHeight/24;
            velocity.y += jumpHeight/24;
        }

        bounds.x = position.x;
        bounds.y = position.y;
        groundHitBox.x = position.x;
        groundHitBox.y = position.y - 0.5f;
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

    public Vector2 getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(Vector2 oldPosition) {
        this.oldPosition = oldPosition;
    }

    public void jump() {
        jumpNum += jumpHeight;
    }

    public void heal() {
        this.health = MaxHealth;
    }

    public void heal(int health) {
        this.health += health;
        if (this.health > MaxHealth) this.health = MaxHealth;
    }

    public int getMaxHealth() {
        return MaxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        MaxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Rectangle getGroundHitBox() {
        return groundHitBox;
    }

    public void setGroundHitBox(Rectangle groundHitBox) {
        this.groundHitBox = groundHitBox;
    }
}
