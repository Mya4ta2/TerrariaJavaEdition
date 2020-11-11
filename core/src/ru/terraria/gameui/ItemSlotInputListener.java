package ru.terraria.gameui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import ru.terraria.content.Items;
import ru.terraria.type.ItemStack;

public class ItemSlotInputListener extends InputListener {

    private ItemSlot slot;

    public ItemSlotInputListener(ItemSlot slot) {
        this.slot = slot;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        slot.setItemStack(new ItemStack());
        System.out.println("oh");
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
    }
}
