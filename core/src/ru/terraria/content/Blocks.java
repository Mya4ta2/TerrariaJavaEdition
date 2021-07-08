package ru.terraria.content;

import ru.terraria.ctype.ContentList;
import ru.terraria.type.Block;
import ru.terraria.type.HighGrass;
import ru.terraria.type.Ore;
import ru.terraria.type.RoundingBlock;

public class Blocks implements ContentList {

    public static Block air, earth, grass, stone, woodenPlank;
    //ore
    public static Ore ironOre;

    public static HighGrass highGrass;


    @Override
    public void load() {
        air = new Block("air") {
            {
                setHEIGHT(1);
                setWIDTH(1);
            }
        };

        grass = new RoundingBlock("grass") {
            {
                setWIDTH(1);
                setHEIGHT(1);
            }
        };

        woodenPlank = new Block("woodenPlank") {
            {
                setWIDTH(1);
                setHEIGHT(1);
            }
        };

        highGrass = new HighGrass("highGrass") {
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

        earth = new Block("dirt") {
            {
                setHEIGHT(1);
                setWIDTH(1);
            }
        };

        ironOre = new Ore("ironOre") {
            {
                setWIDTH(1);
                setHEIGHT(1);
                setSpawnRate(200);
            }
        };
    }
}
