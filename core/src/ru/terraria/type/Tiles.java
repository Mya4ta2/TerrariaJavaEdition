package ru.terraria.type;

import com.badlogic.gdx.math.Vector2;

public class Tiles {
    final Tile[] array;
    private final int width, height;

    public Tiles(int width, int height) {
        this.array = new Tile[width * height];
        this.width = width;
        this.height = height;

        fill();
    }

    public Tile get(int x, int y) {
        return array[y * width + x];
    }

    public void set(int y, int x, Tile tile) {
        array[y * width + x] = tile;
    }

    public void fill(){
        for (int i = 0; i < array.length; i++){
            array[i] = new Tile(new Vector2(i % width, i / width));
        }
    }

    public boolean inBounds(Vector2 position) {
        if (position.x < 0 || position.y < 0) return false;
        return !(position.x > width - 1) && !(position.y > height - 1);
    }

    public Tile[] getArray() {
        return array;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
