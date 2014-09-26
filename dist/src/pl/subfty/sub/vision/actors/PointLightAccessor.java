package pl.subfty.sub.vision.actors;

import com.badlogic.gdx.graphics.Color;

import box2dLight.PointLight;
import aurelienribon.tweenengine.TweenAccessor;

public class PointLightAccessor implements TweenAccessor<PointLight>{
	public static final int ALPHA = 0;
	
	
	@Override
	public int getValues(PointLight target, int tweenType, float[] returnValues) {
		switch(tweenType){
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1;
			
		}
		return 0;
	}

	@Override
	public void setValues(PointLight target, int tweenType, float[] newValues) {
		switch(tweenType){
		case ALPHA:
			Color c = target.getColor();
			c.a = newValues[0];
			target.setColor(c);
			break;
		}
		
	}
}
