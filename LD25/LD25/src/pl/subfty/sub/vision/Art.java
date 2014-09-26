package pl.subfty.sub.vision;

import pl.subfty.sub.audio.SSound;
import pl.subfty.sub.interfaces.Callback;
import pl.subfty.sub.vision.fonts.BFonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Logger;

public class Art{
	private static Logger l = new Logger("Art", Logger.DEBUG);
	

	public static SSound sound;
	
	public static enum ATLASES{
		LEVEL1("textures/level1/level1"),
		UNIVERSAL("textures/universal/universal");
		
		public final String patch;
		ATLASES(String patch){
			this.patch = patch;
		}
	}

  //SPRITES
	public static enum SPRITES{
	  //LEVEL1
		HUMAN1("human1", ATLASES.LEVEL1),
		HUMAN2("human2", ATLASES.LEVEL1),
		HUMAN3("human3", ATLASES.LEVEL1),
		HUMAN4("human4", ATLASES.LEVEL1),
		LEVEL1BG("level1bg", ATLASES.LEVEL1),
		LEVEL1FG("level1fg", ATLASES.LEVEL1),
		
	  //UNIVERSAL
		CAT("cat", ATLASES.UNIVERSAL),
		POO("poo", ATLASES.UNIVERSAL),
		LOVE("love", ATLASES.UNIVERSAL),
		STINK("stink", ATLASES.UNIVERSAL),
		WAYPOINT("waypoint", ATLASES.UNIVERSAL),
		POO_METER("pooMeter", ATLASES.UNIVERSAL),
		POO_PROGRES("pooProgress", ATLASES.UNIVERSAL),
		GAME_OVER("gameOver", ATLASES.UNIVERSAL),
		INTRO("intro", ATLASES.UNIVERSAL),
		INTRO2("intro2", ATLASES.UNIVERSAL),
		BLOCK("block", ATLASES.UNIVERSAL),
		VICTORY("victory", ATLASES.UNIVERSAL),
		PBG("pbg", ATLASES.UNIVERSAL);
		
		public final ATLASES atlas;
		public final String name;
		SPRITES(String name, ATLASES atlas){
			this.name = name;
			this.atlas = atlas;
		}
	};
		
	public static enum FONTS{
		DEBUG("fonts/debug.fnt");
		
		public final String patch;
		FONTS(String patch){
			this.patch = patch;
		}
	}
	
	public static TextureAtlas atlases[];
	public static BFonts fonts[];
	
	public static void init(){
		atlases = new TextureAtlas[ATLASES.values().length];
		fonts = new BFonts[FONTS.values().length];		
		sound = new SSound();
	}

	public static void loadFont(FONTS font, TextureFilter f, boolean fixedWidthForNumbers){
		if(fonts[font.ordinal()] == null){
			l.info("    loading "+font);
			fonts[font.ordinal()] = new BFonts(Gdx.files.internal(font.patch), false);
			fonts[font.ordinal()].getRegion()
								 .getTexture()
								 .setFilter(f,f);
			fonts[font.ordinal()].setUseIntegerPositions(false);
			if(fixedWidthForNumbers)
				fonts[font.ordinal()].setFixedWidthGlyphs("0123456789");
		}
	}
	public static void loadFont(FONTS font, TextureFilter f, boolean fixedWidthForNumbers, Callback c){
		
	}
	public static boolean isFontLoaded(FONTS font){
		return fonts[font.ordinal()] != null;
	}
	public static void disposeFont(FONTS font){
		if(fonts[font.ordinal()] != null){
			fonts[font.ordinal()].dispose();
			fonts[font.ordinal()] = null;
		}
	}
	
	public static void loadAtlas(ATLASES atlas){
		if(atlases[atlas.ordinal()] == null){
			l.info("    loading "+atlas);
			atlases[atlas.ordinal()] = new TextureAtlas(Gdx.files.internal(atlas.patch));
		}
	}
	public static void loadAtlas(ATLASES atlas, Callback c){
		
	}
	public static boolean isAtlasLoaded(ATLASES atlas){
		return atlases[atlas.ordinal()] != null;
	}
	public static void disposeAtlas(ATLASES atlas){
		if(atlases[atlas.ordinal()] != null){
			atlases[atlas.ordinal()].dispose();
			atlases[atlas.ordinal()] = null;
		}
	}
}
