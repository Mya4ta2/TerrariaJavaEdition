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
        player = new Player("player", new Vector2(0,1));

        for (int i = 0; i < 100; i++) { // just testing world
            if (i == 3 || i == 4) continue;
            tiles.get(0,i).setBlock(Blocks.dirt);
            tiles.get(0,i).setPosition(new Vector2(i,0));
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
