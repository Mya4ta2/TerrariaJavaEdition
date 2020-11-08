package ru.terraria.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ru.terraria.Vars;
import ru.terraria.content.Blocks;
import ru.terraria.ctype.MappableContent;
import ru.terraria.type.Tile;
import ru.terraria.type.Tiles;
import ru.terraria.type.World;

import java.util.HashMap;

public class WorldRenderer {
    private World world;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ScreenViewport viewport;

    private HashMap<String, TextureRegion> textures = new HashMap<>();

    public WorldRenderer(World world) {
        this.world = world;

        camera = new OrthographicCamera(Vars.CAMERA_WIDTH, Vars.CAMERA_HEIGHT);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        viewport = new ScreenViewport(camera);

        loadTextures();
    }

    public void loadTextures() {
        Texture texture = new Texture("at1.png");
        TextureRegion[][] textureRegions = TextureRegion.split(texture, texture.getWidth() / 4, texture.getHeight() / 4);

        textures.put("player", textureRegions[0][0]);
        textures.put("air", textureRegions[1][0]);
    }

    public void render(float deltaTime) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(1, 1, 1, 1f);

        batch.begin();
        drawWorld(batch);
        batch.end();

        camera.update();
        viewport.apply();
    }

    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    public void drawWorld(SpriteBatch batch) {

        Tile[] arr = world.getTiles().getArray();

        for (int i = 0; i < arr.length; i++) {
            batch.draw(textures.get("air"),
                1,
                1);
        }
    }
}
