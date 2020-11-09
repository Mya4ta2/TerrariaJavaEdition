package ru.terraria.content;

import ru.terraria.ctype.ContentList;
import ru.terraria.type.Block;

public class Blocks implements ContentList {

    public static Block air, dirt;

    @Override
    public void load() {
        air = new Block("air") {
            {
                setHEIGHT(1);
                setWIDTH(1);
            }
        };

        dirt = new Block("dirt") {
            {
                setHEIGHT(1);
                setWIDTH(1);
            }
        };

    }
}
