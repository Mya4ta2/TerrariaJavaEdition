package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.terraria.MainActivity;
import jdk.javadoc.internal.tool.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MainActivity(), config){
			{
				config.width = 1020;
				config.height = 720;
			}
		};
	}
}
