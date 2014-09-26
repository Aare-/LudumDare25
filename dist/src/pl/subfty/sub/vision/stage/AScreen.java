package pl.subfty.sub.vision.stage;

import pl.subfty.sub.interfaces.Callback;
import pl.subfty.sub.vision.Art;
import pl.subfty.sub.vision.Art.ATLASES;
import pl.subfty.sub.vision.Art.FONTS;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Logger;

public abstract class AScreen implements Screen,
										 Callback,
										 InputProcessor{
	private Logger l = new Logger("AScreen", Logger.DEBUG);
	
	public Group cntnt;
	
	/*protected String requiredTextures[],
					 requiredMusic[],
					 requiredSounds[];
	protected boolean loadTexture[],
					  unloadTexture[],
					  loadMusic[],
					  unloadMusic[],
					  loadSound[],
					  unloadSound[];*/			  
	
	public AScreen(){
		cntnt = null;
	}
	
	public void dispose(){}
	public void resize(int w, int h){}
	
  //LOADING SCREEN ASSETS
	public void loadScreen(){
		l.info("loading atlases: ");
		for(ATLASES a : requiredAtlases())
			Art.loadAtlas(a);
		l.info("loading fonts:");
		for(FONTS f : requiredFonts())
			Art.loadFont(f, TextureFilter.Linear, false);
		l.info("screen loaded!\n\n");
	}
	public boolean isScreenLoaded(){
		for(ATLASES a : requiredAtlases())
			if(!Art.isAtlasLoaded(a))
				return false;
		for(FONTS f : requiredFonts())
			if(!Art.isFontLoaded(f))
				return false;
		return true;
	}
	protected abstract ATLASES[] requiredAtlases();
	protected abstract FONTS[] requiredFonts();

  //INPUT PROCESSOR
	public boolean keyDown(int keycode) {
		return false;
	}
	public boolean keyUp(int keycode) {
		return false;
	}
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return true;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return true;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
