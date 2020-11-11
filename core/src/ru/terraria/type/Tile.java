package ru.terraria.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.content.Blocks;
import ru.terraria.content.Walls;

public class Tile {
    private Vector2 position = new Vector2();
    private Rectangle bounds = new Rectangle();
    private Block block = Blocks.air;
    private Wall wall = Walls.air;

    private final int WIDTH = 1, HEIGHT = 1;
    private int spriteVariant = 1;

    public Tile(Vector2 position) {
        this.position = position;
        bounds.x = position.x;
        bounds.y = position.y;
        bounds.height = HEIGHT;
        bounds.width = WIDTH;

        spriteVariant = (int) ((Math.random() * ((2 - 0) + 0)) + 0);
    }

    public Tile() {
        this.position = Vector2.Zero;
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

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        bounds.x = position.x;
        bounds.y = position.y;
    }

    public int getVariant() {
        return spriteVariant;
    }

    public void setVariant(int spriteVariant) {
        this.spriteVariant = spriteVariant;
    }
}
