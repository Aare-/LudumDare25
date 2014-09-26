package pl.subfty.ld25;

import com.badlogic.gdx.backends.lwjgl.LwjglApplet;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class MainApplet extends LwjglApplet
{
    private static final long serialVersionUID = 1L;
    public MainApplet()
    {
    	
//    	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
//		cfg.title = "LD25";
//		cfg.resizable=false;
//		cfg.useGL20 = false;
//		cfg.width  = (int)(500*1.2f);
//		cfg.height = (int)(300*1.2f);
//		
//		new LwjglApplication(new LD25(), cfg);
        super(new LD25(), false);
    }
}