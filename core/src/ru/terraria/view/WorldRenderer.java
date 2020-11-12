package ru.terraria.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

    private Stage stage;
    private SpriteBar healthBar;
    private ItemPanel inventory;
    private ItemSlots fastSlotBar;

    private ShapeRenderer renderer;

    private TextureRegion playerTexture = TextureRegion.split(new Texture("atlas.png"), Vars.TILE_SIZE, Vars.TILE_SIZE)[0][6];

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

        healthBar = new SpriteBar(0,100, new Texture("health.png"));
        healthBar.setScale(1.5f);
        healthBar.setInverseDraw(true);
        stage.addActor(healthBar);

        inventory = new ItemPanel(10, 6, new Texture("slot.png"));
        fastSlotBar = new ItemSlots(10, new Texture("slot.png"));
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
    }

    public void render(float deltaTime) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(1, 1, 1, 1f);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawWorld(batch);
        batch.end();

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
                batch.draw(arr[i].getWall().getTexture()[arr[i].getVariant()],
                        arr[i].getPosition().x * Vars.TILE_SIZE,
                        arr[i].getPosition().y * Vars.TILE_SIZE,
                        arr[i].getWall().getWIDTH() * Vars.TILE_SIZE,
                        arr[i].getWall().getHEIGHT() * Vars.TILE_SIZE);
            }

            if (arr[i].getBlock() != null && arr[i].getBlock() != Blocks.air) {
                batch.draw(arr[i].getBlock().getTexture()[arr[i].getVariant()],
                        arr[i].getPosition().x * Vars.TILE_SIZE,
                        arr[i].getPosition().y * Vars.TILE_SIZE,
                        arr[i].getBlock().getWIDTH() * Vars.TILE_SIZE,
                        arr[i].getBlock().getHEIGHT() * Vars.TILE_SIZE);
            }
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
}
