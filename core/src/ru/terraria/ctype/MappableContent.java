package ru.terraria.ctype;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.type.Tile;

public abstract class MappableContent {
    public abstract void draw(SpriteBatch batch, Tile tile);
}
