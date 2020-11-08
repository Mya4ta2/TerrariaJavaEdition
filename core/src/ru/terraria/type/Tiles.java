package ru.terraria.type;

import com.badlogic.gdx.math.Vector2;

public class Tiles {
    final Tile[] array;
    private final int width, height;

    public Tiles(int width, int height) {
        this.array = new Tile[width * height];
        this.width = width;
        this.height = height;
    }

    public Tile get(int y, int x) {
        return array[y * width + x];
    }

    public void set(int y, int x, Tile tile) {
        array[y * width + x] = tile;
    }

    public void fill(){
        for(int i = 0; i < array.length; i++){
            array[i] = new Tile(new Vector2(i % width, i / width));
        }
    }

    public Tile[] getArray() {
        return array;
    }
}
