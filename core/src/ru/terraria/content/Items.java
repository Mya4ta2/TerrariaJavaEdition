package ru.terraria.content;

import ru.terraria.ctype.ContentList;
import ru.terraria.type.Item;

public class Items implements ContentList {

    public static Item air, test;

    @Override
    public void load() {
        air = new Item("air") {
            {

            }
        };

        test = new Item("dirt") {
            {

            }
        };
    }
}
