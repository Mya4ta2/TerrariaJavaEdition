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
        Vars.ui = new UI();
        Vars.atlas = new Atlas();
        Vars.atlas.errorTexture = new Texture("sprite/error.png");

        new Blocks().load();
        new Walls().load();
        new Items().load();
        loadTextures();

        GameScreen gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    public void loadTextures() {
        Texture texture = new Texture("sprite/block/block_4.png");
        TextureRegion[][] textureRegions = TextureRegion.split(texture, Vars.TILE_SIZE, Vars.TILE_SIZE);

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

        Blocks.highGrass.setTexture(highGrass);
        Blocks.woodenPlank.setTexture(plank);

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
