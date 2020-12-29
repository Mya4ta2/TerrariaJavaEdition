package ru.terraria.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.terraria.Cursor;
import ru.terraria.content.Items;
import ru.terraria.gameui.*;
import ru.terraria.screen.GameScreen;
import ru.terraria.type.ItemStack;

public class UIRenderer {

    private final GameScreen gameScreen;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Stage stage;
    private SpriteBar healthBar;
    private ItemPanel inventory;
    private FastSlotBar fastSlotBar;
    private AccessoryPanel accessory;
    private TextButton settingsButton;
    private BitmapFont font;
    private SpriteBatch batch;
    private Table leftTable;
    private Table rightTable;

    public UIRenderer(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void init() {
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);

        rightTable = new Table();
        leftTable = new Table();

        font = new BitmapFont(Gdx.files.internal("font\\as.fnt"));
        font.setColor(Color.WHITE);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        stage = new Stage();
        stage.setViewport(viewport);

        healthBar = new SpriteBar(0,100, new Texture("sprite/ui/health.png"));

        inventory = new ItemPanel(10, 6,
                new Texture("sprite/ui/slot.png"),
                new Texture("sprite/ui/selected_slot.png"));
        fastSlotBar = new FastSlotBar(10,
                new Texture("sprite/ui/slot.png"),
                new Texture("sprite/ui/selected_slot.png"), font);
        ItemStack stack = new ItemStack();
        stack.setItem(Items.copperSword);
        stack.setItemsCount(1);
        ItemStack stack1 = new ItemStack();
        stack1.setItem(Items.copperPickaxe);
        stack1.setItemsCount(1);
        ItemStack stack2 = new ItemStack();
        stack2.setItem(Items.copperAxe);
        stack2.setItemsCount(1);
        ItemStack dirt = new ItemStack();
        dirt.setItem(Items.earth);
        dirt.setItemsCount(999);
        ItemStack plank = new ItemStack();
        plank.setItem(Items.woodenPlank);
        plank.setItemsCount(999);

        fastSlotBar.getSlots()[4].setItemStack(plank);
        fastSlotBar.getSlots()[3].setItemStack(dirt);
        fastSlotBar.getSlots()[2].setItemStack(stack2);
        fastSlotBar.getSlots()[1].setItemStack(stack1);
        fastSlotBar.getSlots()[0].setItemStack(stack);

        accessory = new AccessoryPanel(
                1,
                10,
                new Texture("sprite/ui/selected_slot.png"));

        Texture buttonTexture = new Texture("sprite\\ui\\clear_texture.png");
        Texture pressedButtonTexture = new Texture("sprite\\ui\\clear_texture.png");

        settingsButton = new TextButton(buttonTexture,pressedButtonTexture,font);
        settingsButton.setText("Settings");
        settingsButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // oh no
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        leftTable.top().left().add(new Separator(40)).row();
        leftTable.top().left().add(fastSlotBar).row();
        leftTable.top().left().add(new Separator(45)).row();
        leftTable.top().left().add(inventory).row();
        rightTable.top().right().add(healthBar).row();
        rightTable.top().right().add(accessory).row();
        rightTable.top().right().add(settingsButton).row();

        stage.addActor(leftTable);
        stage.addActor(rightTable);
    }

    public void render(float delta) {
        stage.act();
        stage.draw();

        Cursor.itemStack = fastSlotBar.getSlots()[fastSlotBar.getSelectedSlot()].getItemStack();

        inventory.setVisible(gameScreen.inventory);
        accessory.setVisible(gameScreen.inventory);
        settingsButton.setVisible(gameScreen.inventory);

        camera.update();
        viewport.apply();
        healthBar.setValue(gameScreen.getWorld().getPlayer().getHealth() / 20);
    }

    public void resize(int width, int height) {
        viewport.update(width,height);

        leftTable.setSize(width/2,height);
        leftTable.setPosition(0,0);
        rightTable.setSize(width/2,height);
        rightTable.setPosition(width/2, 0);

        camera.position.set(width / 2, height / 2,0); // set camera to screen center
    }

    public Stage getStage() {
        return stage;
    }

    public FastSlotBar getFastSlotBar() {
        return fastSlotBar;
    }
}
