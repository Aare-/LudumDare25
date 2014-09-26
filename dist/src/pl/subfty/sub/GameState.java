package pl.subfty.sub;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameState{
	private Preferences pref;
	private String testPref= "leshgg";
	
	public boolean blckInput=false;
	
	public GameState(){
		pref = Gdx.app.getPreferences(testPref+"data");
	}
	
	public void save(){	
		pref.flush();
	}
	
	public boolean isMuted(){
		return false;
	}
	public boolean areVibrationsEnabled(){
		return true;
	}
}
