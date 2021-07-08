package ru.terraria.type.rounding;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class RoundingAtlas {
    private final HashMap<String, Texture> textures;

    public RoundingAtlas() {
        textures = new HashMap<>();
    }

    public HashMap<String, Texture> getTextures() {
        return textures;
    }
}
