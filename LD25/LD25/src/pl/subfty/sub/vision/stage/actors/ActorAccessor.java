package pl.subfty.sub.vision.stage.actors;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorAccessor implements TweenAccessor<Actor>{

	public final static int POS_X=0,
							POS_Y=1,
							POS_XY=2,
							SCALE_XY=3,
							ALPHA=4,
							SIZE_WH=5,
							SIZE_H=6,
							SIZE_W=7,
							ROTATION=8;
	@Override
	public int getValues(Actor target, int type, float[] val) {
		
		switch(type){
		case POS_X:
			val[0] = target.x;
			return 1;
		case POS_Y:
			val[0] = target.y;
			return 1;
		case POS_XY:
			val[0] = target.x;
			val[1] = target.y;
			return 2;
		case SCALE_XY:
			val[0] = target.scaleX;
			val[1] = target.scaleY;
			return 2;
		case ALPHA:
			val[0] = target.color.a;
			return 1;
		case SIZE_WH:
			val[0] = target.width;
			val[1] = target.height;
			return 2;
		case SIZE_W:
			val[0] = target.width;
			return 1;
		case SIZE_H:
			val[0] = target.height;
			return 1;
		case ROTATION:
			val[0] = target.rotation;
			return 1;
		}
		
		return 0;
	}

	@Override
	public void setValues(Actor target, int type, float[] val) {
		switch(type){
		case POS_X:
			target.x = val[0];
			break;
		case POS_Y:
			target.y = val[0];
			break;
		case POS_XY:
			target.x = val[0];
			target.y = val[1];
			
			break;
		case SCALE_XY:
			target.scaleX = val[0];
			target.scaleY = val[1];
			break;
		case ALPHA:
			target.color.a = val[0];
			break;
		case SIZE_WH:
			target.width = val[0];
			target.height= val[1];
			break;
		case SIZE_W:
			target.width = val[0];
			break;
		case SIZE_H:
			target.height = val[0];
			break;
		case ROTATION:
			target.rotation = val[0];
			break;
		}
	}
}
