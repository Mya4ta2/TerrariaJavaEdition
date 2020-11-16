package ru.terraria.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.w3c.dom.Text;
import ru.terraria.Vars;
import ru.terraria.content.Blocks;

public class Item {
    private final String name;

    private boolean placeble;
    private Block placebleBlock;

    private TextureRegion texture = new TextureRegion(Vars.ERROR_TEXTURE);

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

    public boolean isPlaceble() {
        return placeble;
    }

    public Block getPlacebleBlock() {
        return placebleBlock;
    }

    public void setPlaceble(boolean placeble) {
        this.placeble = placeble;
    }

    public void setPlacebleBlock(Block placebleBlock) {
        this.placebleBlock = placebleBlock;
    }
}
