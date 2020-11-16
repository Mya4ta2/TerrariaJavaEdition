package ru.terraria.type;

import ru.terraria.Vars;
import ru.terraria.content.Items;

import java.util.Arrays;

public class ItemStack {
    private Item item = Items.air;
    private int itemsCount = 1;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }
}
