package ru.terraria.type;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.Vars;
import ru.terraria.content.Blocks;
import ru.terraria.ctype.MappableContent;

import java.util.Arrays;

public class Block extends MappableContent {

    public enum NeighbourAir {
        LEFT_UP(0), UP(1), RIGHT_UP(2),
        LEFT(3), ALL(4), RIGHT(5),
        LEFT_DOWN(6), DOWN(7), RIGHT_DOWN(8),
        NONE(9);

        int a;
        NeighbourAir(int i) {
            a = i;
        }

        public int getNum() {
            return a;
        }
    }

    private final String name;

    private float WIDTH;
    private float HEIGHT;

    private int variants = 3;
    private TextureRegion[][] texture = new TextureRegion[variants][NeighbourAir.values().length];

    private Vector2 position = new Vector2();
    private Rectangle bounds = new Rectangle();

    public Block(String name) {
        this.name = name;
        bounds.width = WIDTH;
        bounds.height = HEIGHT;

        for (int i = 0; i < texture.length; i++) {
            TextureRegion[] region = {
                    new TextureRegion(Vars.ERROR_TEXTURE),
                    new TextureRegion(Vars.ERROR_TEXTURE),
                    new TextureRegion(Vars.ERROR_TEXTURE),
                    new TextureRegion(Vars.ERROR_TEXTURE),
                    new TextureRegion(Vars.ERROR_TEXTURE),
            };

            texture[i] = region;
        }
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

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setTexture(TextureRegion[][] texture) {
        this.texture = texture;
    }

    public TextureRegion[][] getTexture() {
        return texture;
    }
}
