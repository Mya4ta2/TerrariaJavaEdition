package ru.terraria.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ru.terraria.Vars;
import ru.terraria.content.Blocks;
import ru.terraria.content.Walls;
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

    private ShapeRenderer renderer;

    private HashMap<String, TextureRegion> textures = new HashMap<>();

    public WorldRenderer(World world) {
        this.world = world;

        camera = new OrthographicCamera(Vars.CAMERA_WIDTH, Vars.CAMERA_HEIGHT);
        //camera.position.set(1024,100 * 16,0);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        viewport = new ScreenViewport(camera);

        renderer = new ShapeRenderer();

        loadTextures();
    }

    public void loadTextures() {
        Texture texture = new Texture("atlas.png");
        TextureRegion[][] textureRegions = TextureRegion.split(texture, Vars.TILE_SIZE, Vars.TILE_SIZE);

        textures.put("player", textureRegions[0][1]);
        textures.put("dirt", textureRegions[0][0]);
        textures.put("dirtWall", textureRegions[1][0]);
    }

    public void render(float deltaTime) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(1, 1, 1, 1f);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawWorld(batch);
        batch.end();

        //debug
        drawHitBoxes();
        //

        viewport.apply();
        camera.update();
        camera.position.set(
                world.getPlayer().getPosition().x * Vars.TILE_SIZE,
                world.getPlayer().getPosition().y * Vars.TILE_SIZE, 0);
    }

    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    public void drawHitBoxes() {
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < world.getTiles().getArray().length; i++) {
            if (world.getTiles().getArray()[i].getBlock() != Blocks.air) {
                renderer.rect(
                        world.getTiles().getArray()[i].getPosition().x * Vars.TILE_SIZE,
                        world.getTiles().getArray()[i].getPosition().y * Vars.TILE_SIZE,
                        world.getTiles().getArray()[i].getBounds().width * Vars.TILE_SIZE,
                        world.getTiles().getArray()[i].getBounds().height * Vars.TILE_SIZE);
            }
        }

        renderer.rect(
                world.getPlayer().getPosition().x * Vars.TILE_SIZE,
                world.getPlayer().getPosition().y * Vars.TILE_SIZE,
                world.getPlayer().getBounds().width * Vars.TILE_SIZE,
                world.getPlayer().getBounds().height * Vars.TILE_SIZE);

        renderer.end();
        renderer.setColor(Color.RED);
    }

    public void drawWorld(SpriteBatch batch) {

        Tile[] arr = world.getTiles().getArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getWall() != null && arr[i].getWall() != Walls.air) {
                batch.draw(textures.get(arr[i].getWall().getName()),
                        arr[i].getPosition().x * Vars.TILE_SIZE,
                        arr[i].getPosition().y * Vars.TILE_SIZE,
                        arr[i].getWall().getWIDTH() * Vars.TILE_SIZE,
                        arr[i].getWall().getHEIGHT() * Vars.TILE_SIZE);
            }

            if (arr[i].getBlock() != null && arr[i].getBlock() != Blocks.air) {
                batch.draw(textures.get(arr[i].getBlock().getName()),
                        arr[i].getPosition().x * Vars.TILE_SIZE,
                        arr[i].getPosition().y * Vars.TILE_SIZE,
                        arr[i].getBlock().getWIDTH() * Vars.TILE_SIZE,
                        arr[i].getBlock().getHEIGHT() * Vars.TILE_SIZE);
            }
        }

        batch.draw(textures.get("player"),
                world.getPlayer().getPosition().x * Vars.TILE_SIZE,
                world.getPlayer().getPosition().y * Vars.TILE_SIZE,
                world.getPlayer().getWIDTH() * Vars.TILE_SIZE,
                world.getPlayer().getHEIGHT() * Vars.TILE_SIZE);
    }
}
