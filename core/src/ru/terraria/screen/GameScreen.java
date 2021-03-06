package ru.terraria.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Group;
import ru.terraria.Vars;
import ru.terraria.controller.WorldController;
import ru.terraria.type.World;
import ru.terraria.view.UIRenderer;
import ru.terraria.view.WorldRenderer;

public class GameScreen implements Screen {

    private Game game;
    private World world;
    private WorldController controller;
    private WorldRenderer renderer;
    private UIRenderer uiRenderer;

    public boolean inventory = true;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        world = new World(100, 250); // test world
        controller = new WorldController(world, this);
        renderer = new WorldRenderer(world, this);
        uiRenderer = new UIRenderer();
        uiRenderer.init();

        Group group = new Group();
        Vars.ui.gameFragment.build(group);
        uiRenderer.getStage().addActor(group);

        Gdx.input.setInputProcessor(new InputMultiplexer(controller, uiRenderer.getStage()));
    }

    @Override
    public void render(float delta) {
        renderer.render(delta);
        uiRenderer.render(delta);
        controller.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
        uiRenderer.resize(width,height);
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

    public World getWorld() {
        return world;
    }

    public WorldController getController() {
        return controller;
    }

    public WorldRenderer getRenderer() {
        return renderer;
    }

    public UIRenderer getUiRenderer() {
        return uiRenderer;
    }
}
