package pl.subfty.ld25.game;

import pl.subfty.ld25.GameOver;
import pl.subfty.ld25.LD25;
import pl.subfty.ld25.game.BadKittyGame.LEVEL_DEF;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class PooOverlord extends Group{
	
	private UI ui;
	private Array<Poo> poos;
	
	private int counter;
	
	public PooOverlord(UI ui){
		poos = new Array<Poo>();
		this.ui = ui;
	}
	
	public void restart(LEVEL_DEF level){
		while(poos.size > 0){
			Poo p = poos.pop();
			p.pl.remove();
			p.remove();
		}
		counter = 0;
	}
	
  //GAME ACTION
	public void makePoo(final float x, final float y){
		ui.awardPointsForPoo(x, y);
		
		Poo poo = new Poo(BadKittyGame.rH);
		poo.x = x;
		poo.y = y;
		this.addActor(poo);
		poos.add(poo);
		
		counter++;
		if(counter >= 14){
			GameOver.victory = true;
			LD25.me.setScreen(LD25.gver);
		}
	}
	
  //GETTERS
	public Array<Poo> getActivePoos(){
		return poos;
	}
}
