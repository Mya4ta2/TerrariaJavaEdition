package ru.terraria.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.Vars;
import ru.terraria.ctype.MappableContent;

import java.util.Arrays;

public class Wall extends MappableContent {
    private final String name;

    private Vector2 position = new Vector2();
    private Rectangle bounds = new Rectangle();

    private int variants = 3;
    private TextureRegion[] texture = new TextureRegion[variants];

    private float WIDTH;
    private float HEIGHT;

    public float getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(float WIDTH) {
        this.WIDTH = WIDTH;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(float HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public Wall(String name) {
        this.name = name;
        bounds.width = WIDTH;
        bounds.height = HEIGHT;

        Arrays.fill(texture, new TextureRegion(Vars.ERROR_TEXTURE));
    }

    @Override
    public void draw(SpriteBatch batch, Tile tile) {
        batch.draw(texture[tile.getVariant()],
                tile.getPosition().x * Vars.TILE_SIZE,
                tile.getPosition().y * Vars.TILE_SIZE,
                WIDTH * Vars.TILE_SIZE,
                HEIGHT * Vars.TILE_SIZE);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public String getName() {
        return name;
    }

    public TextureRegion[] getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion[] texture) {
        this.texture = texture;
    }
}
