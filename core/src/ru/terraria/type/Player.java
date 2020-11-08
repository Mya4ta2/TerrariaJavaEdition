package ru.terraria.type;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.terraria.ctype.MappableContent;

public class Player extends MappableContent {
    private final String name;

    private Vector2 position;
    private Rectangle bounds;

    public Player(String name) {
        this.position = Vector2.Zero;
        this.name = name;
    }

    public Player(String name, Vector2 position) {
        this.position = position;
        this.name = name;
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
}
