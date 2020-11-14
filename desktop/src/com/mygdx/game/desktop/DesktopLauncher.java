package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.terraria.MainActivity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MainActivity(), config){
			{
				config.width = 1250;
				config.height = 720;
				config.title = "Terraria Java Edition";
				config.addIcon("sprite/ui/icon.png", Files.FileType.Internal);
			}
		};
	}
}
