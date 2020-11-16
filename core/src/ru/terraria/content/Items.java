package ru.terraria.content;

import ru.terraria.ctype.ContentList;
import ru.terraria.type.Item;
import ru.terraria.type.Pickaxe;

public class Items implements ContentList {

    public static Item air, test;

    public static Pickaxe copperPickaxe;

    @Override
    public void load() {
        air = new Item("air") {
            {

            }
        };

        copperPickaxe = new Pickaxe("copper pickaxe") {
            {

            }
        };

        test = new Item("dirt") {
            {

            }
        };
    }
}
