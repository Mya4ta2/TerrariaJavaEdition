package ru.terraria.type;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.collision._btMprSimplex_t;
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
        player = new Player("player", new Vector2(width/2,height-10));

        generateWorld(555);
        setBlocksNeighbourAir();

        tiles.get(height - 5, width/2).setBlock(Blocks.dirt);
        tiles.get(height - 6, width/2).setBlock(Blocks.dirt);
        tiles.get(height - 5, width/2-1).setBlock(Blocks.dirt);
        tiles.get(height - 6, width/2-1).setBlock(Blocks.dirt);
        tiles.get(height - 7, width/2).setBlock(Blocks.dirt);
        tiles.get(height - 5, width/2).setBlock(Blocks.dirt);
        tiles.get(height - 7, width/2-2).setBlock(Blocks.dirt);
        tiles.get(height - 5, width/2-2).setBlock(Blocks.dirt);
        tiles.get(height - 7, width/2-2).setBlock(Blocks.dirt);
        tiles.get(height - 6, width/2-2).setBlock(Blocks.dirt);
        tiles.get(height - 7, width/2-1).setBlock(Blocks.dirt);
        tiles.get(height - 7, width/2-1).setBlock(Blocks.dirt);
        setBlocksNeighbourAir();

        //spawn ore
        /*int s = 0;

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
        }*/
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

    public void generateWorld(int seed) {
        Random random = seed > 0 ? new Random(seed) : new Random(System.currentTimeMillis());

        int groundLevelMax = random.nextInt(20 + 1 - 10) + 10;
        int groundLevelMin = groundLevelMax + random.nextInt(20 + 1 - 10) + 10;

        int[] arr = new int[width];
        for (int i = 0; i < width; i++) {
            int dir = random.nextInt(2) == 1 ? 1 : -1;

            if (i > 0) {
                if (arr[i - 1] + dir < groundLevelMax || arr[i - 1] + dir > groundLevelMin) {
                    dir = -dir;
                }

                arr[i] = arr[i - 1] + dir;
            } else {
                arr[i] = groundLevelMin;
            }
        }

        //smooth
        for (int i = 1; i < width - 5; i++) {
            float sum = arr[i];
            int count = 1;
            for (int j = 1; j <= 5; j++) {
                int i1 = i - j;
                int i2 = i + j;

                if (i1 > 0) {
                    sum += arr[i1];
                    count++;
                }

                if (i2 > 0) {
                    sum += arr[i2];
                    count++;
                }

                arr[i] = (int)(sum / count);
            }
        }

        // set tiles to world
        for (int i = 0; i < width; i++) {
            tiles.get(arr[i], i).setBlock(Blocks.grass);
            for (int j = 0; j < arr[i]; j++) {
                tiles.get(j,i).setBlock(Blocks.dirt);
            }
        }

        setBlocksNeighbourAir();
    }

    public void setBlocksNeighbourAir() {
        for (int i = 0; i < getTiles().getArray().length; i++) {
            if (tiles.getArray()[i].getPosition().x > 0 && tiles.getArray()[i].getPosition().y > 0 &&
                    tiles.getArray()[i].getPosition().x < width - 1 && tiles.getArray()[i].getPosition().y < height - 1) {
                Tile downTile = tiles.get((int) tiles.getArray()[i].getPosition().y - 1,
                        (int) tiles.getArray()[i].getPosition().x);
                Tile upTile = tiles.get((int) tiles.getArray()[i].getPosition().y + 1,
                        (int) tiles.getArray()[i].getPosition().x);
                Tile leftTile = tiles.get((int) tiles.getArray()[i].getPosition().y,
                        (int) tiles.getArray()[i].getPosition().x - 1);
                Tile rightTile = tiles.get((int) tiles.getArray()[i].getPosition().y,
                        (int) tiles.getArray()[i].getPosition().x + 1);
                tiles.getArray()[i].setNeighbourAir(downTile, upTile, rightTile, leftTile);
            } else if (tiles.getArray()[i].getPosition().y == 0) {
                tiles.getArray()[i].setBlockNeighbourAir(Block.NeighbourAir.ALL);
            } else if (tiles.getArray()[i].getPosition().x == 0 || tiles.getArray()[i].getPosition().x == width - 1) {
                tiles.getArray()[i].setBlockNeighbourAir(Block.NeighbourAir.ALL);
            }
        }
    }

    public int random(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }
}
