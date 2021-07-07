package ru.terraria.gameui.Fragment;

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
import ru.terraria.Event;
import ru.terraria.Events;
import ru.terraria.Vars;
import ru.terraria.content.Items;
import ru.terraria.gameui.*;
import ru.terraria.type.ItemStack;

public class GameFragment extends Fragment{
    @Override
    public void build(Group group) {
        SpriteBar healthBar;
        ItemPanel inventory;
        final FastSlotBar fastSlotBar;
        AccessoryPanel accessory;
        TextButton settingsButton;
        final Table leftTable;
        final Table rightTable;
        rightTable = new Table();
        leftTable = new Table();

        healthBar = new SpriteBar(0,100, new Texture("sprite/ui/health.png"));

        inventory = new ItemPanel(10, 6,
                new Texture("sprite/ui/slot.png"),
                new Texture("sprite/ui/selected_slot.png"));
        fastSlotBar = new FastSlotBar(10,
                new Texture("sprite/ui/slot.png"),
                new Texture("sprite/ui/selected_slot.png"), Vars.uiRenderer.getFont());
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

        settingsButton = new TextButton(buttonTexture,pressedButtonTexture, Vars.uiRenderer.getFont());
        settingsButton.setText("Settings");
        settingsButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // oh no
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        Event.on(Events.ResizeEvent.class, new Runnable() {
            @Override
            public void run() {
                leftTable.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
                rightTable.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            }
        });

        fastSlotBar.setName("hotbar");

        leftTable.top().left().add(new Separator(40)).row();
        leftTable.top().left().add(fastSlotBar).row();
        leftTable.top().left().add(new Separator(45)).row();
        leftTable.top().left().add(inventory).row();
        rightTable.top().right().add(healthBar).row();
        rightTable.top().right().add(accessory).row();
        rightTable.top().right().add(settingsButton).row();

        group.addActor(leftTable);
        group.addActor(rightTable);
    }
}
