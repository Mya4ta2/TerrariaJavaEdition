package ru.terraria.type;

import ru.terraria.type.rounding.RoundingAtlas;

public class RoundingBlock extends Block {
    public RoundingBlock(String name) {
        super(name);
    }

    public RoundingAtlas getRoundingAtlas() {
        return new RoundingAtlas();
    }
}
