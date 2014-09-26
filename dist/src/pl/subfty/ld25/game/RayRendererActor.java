package pl.subfty.ld25.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RayRendererActor extends Actor{

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.end();
		BadKittyGame.rH.updateAndRender();
		batch.begin();
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}
