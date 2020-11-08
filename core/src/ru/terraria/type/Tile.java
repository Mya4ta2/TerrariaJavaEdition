package ru.terraria.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.content.Blocks;

public class Tile {
    private Vector2 position = new Vector2();
    private Rectangle bounds = new Rectangle();
    private Block block;
    private Floor floor;

    public Tile(Vector2 position) {
        this.position = position;
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
        block.getPosition().set(getPosition());
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
        floor.getPosition().set(getPosition());
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        if (block != null) {
            block.getPosition().set(position);
        }

        if (floor != null) {
            floor.getPosition().set(position);
        }
    }
}
