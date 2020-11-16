package ru.terraria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Vars {
    public static float CAMERA_WIDTH = Gdx.graphics.getWidth();
    public static float CAMERA_HEIGHT = Gdx.graphics.getHeight();

    public static final int TILE_SIZE = 16;

    public static final int STACK_SIZE = 999;

    public static final Texture ERROR_TEXTURE = new Texture("sprite/error.png");

    public static final int SMALL_WORLD_HEIGHT = 1200;
    public static final int SMALL_WORLD_WIDTH = 2400;

    public static final int MEDIUM_WORLD_HEIGHT = 1800;
    public static final int MEDIUM_WORLD_WIDTH = 6400;

    public static final int BIG_WORLD_HEIGHT = 2400;
    public static final int BIG_WORLD_WIDTH = 8400;
}
