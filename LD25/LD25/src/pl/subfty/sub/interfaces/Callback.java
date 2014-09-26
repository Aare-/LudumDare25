package pl.subfty.sub.interfaces;

public interface Callback {
	public final static int DEFAULT=0,
							BACK_BUTTON=1,
							
						  //GAME ACTIONS
							NEW_ROUND=2,
							RESIZE = 3;
	
	public abstract void call(int type);
}