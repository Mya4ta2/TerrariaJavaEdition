package ru.terraria.content;

import ru.terraria.ctype.ContentList;
import ru.terraria.type.Block;

public class Blocks implements ContentList {

    public static Block air;

    @Override
    public void load() {
        air = new Block("air") {
            {

            }
        };
    }
}
