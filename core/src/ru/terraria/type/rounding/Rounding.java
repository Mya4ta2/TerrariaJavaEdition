package ru.terraria.type.rounding;

import com.badlogic.gdx.graphics.Texture;
import ru.terraria.type.Tile;
import ru.terraria.type.Tiles;

public class Rounding {
    private RoundingAtlas roundingAtlas;
    private Tiles tiles;
    private Tile tile;
    private Texture currentTexture;

    public Rounding(RoundingAtlas roundingAtlas, Tiles tiles, Tile tile) {
        this.roundingAtlas = roundingAtlas;
        currentTexture = roundingAtlas.getTextures().get("up");
        this.tiles = tiles;
        this.tile = tile;
    }

    public void update() {

    }

    public Tiles getTilemap() {
        return tiles;
    }

    public void setTilemap(Tiles tiles) {
        this.tiles = tiles;
    }

    public Texture getCurrentTexture() {
        return currentTexture;
    }

    public void setCurrentTexture(Texture currentTexture) {
        this.currentTexture = currentTexture;
    }
}
