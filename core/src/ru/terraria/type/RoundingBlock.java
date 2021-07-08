package ru.terraria.type;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import ru.terraria.Vars;
import ru.terraria.type.rounding.RoundingAtlas;

public class RoundingBlock extends Block {
    public RoundingBlock(String name) {
        super(name);
    }

    public RoundingAtlas getRoundingAtlas() {
        RoundingAtlas roundingAtlas = new RoundingAtlas();
        Vars.atlas.fillRoundingAtlas(roundingAtlas, getRoundingType(), getName(), "sprite/block/");
        return roundingAtlas;
    }

    public RoundingType getRoundingType() {
        return new RoundingType();
    }

    @Override
    public void draw(SpriteBatch batch, Tile tile) {
        batch.draw(tile.getBlockRounding().getCurrentTexture(),
                tile.getPosition().x * Vars.TILE_SIZE,
                tile.getPosition().y * Vars.TILE_SIZE,
                Vars.TILE_SIZE,
                Vars.TILE_SIZE);
    }

    public static class RoundingType {
        public final RoundingAtlas roundingAtlas;
        public Array<String> names = new Array<>();

        public RoundingType() {
            roundingAtlas = new RoundingAtlas();
            names.add("left");
            names.add("right");
            names.add("down");
            names.add("up");
            names.add("leftUp");
            names.add("rightUp");
            names.add("leftDown");
            names.add("rightDown");
            names.add("all");
            names.add("none");
        }
    }
}
