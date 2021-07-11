package ru.terraria.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.Vars;
import ru.terraria.content.Blocks;
import ru.terraria.content.Walls;
import ru.terraria.type.rounding.Rounding;

public class Tile {
    private Vector2 position = new Vector2();
    private Rectangle bounds = new Rectangle();
    private Block block = Blocks.air;
    private Wall wall = Walls.air;
    private Rounding blockRounding;

    private Tile downTile;
    private Tile upTile;
    private Tile leftTile;
    private Tile rightTile;

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

    public void draw(SpriteBatch batch) {
        if (block != Blocks.air) {
            block.draw(batch, this);
        }

        if (wall != Walls.air) {
            wall.draw(batch, this);
        }

        if (!block.hard) {
            bounds.width = 0;
            bounds.height = 0;
        } else {
            bounds.height = HEIGHT;
            bounds.width = WIDTH;
        }
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

        if (block.isRounding()) {
            blockRounding = new Rounding(
                    ((RoundingBlock) block).getRoundingType(),
                    Vars.world.getTiles(),
                    this
            );

            setNeighbourAir();
        }
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

    public Rounding getBlockRounding() {
        return blockRounding;
    }

    public void setBlockRounding(Rounding blockRounding) {
        this.blockRounding = blockRounding;
    }

    public void setTiles(Tile downTile, Tile upTile, Tile rightTile, Tile leftTile) {
        this.rightTile = rightTile;
        this.leftTile = leftTile;
        this.upTile = upTile;
        this.downTile = downTile;
    }

    public void setNeighbourAir() {
        blockRounding.update();
    }
}
