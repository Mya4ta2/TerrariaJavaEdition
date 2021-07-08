package ru.terraria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import ru.terraria.type.rounding.RoundingAtlas;

public class Atlas {
    public Texture errorTexture;

    public Texture find(String name) {
        name = name + ".png";

        if (!Gdx.files.internal(name).exists())
            return errorTexture;

        return new Texture(name);
    }
}
