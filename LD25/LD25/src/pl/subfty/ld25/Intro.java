package pl.subfty.ld25;

import pl.subfty.sub.vision.Art.ATLASES;
import pl.subfty.sub.vision.Art.FONTS;
import pl.subfty.sub.vision.Art.SPRITES;
import pl.subfty.sub.vision.SColor;
import pl.subfty.sub.vision.actors.SpriteActor;
import pl.subfty.sub.vision.stage.AScreen;
import pl.subfty.sub.vision.stage.actors.ActorAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.scenes.scene2d.Group;

public class Intro extends AScreen{

	private SColor clr = new SColor();
	private SpriteActor screenFiller,
						INTRO1,
						INTRO2,
						tmp = new SpriteActor();
	
	public Intro(){
		cntnt = new Group();
		
		screenFiller = new SpriteActor();
		INTRO1 = new SpriteActor();
		INTRO2 = new SpriteActor();
		
		cntnt.addActor(INTRO2);
		cntnt.addActor(INTRO1);
		cntnt.addActor(screenFiller);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		clr.set(1,1,1,1);
		clr.interp(0,0,0, tmp.x);
		
		screenFiller.color.set(clr.r,clr.g,clr.b,screenFiller.color.a);
		
	}

	@Override
	public void show() {
		if(!isScreenLoaded())
			loadScreen();
		
		screenFiller.setSprite(SPRITES.BLOCK, LD25.STAGE_W, LD25.STAGE_H);
		INTRO1.setSprite(SPRITES.INTRO, LD25.STAGE_W);
		INTRO2.setSprite(SPRITES.INTRO2, LD25.STAGE_W);
		
		LD25.stage.addActor(cntnt);
		
		screenFiller.color.set(1, 1, 1, 1);
		tmp.x = 0;
		
		Timeline.createSequence()
				.push(Tween.to(screenFiller, ActorAccessor.ALPHA, 2)
						.target(0)
						.delay(0.5f))
				.push(Tween.to(screenFiller, ActorAccessor.ALPHA, 1)
						 .target(1)
						 .delay(2))
				.push(Tween.to(tmp, ActorAccessor.POS_X, 1)
						   .target(1)
						   .setCallback(new TweenCallback() {
							@Override
							public void onEvent(int type, BaseTween<?> source) {
								INTRO1.visible=false;
							}
						}))
				.push(Tween.to(screenFiller, ActorAccessor.ALPHA, 1)
						.target(0))
				.push(Tween.to(screenFiller, ActorAccessor.ALPHA, 1)
						 .target(1)
						 .delay(2)
						 .setCallback(new TweenCallback() {
							@Override
							public void onEvent(int type, BaseTween<?> source) {
								LD25.me.setScreen(LD25.bk);
							}
						}))
				.start(LD25.tM);
						 
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		cntnt.remove();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void call(int type) {
		// TODO Auto-generated method stub
		
	}

	private final static ATLASES reqAtlases[] = {ATLASES.LEVEL1, 
			 									 ATLASES.UNIVERSAL};
	private final static FONTS reqFonts[] = {FONTS.DEBUG};
	@Override
	protected ATLASES[] requiredAtlases() {
		return reqAtlases;
	}
	@Override
	protected FONTS[] requiredFonts() {
		return reqFonts;
	}
}
