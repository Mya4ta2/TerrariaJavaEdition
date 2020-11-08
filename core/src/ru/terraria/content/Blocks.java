package ru.terraria.content;

import ru.terraria.ctype.ContentList;

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
