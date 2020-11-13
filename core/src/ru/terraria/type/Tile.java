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
    private Block.NeighbourAir blockNeighbourAir = Block.NeighbourAir.NONE;

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

    public Block.NeighbourAir getNeighbourAir() {
        return blockNeighbourAir;
    }

    public void setBlockNeighbourAir(Block.NeighbourAir blockNeighbourAir) {
        this.blockNeighbourAir = blockNeighbourAir;
    }

    public void setNeighbourAir(Tile downTile, Tile upTile, Tile rightTile, Tile leftTile) {
        if (downTile != null && upTile != null && rightTile != null & leftTile != null) {
            if
            (
                    downTile.getBlock() == Blocks.air &&
                            upTile.getBlock() == Blocks.air &&
                            rightTile.getBlock() == Blocks.air &&
                            leftTile.getBlock() == Blocks.air
            )
            {
                blockNeighbourAir = Block.NeighbourAir.NONE;
            }

            if
            (
                    downTile.getBlock() != Blocks.air &&
                            upTile.getBlock() != Blocks.air &&
                            rightTile.getBlock() != Blocks.air &&
                            leftTile.getBlock() != Blocks.air
            )
            {
                blockNeighbourAir = Block.NeighbourAir.ALL;
            }

            if
            (
                    downTile.getBlock() == Blocks.air &&
                            upTile.getBlock() != Blocks.air &&
                            rightTile.getBlock() != Blocks.air &&
                            leftTile.getBlock() != Blocks.air
            )
            {
                blockNeighbourAir = Block.NeighbourAir.DOWN;
            }

            if
            (
                    downTile.getBlock() != Blocks.air &&
                            upTile.getBlock() != Blocks.air &&
                            rightTile.getBlock() == Blocks.air &&
                            leftTile.getBlock() != Blocks.air
            )
            {
                blockNeighbourAir = Block.NeighbourAir.RIGHT;
            }

            if
            (
                    downTile.getBlock() != Blocks.air &&
                            upTile.getBlock() == Blocks.air &&
                            rightTile.getBlock() != Blocks.air &&
                            leftTile.getBlock() != Blocks.air
            )
            {
                blockNeighbourAir = Block.NeighbourAir.UP;
            }

            if
            (
                    downTile.getBlock() != Blocks.air &&
                            upTile.getBlock() != Blocks.air &&
                            rightTile.getBlock() != Blocks.air &&
                            leftTile.getBlock() == Blocks.air
            )
            {
                blockNeighbourAir = Block.NeighbourAir.LEFT;
            }


            if
            (
                    downTile.getBlock() != Blocks.air &&
                            upTile.getBlock() == Blocks.air &&
                            rightTile.getBlock() != Blocks.air &&
                            leftTile.getBlock() == Blocks.air
            )
            {
                blockNeighbourAir = Block.NeighbourAir.LEFT_UP;
            }

            if
            (
                    downTile.getBlock() == Blocks.air &&
                            upTile.getBlock() != Blocks.air &&
                            rightTile.getBlock() != Blocks.air &&
                            leftTile.getBlock() == Blocks.air
            )
            {
                blockNeighbourAir = Block.NeighbourAir.LEFT_DOWN;
            }

            if
            (
                    downTile.getBlock() != Blocks.air &&
                            upTile.getBlock() == Blocks.air &&
                            rightTile.getBlock() == Blocks.air &&
                            leftTile.getBlock() != Blocks.air
            )
            {
                blockNeighbourAir = Block.NeighbourAir.RIGHT_UP;
            }

            if
            (
                    downTile.getBlock() == Blocks.air &&
                            upTile.getBlock() != Blocks.air &&
                            rightTile.getBlock() == Blocks.air &&
                            leftTile.getBlock() != Blocks.air
            )
            {
                blockNeighbourAir = Block.NeighbourAir.RIGHT_DOWN;
            }
        }
    }
}
