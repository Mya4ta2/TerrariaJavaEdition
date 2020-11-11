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

        TextureRegion[] dirt = {
                textureRegions[0][0],
                textureRegions[0][0],
                textureRegions[0][0],
        };

        TextureRegion[] grass = {
                textureRegions[0][1],
                textureRegions[0][1],
                textureRegions[0][1],
        };

        TextureRegion[] stone = {
                textureRegions[0][2],
                textureRegions[0][2],
                textureRegions[0][2],
        };

        TextureRegion[] ironOre = {
                textureRegions[0][3],
                textureRegions[0][3],
                textureRegions[0][3],
        };

        TextureRegion[] dirtWall = {
                textureRegions[1][0],
                textureRegions[1][0],
                textureRegions[1][0],
        };

        TextureRegion[] stoneWall = {
                textureRegions[1][3],
                textureRegions[1][3],
                textureRegions[1][3],
        };

        Blocks.dirt.setTexture(dirt);
        Blocks.grass.setTexture(grass);
        Blocks.stone.setTexture(stone);
        Blocks.ironOre.setTexture(ironOre);

        Walls.dirt.setTexture(dirtWall);
        Walls.stone.setTexture(stoneWall);

        Items.test.setTexture(dirt[1]);
    }
}
