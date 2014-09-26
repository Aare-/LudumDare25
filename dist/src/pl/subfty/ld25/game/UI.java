package pl.subfty.ld25.game;

import pl.subfty.ld25.LD25;
import pl.subfty.ld25.game.BadKittyGame.LEVEL_DEF;
import pl.subfty.sub.vision.Art.FONTS;
import pl.subfty.sub.vision.Art.SPRITES;
import pl.subfty.sub.vision.actors.SpriteActor;
import pl.subfty.sub.vision.fonts.TextActor;
import pl.subfty.sub.vision.stage.actors.ActorAccessor;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Quad;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class UI extends Group{
	public static int POINTS_PER_POO = 1000;
	
	SpriteActor pooMeter,
				pooIndicator,
				pBG,
				pBG2;
	private TextActor points,
					  awardPoint,
					  pooSleft;
	
	private PooOverlord pOoo;
	public static int score;
	public static int scoreValue;
	
	public UI(){		
		pooMeter = new SpriteActor();
		pooIndicator = new SpriteActor();
		pBG = new SpriteActor();
		pBG2 = new SpriteActor();
		points = new TextActor(FONTS.DEBUG, 10);
		pooSleft= new TextActor(FONTS.DEBUG, 10);
		//points.color.set(246.0f/255.0f, 255.0f/255.0f, 0, 1);
		
		awardPoint = new TextActor(FONTS.DEBUG, 10);
		

		this.addActor(pBG);
		this.addActor(pBG2);
		this.addActor(points);
		this.addActor(pooSleft);
		this.addActor(awardPoint);
		this.addActor(pooMeter);
		this.addActor(pooIndicator);
	}
	public void connectToPooOverlord(PooOverlord pOoo){
		this.pOoo = pOoo;
	}

	public void restart(LEVEL_DEF level){
		score = 0;
		scoreValue=0;
		awardPoint.visible=false;
		
		pooMeter.setSprite(SPRITES.POO_METER, 15.0f);
		pooIndicator.setSprite(SPRITES.POO_PROGRES, 15.0f);

		
		
		LD25.tM.killTarget(points);
		LD25.tM.killTarget(awardPoint);
	}
	
	public void awardPointsForPoo(float poo_x, float poo_y){
		int points = 0;
		Array<Poo> poos = pOoo.getActivePoos();
		float minSize = 666;
		for(int i=0; i<poos.size; i++)
			minSize = (float)Math.min(minSize, Math.sqrt(Math.pow(poos.get(i).x - poo_x, 2) + 
														 Math.pow(poos.get(i).y - poo_y, 2)));
		
		if(minSize < Poo.STINK_DIAMETER/2)
			points = 0;
		else
			points = (int)((float)POINTS_PER_POO * (Math.min(1,(minSize-Poo.STINK_DIAMETER/2)/(Poo.STINK_DIAMETER/2))));
		
		
		score += points;
	}
	public int getPoints(){
		return score;
	}
	
	public void act (float delta) {
		pBG.setSprite(SPRITES.PBG, 10);
		pBG.y = 25.5f;
		pBG.x = 1;
		pBG.scaleX =1;
		
		pBG2.setSprite(SPRITES.PBG, 10);
		pBG2.y = 25.5f;
		pBG2.x = 49;
		pBG2.scaleX =-1f;
		
		pooSleft.setText(LD25.bk.getPooOverlord().getActivePoos().size+"/14");
		
		pooMeter.x = (LD25.STAGE_W-pooMeter.width)/2;
		pooMeter.y = LD25.STAGE_H-pooMeter.height*1.2f;
		pooIndicator.x = pooMeter.x;
		pooIndicator.y = pooMeter.y;
		
		
		pooIndicator.setSprite(SPRITES.POO_PROGRES, 15.0f);
		Sprite s = pooIndicator.getSprite();
		
		
		//pooIndicator.width *= 0.5f;
		float v = LD25.bk.getKitty().getPooMeterFillPercent();
		pooIndicator.width *= (v);
		s.setU2(s.getU2()-(1-v)*(s.getU2()-s.getU()));//+2f*LD25.bk.getKitty().getPooMeterFillPercent());
		
		if(scoreValue < score)
			scoreValue += 1000*delta;
		scoreValue = Math.min(scoreValue, score);
		
		points.setNumber(scoreValue, 6);
		points.x = 40-2;
		points.y = 30-1.0f;
		points.alignment = HAlignment.RIGHT;
		points.scaleX = points.scaleY = 0.022f;
		
		pooSleft.x = 2;
		pooSleft.y = 30-1.0f;
		pooSleft.alignment = HAlignment.LEFT;
		pooSleft.scaleX = pooSleft.scaleY = 0.022f;
		
		super.act(delta);
	}
}
