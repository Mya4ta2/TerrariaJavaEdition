package ru.terraria;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.w3c.dom.Text;
import ru.terraria.content.Blocks;
import ru.terraria.content.Items;
import ru.terraria.content.Walls;
import ru.terraria.screen.GameScreen;

public class MainActivity extends Game {

    @Override
    public void create() {
        new Blocks().load();
        new Walls().load();
        new Items().load();
        loadTextures();

        Vars.ui = new UI();

        GameScreen gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    public void loadTextures() {
        Texture texture = new Texture("sprite/block/block_0.png");
        TextureRegion[][] textureRegions = TextureRegion.split(texture, Vars.TILE_SIZE, Vars.TILE_SIZE);

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

        texture = new Texture("sprite/block/block_1.png");
        textureRegions = TextureRegion.split(texture, Vars.TILE_SIZE, Vars.TILE_SIZE);

        TextureRegion[] stone1 = new TextureRegion[textureRegions.length * textureRegions.length];

        stone1[0] = textureRegions[0][0];
        stone1[1] = textureRegions[0][1];
        stone1[2] = textureRegions[0][2];

        stone1[3] = textureRegions[1][0];
        stone1[4] = textureRegions[1][1];
        stone1[5] = textureRegions[1][2];

        stone1[6] = textureRegions[2][0];
        stone1[7] = textureRegions[2][1];
        stone1[8] = textureRegions[2][2];

        stone1[9] = textureRegions[0][3];

        texture = new Texture("sprite/block/block_2.png");
        textureRegions = TextureRegion.split(texture, Vars.TILE_SIZE, Vars.TILE_SIZE);

        TextureRegion[] grass1 = new TextureRegion[textureRegions.length * textureRegions.length];

        grass1[0] = textureRegions[0][0];
        grass1[1] = textureRegions[0][1];
        grass1[2] = textureRegions[0][2];

        grass1[3] = textureRegions[1][0];
        grass1[4] = textureRegions[1][1];
        grass1[5] = textureRegions[1][2];

        grass1[6] = textureRegions[2][0];
        grass1[7] = textureRegions[2][1];
        grass1[8] = textureRegions[2][2];

        grass1[9] = textureRegions[0][3];

        texture = new Texture("sprite/block/block_4.png");
        textureRegions = TextureRegion.split(texture, Vars.TILE_SIZE, Vars.TILE_SIZE);

        TextureRegion[] plank = new TextureRegion[textureRegions.length * textureRegions.length];

        plank[0] = textureRegions[0][0];
        plank[1] = textureRegions[0][1];
        plank[2] = textureRegions[0][2];

        plank[3] = textureRegions[1][0];
        plank[4] = textureRegions[1][1];
        plank[5] = textureRegions[1][2];

        plank[6] = textureRegions[2][0];
        plank[7] = textureRegions[2][1];
        plank[8] = textureRegions[2][2];

        plank[9] = textureRegions[0][3];

        texture = new Texture("sprite/block/block_3.png");
        textureRegions = TextureRegion.split(texture, Vars.TILE_SIZE, Vars.TILE_SIZE);

        TextureRegion[] highGrass = new TextureRegion[3];
        highGrass[0] = textureRegions[0][0];
        highGrass[1] = textureRegions[0][1];
        highGrass[2] = textureRegions[0][2];

        TextureRegion[][] dirts = {
                dirt1,
                dirt1,
                dirt1,
        };

        TextureRegion[][] stones = {
                stone1,
                stone1,
                grass1,
        };

        TextureRegion[][] grases = {
                grass1,
                grass1,
                grass1,
        };

        TextureRegion[][] planks = {
                plank,
                plank,
                plank,
        };

        Blocks.earth.setTexture(dirts);
        Blocks.stone.setTexture(stones);
        Blocks.grass.setTexture(grases);
        Blocks.highGrass.setTexture(highGrass);
        Blocks.woodenPlank.setTexture(planks);

        Items.copperSword.setTexture(new TextureRegion(new Texture("sprite\\items\\Item_0.png")));
        Items.copperPickaxe.setTexture(new TextureRegion(new Texture("sprite\\items\\Item_1.png")));
        Items.copperAxe.setTexture(new TextureRegion(new Texture("sprite\\items\\Item_2.png")));
        Items.woodenPlank.setTexture(new TextureRegion(new Texture("sprite\\items\\Item_3.png")));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Event.fire(Events.ResizeEvent.class);
    }
}
