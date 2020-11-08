package ru.terraria.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ru.terraria.Vars;
import ru.terraria.type.World;

public class WorldRenderer {
    private World world;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ScreenViewport viewport;

    public WorldRenderer(World world) {
        this.world = world;

        camera = new OrthographicCamera(Vars.CAMERA_WIDTH, Vars.CAMERA_HEIGHT);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        viewport = new ScreenViewport(camera);
    }

    public void render(float deltaTime) {
        batch.begin();
        batch.end();

        camera.update();
        viewport.apply();
    }

    public void resize(int width, int height) {
        viewport.update(width,height);
    }
}
