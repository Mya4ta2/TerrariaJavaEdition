package ru.terraria.type;

import com.badlogic.gdx.utils.Array;
import ru.terraria.Vars;
import ru.terraria.type.rounding.RoundingAtlas;

public class RoundingBlock extends Block {
    public RoundingBlock(String name) {
        super(name);
    }

    public RoundingAtlas getRoundingAtlas() {
        RoundingAtlas roundingAtlas = new RoundingAtlas();
        Vars.atlas.fillRoundingAtlas(roundingAtlas, getRoundingType(), getName());
        return roundingAtlas;
    }

    public RoundingType getRoundingType() {
        return new RoundingType();
    }

    public static class RoundingType {
        public final RoundingAtlas roundingAtlas;
        public Array<String> names = new Array<>();

        public RoundingType() {
            roundingAtlas = new RoundingAtlas();
            names.add("up");
        }
    }
}
