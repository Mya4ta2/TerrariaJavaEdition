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
        player = new Player("player", new Vector2(width/2,height/2 + 1));

        for (int i = 0; i < width; i++) {
            if (i >= width/2 - 20 && i <= width/2 - 10) continue;
            for (int j = 0; j < height/2; j++) {
                tiles.get(j,i).setBlock(Blocks.dirt);
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height/2; j++) {
                tiles.get(j,i).setWall(Walls.dirt);
            }
        }

        for (int i = 0; i < width; i++) {
            if (i >= width/2 - 20 && i <= width/2 - 10) continue;
            tiles.get(width/2, i).setBlock(Blocks.grass);
        }

        for (int i = 0; i < width; i++) {
            if (i >= width/2 - 20 && i <= width/2 - 10) continue;
            for (int j = 0; j < height/2 - 10; j++) {
                tiles.get(j, i).setBlock(Blocks.stone);
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height/2 - 10; j++) {
                tiles.get(j, i).setWall(Walls.stone);
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
