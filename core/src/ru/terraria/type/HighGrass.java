package ru.terraria.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.Vars;

import java.util.Arrays;

public class HighGrass extends Block {

    private TextureRegion[] texture;
    private int variants = 3;

    private float WIDTH;
    private float HEIGHT;

    public HighGrass(String name) {
        super(name);

        texture = new TextureRegion[variants];
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

    @Override
    public void setWIDTH(float WIDTH) {
        this.WIDTH = WIDTH;
    }

    @Override
    public void setHEIGHT(float HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public void setTexture(TextureRegion[] texture) {
        this.texture = texture;
    }
}
