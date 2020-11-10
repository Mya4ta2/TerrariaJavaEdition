package ru.terraria.content;

import ru.terraria.ctype.ContentList;
import ru.terraria.type.Block;
import ru.terraria.type.Wall;

public class Walls implements ContentList {
    public static Wall air, dirt, stone;

    @Override
    public void load() {
        air = new Wall("air") {
            {
                setHEIGHT(1);
                setWIDTH(1);
            }
        };

        stone = new Wall("stoneWall") {
            {
                setHEIGHT(1);
                setWIDTH(1);
            }
        };

        dirt = new Wall("dirtWall") {
            {
                setHEIGHT(1);
                setWIDTH(1);
            }
        };
    }
}
