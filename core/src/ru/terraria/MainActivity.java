package ru.terraria;

import com.badlogic.gdx.Game;
import ru.terraria.content.Blocks;
import ru.terraria.screen.GameScreen;

public class MainActivity extends Game {

    @Override
    public void create() {
        new Blocks().load();
        GameScreen gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }
}
