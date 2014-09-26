package pl.subfty.sub.audio;

import pl.subfty.sub.vision.Art;
import aurelienribon.tweenengine.TweenAccessor;
public class SoundAccessor implements TweenAccessor<SSound>{

	@Override
	public int getValues(SSound arg0, int arg1, float[] arg2) {
		arg2[0] = Art.sound.getVolume(arg1);
		return 1;
	}

	@Override
	public void setValues(SSound arg0, int arg1, float[] arg2) {
		Art.sound.setVoumeforLoopingS(arg1, arg2[0]);
	}

}
