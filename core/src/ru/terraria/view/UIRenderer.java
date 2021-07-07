package ru.terraria.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.terraria.Cursor;
import ru.terraria.Vars;
import ru.terraria.content.Items;
import ru.terraria.gameui.*;
import ru.terraria.gameui.Fragment.Fragment;
import ru.terraria.screen.GameScreen;
import ru.terraria.type.ItemStack;

public class UIRenderer {
    private OrthographicCamera camera;
    private Viewport viewport;

    private Stage stage;
    private BitmapFont font;
    private SpriteBatch batch;

    public UIRenderer() {
        Vars.uiRenderer = this;
    }

    public void init() {
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);

        font = new BitmapFont(Gdx.files.internal("font\\as.fnt"));
        font.setColor(Color.WHITE);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        stage = new Stage();
        stage.setViewport(viewport);
    }

    public void render(float delta) {
        stage.act();
        stage.draw();

        camera.update();
        viewport.apply();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);

        camera.position.set(width / 2f, height / 2f,0); // set camera to screen center
    }

    public Stage getStage() {
        return stage;
    }

    public BitmapFont getFont() {
        return font;
    }
}
