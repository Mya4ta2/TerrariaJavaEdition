package ru.terraria.type;

import com.badlogic.gdx.math.Vector2;
import ru.terraria.content.Blocks;

public class World {
    final int width, height;

    private Tiles tiles;
    private Player player;

    public World(int width, int height) {
        tiles = new Tiles(width, height);
        this.width = width;
        this.height = height;

        createWorld();
    }

    /* i need this only for test, after i delete this */
    public void createWorld() {
        player = new Player("player", Vector2.Zero);
        tiles.fill();
        for (Tile tile : tiles.getArray()) {
            tile.setBlock(Blocks.air);
        }
    }

    public Tiles getTiles() {
        return tiles;
    }
}
