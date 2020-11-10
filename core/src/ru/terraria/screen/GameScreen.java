package ru.terraria.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import ru.terraria.controller.WorldController;
import ru.terraria.type.World;
import ru.terraria.view.WorldRenderer;

public class GameScreen implements Screen {

    private Game game;
    private World world;
    private WorldController controller;
    private WorldRenderer renderer;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        world = new World(500, 500); // test world
        controller = new WorldController(world);
        renderer = new WorldRenderer(world);

        Gdx.input.setInputProcessor(controller);
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
