package ru.terraria;

import com.badlogic.gdx.Game;
import ru.terraria.screen.GameScreen;

public class MainActivity extends Game {
    @Override
    public void create() {
        GameScreen gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
