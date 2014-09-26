package pl.subfty.ld25.game;

import pl.subfty.ld25.LD25;
import pl.subfty.sub.vision.Art.SPRITES;
import pl.subfty.sub.vision.actors.PointLightAccessor;
import pl.subfty.sub.vision.actors.SpriteActor;
import pl.subfty.sub.vision.stage.actors.ActorAccessor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Back;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Poo extends Group{
	public static float STINK_DIAMETER = 5f;
	
	private SpriteActor shit,
						stinkRadius;
	public PointLight pl;
	
	public Poo(RayHandler rH){
		shit = new SpriteActor(SPRITES.POO, 1);
		stinkRadius = new SpriteActor(SPRITES.STINK, STINK_DIAMETER);
		shit.x = -shit.width/2;
		shit.y = -shit.height/2;
		
		stinkRadius.x = -stinkRadius.width/2;
		stinkRadius.y = -stinkRadius.height/2;
		stinkRadius.scaleX = stinkRadius.scaleY = 0;
		stinkRadius.color.a = 0.0f;
		stinkRadius.originX = 0; 
		stinkRadius.originY = 0;
		
		pl = new PointLight(rH, 200, new Color(0, 1, 0, 0.0f), 5, 
							0, 0);
		pl.setSoft(false);
		pl.setContactFilter((short)1, (short)1, (short)2);
		
		this.addActor(stinkRadius);
		this.addActor(shit);
		
		Timeline.createParallel()
				.push(Tween.to(stinkRadius, ActorAccessor.SCALE_XY, 1.5f)
						   .ease(Back.OUT)
						   .target(1,1))
			   .push(Tween.to(stinkRadius, ActorAccessor.ALPHA, 2f)
						   .ease(Back.OUT)
						   .target(0.25f))
				.push(Tween.to(pl, PointLightAccessor.ALPHA, 6)
						   .ease(Back.OUT)
						   .target(0.3f)
						   .delay(1))
						   .start(LD25.tM);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		pl.setActive(true);
		pl.setPosition(this.x, this.y);
		
		stinkRadius.x = -stinkRadius.width/2*stinkRadius.scaleX;
		stinkRadius.y = -stinkRadius.height/2*stinkRadius.scaleY;
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}
}
