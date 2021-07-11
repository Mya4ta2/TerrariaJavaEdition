package ru.terraria.type.rounding;

import com.badlogic.gdx.graphics.Texture;
import ru.terraria.type.RoundingBlock;
import ru.terraria.type.Tile;
import ru.terraria.type.Tiles;

public class Rounding {
    private RoundingAtlas roundingAtlas;
    private RoundingBlock.RoundingType roundingType;
    private Tiles tiles;
    private Tile tile;
    private Texture currentTexture;

    public Rounding(RoundingBlock.RoundingType roundingType, Tiles tiles, Tile tile) {
        this.roundingType = roundingType;
        this.roundingAtlas = roundingType.roundingAtlas;
        currentTexture = roundingAtlas.getTextures().get("up"); // maybe need to add other default value
        this.tiles = tiles;
        this.tile = tile;
    }

    public void update() {
        roundingType.update(this);
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

    public RoundingAtlas getAtlas() {
        return roundingAtlas;
    }

    public Tile getTile() {
        return tile;
    }
}
