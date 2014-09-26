package pl.subfty.ld25;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "LD25";
		cfg.resizable=false;
		cfg.useGL20 = false;
		cfg.width  = (int)(500*1.2f);
		cfg.height = (int)(300*1.2f);
		
		new LwjglApplication(new LD25(), cfg);
	}
}
