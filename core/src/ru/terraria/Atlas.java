package ru.terraria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import ru.terraria.type.RoundingBlock;
import ru.terraria.type.rounding.RoundingAtlas;

public class Atlas {
    public Texture errorTexture;

    public Texture find(String name) {
        name = name + ".png";

        if (!Gdx.files.internal(name).exists())
            return errorTexture;

        return new Texture(name);
    }

    public void fillRoundingAtlas(RoundingAtlas roundingAtlas, RoundingBlock.RoundingType roundingType, String name, String dir) {
        for (int i = 0; i < roundingType.names.size; i++) {
            roundingAtlas.getTextures().put(
                    roundingType.names.get(i),
                    find(dir + name + "-" + roundingType.names.get(i))
            );
        }
    }
}
