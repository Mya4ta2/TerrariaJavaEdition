package ru.terraria.content;

import ru.terraria.ctype.ContentList;
import ru.terraria.type.Axe;
import ru.terraria.type.Item;
import ru.terraria.type.Pickaxe;
import ru.terraria.type.Sword;

public class Items implements ContentList {

    public static Item air, test;

    public static Pickaxe copperPickaxe;
    public static Sword copperSword;
    public static Axe copperAxe;

    @Override
    public void load() {
        air = new Item("air") {
            {
                setPlaceble(false);
            }
        };

        copperPickaxe = new Pickaxe("copper pickaxe") {
            {
                setPlaceble(false);
            }
        };

        copperAxe = new Axe("copper axe") {
            {
                setPlaceble(false);
            }
        };

        copperSword = new Sword("copper sword") {
            {
                setPlaceble(false);
            }
        };

        test = new Item("dirt") {
            {
                setPlaceble(true);
                setPlacebleBlock(Blocks.dirt);
            }
        };
    }
}
