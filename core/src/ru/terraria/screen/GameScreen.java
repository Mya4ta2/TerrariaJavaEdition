package ru.terraria.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import ru.terraria.controller.WorldController;
import ru.terraria.type.Tile;
import ru.terraria.type.World;
import ru.terraria.view.WorldRenderer;

public class GameScreen implements Screen {

    private Game game;
    private World world;
    private WorldController controller;
    private WorldRenderer renderer;

    //public Tile downTile; debug

    public boolean inventory;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        world = new World(500, 50); // test world
        controller = new WorldController(world, this);
        renderer = new WorldRenderer(world, this);

        Gdx.input.setInputProcessor(new InputMultiplexer(controller, renderer.getStage()));
    }

    @Override
    public void render(float delta) {
        renderer.render(delta);
        controller.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
