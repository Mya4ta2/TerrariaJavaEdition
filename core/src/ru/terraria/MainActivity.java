package ru.terraria;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.terraria.content.Blocks;
import ru.terraria.content.Items;
import ru.terraria.content.Walls;
import ru.terraria.screen.GameScreen;

public class MainActivity extends Game {

    @Override
    public void create() {
        new Walls().load();
        new Items().load();
        new Blocks().load();
        loadTextures();

        GameScreen gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    public void loadTextures() {
        Texture texture = new Texture("atlas.png");
        TextureRegion[][] textureRegions = TextureRegion.split(texture, Vars.TILE_SIZE, Vars.TILE_SIZE);

        /*TextureRegion[] dirt = {
                textureRegions[0][0],
                textureRegions[1][0],
                textureRegions[2][0],
        };

        TextureRegion[][] dirts = {
                dirt,
                dirt,
                dirt
        };

        TextureRegion[] grass = {
                textureRegions[0][1],
                textureRegions[1][1],
                textureRegions[2][1],
        };

        TextureRegion[][] grasses = {
                grass,
                grass,
                grass
        };

        TextureRegion[] stone = {
                textureRegions[0][2],
                textureRegions[1][2],
                textureRegions[2][2],
        };

        TextureRegion[][] stones = {
                stone,
                stone,
                stone
        };

        TextureRegion[] ironOre = {
                textureRegions[0][3],
                textureRegions[1][3],
                textureRegions[2][3],
        };

        TextureRegion[][] ironOres = {
                ironOre,
                ironOre,
                ironOre
        };

        TextureRegion[] dirtWall = {
                textureRegions[0][5],
                textureRegions[1][5],
                textureRegions[2][5],
        };

        TextureRegion[][] dirtWalls = {
                dirtWall,
                dirtWall,
                dirtWall
        };

        TextureRegion[] stoneWall = {
                textureRegions[0][4],
                textureRegions[1][4],
                textureRegions[2][4],
        };

        Blocks.dirt.setTexture(dirts);
        Blocks.grass.setTexture(grasses);
        Blocks.stone.setTexture(stones);
        Blocks.ironOre.setTexture(ironOres);

        Walls.dirt.setTexture(dirtWall);
        Walls.stone.setTexture(stoneWall);

        Items.test.setTexture(dirt[1]);*/

        TextureRegion[] dirt1 = new TextureRegion[textureRegions.length * textureRegions.length];

        dirt1[0] = textureRegions[0][0];
        dirt1[1] = textureRegions[0][1];
        dirt1[2] = textureRegions[0][2];

        dirt1[3] = textureRegions[1][0];
        dirt1[4] = textureRegions[1][1];
        dirt1[5] = textureRegions[1][2];

        dirt1[6] = textureRegions[2][0];
        dirt1[7] = textureRegions[2][1];
        dirt1[8] = textureRegions[2][2];

        dirt1[9] = textureRegions[0][3];


        TextureRegion[][] dirts = {
                dirt1,
                dirt1,
                dirt1,
        };

        Blocks.dirt.setTexture(dirts);

    }
}
