package ru.terraria.type;

import com.badlogic.gdx.math.Vector2;
import ru.terraria.content.Blocks;
import ru.terraria.content.Walls;

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
        player = new Player("player", new Vector2(0,21));

        for (int i = 0; i < 100; i++) {
            if (i >= 15 && i <= 20) continue;
            tiles.get(20,i).setBlock(Blocks.dirt);
        }

        for (int i = 0; i < 100; i++) {
            if (i >= 15 && i <= 20) continue;
            tiles.get(19,i).setBlock(Blocks.dirt);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 21; j++) {
                tiles.get(j,i).setWall(Walls.dirt);
            }
        }
    }

    public Tiles getTiles() {
        return tiles;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
