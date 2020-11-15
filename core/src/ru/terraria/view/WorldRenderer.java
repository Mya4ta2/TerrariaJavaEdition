package ru.terraria.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ru.terraria.Vars;
import ru.terraria.content.Blocks;
import ru.terraria.content.Items;
import ru.terraria.content.Walls;
import ru.terraria.gameui.ItemPanel;
import ru.terraria.gameui.ItemSlots;
import ru.terraria.gameui.SpriteBar;
import ru.terraria.screen.GameScreen;
import ru.terraria.type.ItemStack;
import ru.terraria.type.Tile;
import ru.terraria.type.World;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WorldRenderer {
    private World world;
    private GameScreen screen;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private OrthographicCamera UICamera;
    private ScreenViewport viewport;
    private ScreenViewport UIViewport;
    private BitmapFont font;

    private Stage stage;
    private SpriteBar healthBar;
    private ItemPanel inventory;
    private ItemSlots fastSlotBar;

    public ShapeRenderer renderer;

    private TextureRegion playerTexture = TextureRegion.split(new Texture("sprite/block/block_0.png"), Vars.TILE_SIZE, Vars.TILE_SIZE)[1][1];

    public WorldRenderer(World world, GameScreen screen) {
        this.world = world;
        this.screen = screen;

        camera = new OrthographicCamera(Vars.CAMERA_WIDTH, Vars.CAMERA_HEIGHT);
        UICamera = new OrthographicCamera(Vars.CAMERA_WIDTH, Vars.CAMERA_HEIGHT);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        viewport = new ScreenViewport(camera);
        UIViewport = new ScreenViewport(UICamera);
        stage = new Stage();
        stage.setViewport(UIViewport);

        healthBar = new SpriteBar(0,100, new Texture("sprite/ui/health.png"));
        healthBar.setInverseDraw(true);
        stage.addActor(healthBar);

        inventory = new ItemPanel(10, 6, new Texture("sprite/ui/slot.png"));
        fastSlotBar = new ItemSlots(10, new Texture("sprite/ui/slot.png"));
        ItemStack stack = new ItemStack();
        Arrays.fill(stack.getItems(), Items.test);
        stack.setItemType(Items.test);
        inventory.getSlots()[0][0].setItemStack(stack);
        inventory.getSlots()[1][0].setItemStack(stack);
        inventory.getSlots()[2][0].setItemStack(stack);
        fastSlotBar.getSlots()[0].setItemStack(stack);

        stage.addActor(inventory);
        stage.addActor(fastSlotBar);

        renderer = new ShapeRenderer();
        font = new BitmapFont();
    }

    public void render(float deltaTime) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(1, 1, 1, 1f);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawWorld(batch);
        font.setColor(Color.RED);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(),
                world.getPlayer().getPosition().x * Vars.TILE_SIZE,world.getPlayer().getPosition().y * Vars.TILE_SIZE);
        batch.end();

        drawCursorHitbox();

        stage.act();
        stage.draw();

        //debug
        //drawHitBoxes();
        //

        inventory.setVisible(screen.inventory);

        viewport.apply();
        camera.update();
        camera.position.set(
                world.getPlayer().getPosition().x * Vars.TILE_SIZE,
                world.getPlayer().getPosition().y * Vars.TILE_SIZE, 0);

        // update UI
        UICamera.update();
        UIViewport.apply();
        healthBar.setValue(world.getPlayer().getHealth() / 20);
    }

    public void resize(int width, int height) {
        healthBar.setPosition(width / 3f + width / 8f, height / 3f + height / 8f);
        fastSlotBar.setPosition(-width / 2f + 10, height / 3f + height / 9f - 10);
        inventory.setPosition(-width / 2f + 10, height / 3f + height / 22f - 10);
        viewport.update(width,height);
        UIViewport.update(width, height);

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

        Tile[] arr = world.getTiles().getArray();

        for (int i = 0; i < arr.length; i++) {
            arr[i].draw(batch);
        }

        batch.draw(playerTexture,
                world.getPlayer().getPosition().x * Vars.TILE_SIZE,
                world.getPlayer().getPosition().y * Vars.TILE_SIZE,
                world.getPlayer().getWIDTH() * Vars.TILE_SIZE,
                world.getPlayer().getHEIGHT() * Vars.TILE_SIZE);
    }

    public Stage getStage() {
        return stage;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
