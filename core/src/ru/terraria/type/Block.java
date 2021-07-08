package ru.terraria.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.Vars;
import ru.terraria.content.Blocks;
import ru.terraria.ctype.MappableContent;

import java.awt.*;
import java.util.Arrays;

public class Block extends MappableContent {
    private final String name;

    private float WIDTH;
    private float HEIGHT;
    protected boolean hard = true;

    private int variants = 3;
    private TextureRegion[] texture;

    private Vector2 position = new Vector2();
    private Rectangle bounds = new Rectangle();

    public Block(String name) {
        this.name = name;
        bounds.width = WIDTH;
        bounds.height = HEIGHT;

        texture = new TextureRegion[]{
                new TextureRegion(Vars.atlas.errorTexture),
                new TextureRegion(Vars.atlas.errorTexture),
                new TextureRegion(Vars.atlas.errorTexture),
                new TextureRegion(Vars.atlas.errorTexture),
                new TextureRegion(Vars.atlas.errorTexture),
                new TextureRegion(Vars.atlas.errorTexture),
                new TextureRegion(Vars.atlas.errorTexture),
                new TextureRegion(Vars.atlas.errorTexture),
                new TextureRegion(Vars.atlas.errorTexture),
                new TextureRegion(Vars.atlas.errorTexture),
        };
    }

    @Override
    public void draw(SpriteBatch batch, Tile tile) {
        batch.draw(texture[tile.getVariant()],
                tile.getPosition().x * Vars.TILE_SIZE,
                tile.getPosition().y * Vars.TILE_SIZE,
                WIDTH * Vars.TILE_SIZE,
                HEIGHT * Vars.TILE_SIZE);
    }

    public void setWIDTH(float WIDTH) {
        this.WIDTH = WIDTH;
    }

    public void setHEIGHT(float HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public TextureRegion[] getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion[] texture) {
        this.texture = texture;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public boolean isRounding() {
        return this instanceof RoundingBlock;
    }

    public String getName() {
        return name;
    }
}
