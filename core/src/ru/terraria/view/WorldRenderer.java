package ru.terraria.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.w3c.dom.css.Rect;
import ru.terraria.Cursor;
import ru.terraria.Vars;
import ru.terraria.content.Blocks;
import ru.terraria.content.Items;
import ru.terraria.gameui.*;
import ru.terraria.screen.GameScreen;
import ru.terraria.type.ItemStack;
import ru.terraria.type.Tile;
import ru.terraria.type.Tiles;
import ru.terraria.type.World;

import java.util.Arrays;

public class WorldRenderer {
    private World world;
    private GameScreen screen;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ScreenViewport viewport;

    public ShapeRenderer renderer;

    private TextureRegion playerTexture = new TextureRegion(new Texture("sprite/mob/player.png"));

    public WorldRenderer(World world, GameScreen screen) {
        this.world = world;
        this.screen = screen;

        camera = new OrthographicCamera(Vars.CAMERA_WIDTH, Vars.CAMERA_HEIGHT);
        viewport = new ScreenViewport(camera);
        batch = new SpriteBatch();

        renderer = new ShapeRenderer();
    }

    Texture bg = new Texture("sprite\\background\\Background_0.png");

    public void render(float deltaTime) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(1, 1, 1, 1f);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(
                bg,-Vars.CAMERA_WIDTH,
                (world.getHeight()/3 - 30) * Vars.TILE_SIZE,
                world.getWidth() * Vars.TILE_SIZE + Vars.CAMERA_WIDTH * 2,
                bg.getHeight()
        );
        drawWorld(batch);
        batch.end();

        drawCursorHitbox();

        //debug
        //drawHitBoxes();
        //

        viewport.apply();
        camera.update();
        camera.position.set(
                world.getPlayer().getPosition().x * Vars.TILE_SIZE,
                world.getPlayer().getPosition().y * Vars.TILE_SIZE, 0);
    }

    public void resize(int width, int height) {
        viewport.update(width,height);

        Vars.CAMERA_WIDTH = Gdx.graphics.getWidth();
        Vars.CAMERA_HEIGHT = Gdx.graphics.getHeight();
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

        renderer.setColor(Color.RED);
        renderer.rect(
                world.getPlayer().getGroundHitBox().x * Vars.TILE_SIZE,
                world.getPlayer().getGroundHitBox().y * Vars.TILE_SIZE,
                world.getPlayer().getGroundHitBox().width * Vars.TILE_SIZE,
                world.getPlayer().getGroundHitBox().height * Vars.TILE_SIZE);

        renderer.end();
    }

    public void drawCursorHitbox() {
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.YELLOW);
        int cX = (int)(Gdx.input.getX() + (camera.position.x - Gdx.graphics.getWidth() / 2));
        int cY = (int)(Vars.CAMERA_HEIGHT - Gdx.input.getY() + (camera.position.y - Gdx.graphics.getHeight() / 2));
        renderer.rect(
                (int) (cX / 16) * 16,
                (int) (cY / 16) * 16,
                Vars.TILE_SIZE,
                Vars.TILE_SIZE);
        renderer.end();
    }

    public void drawWorld(SpriteBatch batch) {

        Tiles tiles = world.getTiles();

        for (int i = (int) (-camera.viewportHeight / Vars.TILE_SIZE); i < camera.viewportHeight / Vars.TILE_SIZE; i++) {
            for (int j = (int) (-camera.viewportWidth / Vars.TILE_SIZE); j < camera.viewportWidth / Vars.TILE_SIZE; j++) {
                if (i + world.getPlayer().getPosition().y > 0 && i + world.getPlayer().getPosition().y < world.getHeight() &&
                    j + world.getPlayer().getPosition().x > 0 && j + world.getPlayer().getPosition().x < world.getWidth()) {
                    tiles.get(
                            (int) (i + world.getPlayer().getPosition().y),
                            (int) (j + world.getPlayer().getPosition().x)).draw(batch);
                }
            }
        }

        batch.draw(playerTexture,
                world.getPlayer().getPosition().x * Vars.TILE_SIZE,
                world.getPlayer().getPosition().y * Vars.TILE_SIZE,
                world.getPlayer().getWIDTH() * Vars.TILE_SIZE,
                world.getPlayer().getHEIGHT() * Vars.TILE_SIZE);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Rectangle getCameraBounds() {
        Rectangle rect = new Rectangle();
        rect.x = (camera.position.x - camera.viewportWidth/2) / Vars.TILE_SIZE;
        rect.y = (camera.position.y - camera.viewportHeight/2) / Vars.TILE_SIZE;
        rect.width = camera.viewportWidth / Vars.TILE_SIZE;
        rect.height = camera.viewportHeight / Vars.TILE_SIZE;
        return rect;
    }
}
