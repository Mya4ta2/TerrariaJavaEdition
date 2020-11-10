package ru.terraria.content;

import ru.terraria.ctype.ContentList;
import ru.terraria.type.Block;

public class Blocks implements ContentList {

    public static Block air, dirt, grass, stone;

    @Override
    public void load() {
        air = new Block("air") {
            {
                setHEIGHT(1);
                setWIDTH(1);
            }
        };

        grass = new Block("grass") {
            {
                setWIDTH(1);
                setHEIGHT(1);
            }
        };

        stone = new Block("stone") {
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
