package pl.subfty.ld25;

import java.util.Random;

import pl.subfty.ld25.game.BadKittyGame;
import pl.subfty.sub.GameState;
import pl.subfty.sub.vision.Art;
import pl.subfty.sub.vision.actors.PointLightAccessor;
import pl.subfty.sub.vision.actors.SpriteActor;
import pl.subfty.sub.vision.fonts.TextActor;
import pl.subfty.sub.vision.stage.SStage;
import pl.subfty.sub.vision.stage.actors.ActorAccessor;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import box2dLight.PointLight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class LD25 extends Game {
  //GAME UTILS
	public static SStage stage;
	public static SpriteBatch batch;
	public static Random rand;
	public static GameState gst;
	public static TweenManager tM;
	
	public final static float STAGE_W=50,
							  STAGE_H=30,
							  ASPECT_RATIO = STAGE_W/STAGE_H;
	public static float SCREEN_WIDTH,
						SCREEN_HEIGHT,
						SCALE;
	public static Vector2 tmp = new Vector2(); 

	public static LD25 me;
	
  //SCREENS
	public static BadKittyGame bk;
	public static GameOver gver;
	public static Intro intr;
	
	@Override
	public void create() {		
		LD25.me = this;
		
		Art.init();
		rand = new Random();
		batch = new SpriteBatch();
		stage = new SStage(STAGE_W, 
						   STAGE_H, 
						   false, 
						   batch);
		gst = new GameState();
		
		tM = new TweenManager();
		Tween.registerAccessor(SpriteActor.class, new ActorAccessor());
		Tween.registerAccessor(TextActor.class, new ActorAccessor());
		Tween.registerAccessor(PointLight.class, new PointLightAccessor());
		
		stage.getRoot()
			 .originX =STAGE_W/2;
		stage.getRoot()
			 .originY =STAGE_H/2;
				
		bk = new BadKittyGame();
		intr = new Intro();
		gver = new GameOver();
		
		this.setScreen(intr);
		//this.setScreen(bk);
	}
	@Override
	public void resize(int w, int h) {
		float ratio = (float)w/(float)h;
		if(ratio > STAGE_W/STAGE_H)
			SCALE = STAGE_H/((float)h);
		else
			SCALE = STAGE_W/((float)w);
		
		Camera c = stage.getCamera(); 
		c.viewportWidth = SCREEN_WIDTH= w*SCALE;
		c.viewportHeight = SCREEN_HEIGHT = h*SCALE;
		c.position.set(STAGE_W/2,STAGE_H/2,0);
		
		super.resize(w, h);
	}

	@Override
	public void render() {		
		//sBatch.totalRenderCalls=0;
		float delta = Gdx.graphics.getRawDeltaTime();
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		tM.update(delta);
		
		if(getScreen() != null)
			getScreen().render(delta);

		BadKittyGame.rH
				.setCombinedMatrix(stage.getCamera()
										.combined);
		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void pause() {
		if(this.getScreen() != null)
			this.getScreen().pause();
		tM.pause();
		Art.sound.stopAllLooping();
		gst.save();
	}
	@Override
	public void resume() {
		if(this.getScreen() != null){
			this.getScreen().resume();
			stage.unfocusAll();
		}
		tM.resume();
		Art.sound.resumeAllLooping();
	}
	@Override
	public void dispose() {
		gst.save();
		if(this.getScreen() != null)
			this.getScreen().dispose();
		batch.dispose();
	}
	
  //HELPERS
	public static void toStageCoordinates(int x, int y, Vector2 out){
		//TODO: this
		//stage.toStageCoordinates(x, y, out);
	}
	public static float toStageUnitsY(int x){
		return x*SCALE;
	}
	public static float toStageCoordinates(int x){
		//TODO: and this
		//stage.toStageCoordinates(x, 0, tmp);
		return tmp.x;
	}
}
