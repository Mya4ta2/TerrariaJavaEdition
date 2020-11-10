package ru.terraria.type;

import ru.terraria.Vars;
import ru.terraria.content.Items;

public class ItemStack {
    private Item[] items = new Item[Vars.STACK_SIZE];
    private Item itemType = Items.air;
    private int itemsContains;

    public void checkItemsContains() {

        itemsContains = 0;

        for (int i = 0; i < items.length; i++) {
            if (items[i] != Items.air) {
                itemsContains += 1;
            }
        }
    }

    public Item[] getItems() {
        checkItemsContains();
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
        checkItemsContains();
    }

    public Item getItemType() {
        checkItemsContains();
        return itemType;
    }

    public void setItemType(Item itemType) {
        checkItemsContains();
        this.itemType = itemType;
    }

    public int getItemsContains() {
        checkItemsContains();
        return itemsContains;
    }

    public void setItemsContains(int itemsContains) {
        this.itemsContains = itemsContains;
    }
}
