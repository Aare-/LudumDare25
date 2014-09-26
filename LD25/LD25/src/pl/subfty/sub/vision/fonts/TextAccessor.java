package pl.subfty.sub.vision.fonts;

import aurelienribon.tweenengine.TweenAccessor;

public class TextAccessor implements TweenAccessor<TextActor>{

	public final static int SCALE_X=0,
							SCALE_Y=1,
							SCALE_XY=2,
							POSITION_XY=3,
							ALPHA=4,
							FALPHA=5,
							NUM=6,
							FADE_IN=7;
	
	@Override
	public int getValues(TextActor arg0, int arg1, float[] arg2) {
		switch (arg1) {
		case SCALE_X:
			arg2[0] = arg0.scaleX;
			return 1;
		case SCALE_Y:
			arg2[0] = arg0.scaleY;
			return 1;
		case SCALE_XY:
			arg2[0] = arg0.scaleX;
			arg2[1] = arg0.scaleY;
			return 2;
		case POSITION_XY:
			arg2[0] = arg0.x;
			arg2[1] = arg0.y;
			return 2;
		case ALPHA:
			arg2[0] = arg0.alpha;
			return 1;
		case NUM:
			arg2[0] = arg0.settedNumberF;
			return 1;
		case FADE_IN:
			arg2[0] = arg0.fadeInA;
			return 1;
		}
		return 0;
	}

	@Override
	public void setValues(TextActor arg0, int arg1, float[] arg2) {
		switch (arg1) {
		case SCALE_X:
			arg0.scaleX = arg2[0]; 
			break;
		case SCALE_Y:
			arg0.scaleY = arg2[0];
			break;
		case SCALE_XY:
			arg0.scaleX = arg2[0];
			arg0.scaleY = arg2[1];
			break;
		case POSITION_XY:
			arg0.x = arg2[0];
			arg0.y = arg2[1];
			break;
		case ALPHA:
			arg0.alpha = arg2[0];
			break;
		case NUM:
			arg0.setNumberF(arg2[0], 15);
			break;
		case FADE_IN:
			arg0.fadeInA = arg2[0];
			break;
		}
	}

}
