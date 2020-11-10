package ru.terraria.type;

import com.badlogic.gdx.math.Vector2;
import ru.terraria.content.Blocks;
import ru.terraria.content.Walls;

import java.util.Random;

public class World {
    final int width, height;

    private Tiles tiles;
    private Player player;

    private Random random; // for test, in after updates its has been deleted

    public World(int width, int height) {
        tiles = new Tiles(width, height);
        this.width = width;
        this.height = height;

        random = new Random();

        createWorld();
    }

    /* i need this only for test, after i delete this */
    public void createWorld() {
        player = new Player("player", new Vector2(width/2,height/2 + 1));

        for (int i = 0; i < width; i++) {
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
            tiles.get(width/2, i).setBlock(Blocks.grass);
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height/2 - 10; j++) {
                tiles.get(j, i).setBlock(Blocks.stone);
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height/2 - 10; j++) {
                tiles.get(j, i).setWall(Walls.stone);
            }
        }

        //spawn ore
        int s = 0;

        for (int i = 0; i < height; i++) {

            int y = i + random(0, 10);

            for (int j = 0; j < width; j++) {

                int x = j + random(0, 10);
                if (y < height/2 - 10 && x < width) {
                    s++;
                    if (s >= Blocks.ironOre.getSpawnRate()) {
                        s = 0;
                        tiles.get(y , x).setBlock(Blocks.ironOre);
                    };
                }
            }
        }

        //make hole
        for (int i = width/2 - 20; i <= width/2 - 10; i++) {
            for (int j = 0; j < height; j++) {
                tiles.get(j,i).setBlock(Blocks.air);
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

    public int random(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }
}
