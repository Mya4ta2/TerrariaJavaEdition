package ru.terraria.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.w3c.dom.Text;

public class Item {
    private final String name;

    private TextureRegion texture;

    public Item(String name) {
        this.name = name;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }
}
