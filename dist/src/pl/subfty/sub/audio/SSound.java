package pl.subfty.sub.audio;

import com.badlogic.gdx.audio.Sound;

public class SSound {
	
	private final static String SOUND_PATCH = "sounds/";
  //DYNAMIC SOUNDS
	public static final int HIT_1=0,
							HIT_2=18,
							HIT_3=1,
							POWUP_1=2,
							POWUP_2=3,
							POWUP_3=22,
							BOMB=4,
							MIS_2=5,
							MIS_3=6,
							DEATH=7,
							COUNT=8,
							POWUP_COLL=9,
							VIRUS_COLL=10,
							MENU_FORTH=11,
							MENU_BACK=12,
							START_GAME=13,
							SHIELD_HIT_1=14,
							SHIELD_HIT_2=15,
							VIRUS=16,
							NEW_HISCORE=17,
							TICK_1=19,
							TICK_2=20,
							WHEEL=21;
	private Sound sounds[][] = new Sound[23][2];
	private long loopingIds[][] = new long[23][2];
	private boolean looping[] = new boolean[23];
	private float volume[] = new float[23];
	
	public SSound(){
//		for(int i=0; i<loopingIds.length; i++){
//			loopingIds[i][0] = -1;
//			loopingIds[i][1] = -1;
//		}
//		
//	  //LOADING STATIC SOUNDS
//		loadSound(HIT_1,"h1","h1");
//		loadSound(HIT_2,"h2","h2");
//		loadSound(HIT_3,"h3","h3");
//		loadSound(BOMB,"bomb","bomb");
//		loadSound(MIS_2,"miss2","miss1");
//		loadSound(MIS_3,"miss3","miss1");
//		loadSound(DEATH, "death","death");
//		loadSound(COUNT, "points_count","points_count");
//		loadSound(POWUP_COLL, "powerup_collect","powerup_collect");
//		loadSound(VIRUS_COLL, "virus_collect","virus_collect");
//		loadSound(MENU_FORTH, "menu_click","menu_click");
//		loadSound(MENU_BACK, "menu_back","menu_back");
//		loadSound(START_GAME, "menu_gamestart","menu_gamestart");
//		loadSound(SHIELD_HIT_1, "shieldhit1","shieldhit1");
//		loadSound(SHIELD_HIT_2, "shieldhit2","shieldhit1");
//		loadSound(POWUP_1,"powerup2","powerup1");
//		loadSound(POWUP_2,"powerup4","powerup2");
//		loadSound(VIRUS,"virus1","virus1");
//		loadSound(NEW_HISCORE,"newhighscore","newhighscore");
//		loadSound(TICK_1,"tick1","tick1");
//		loadSound(TICK_2,"tick2","tick2");
//		loadSound(WHEEL, "wheel2", "wheel2");
//		loadSound(POWUP_3, "powerup5", "powerup2");
	}
	private void loadSound(int id, String name, String retro){
//		sounds[id][0] = Gdx.audio.newSound(Gdx.files.internal(SOUND_PATCH+name+".ogg"));
//		if(retro == null)
//			sounds[id][1] = sounds[id][0];
//		else
//			sounds[id][1] = Gdx.audio.newSound(Gdx.files.internal(SOUND_PATCH+"8bit/"+retro+".ogg"));
//		looping[id] = false;
	}
	
	public void playSound(int id){
//		playSound(id, 1);
	}
	public void playSound(int id, float volume){
//		if(GYRO.gState.bData[GameState.B_SOUND]){
//			sounds[id][0].stop();
//			sounds[id][1].stop();
//			if(looping[POWUP_1] || looping[POWUP_2] || looping[VIRUS])
//				volume *= 0.75f;
//			if(GYRO.gState.bData[GameState.B_RETRO])
//				sounds[id][1].play(volume*0.7f);
//			else
//				sounds[id][0].play(volume);
//		}
	}
	public void loopSound(int id){
		loopSound(id, 1);
	}
	public void loopSound(int id, float volume){
//		if(GYRO.gState.bData[GameState.B_SOUND]){
//			sounds[id][0].stop();
//			sounds[id][1].stop();
//			this.volume[id] = volume;
//			if(GYRO.gState.bData[GameState.B_RETRO]){
//				if(loopingIds[id][1] != -1)
//					sounds[id][1].stop(loopingIds[id][1]);
//				loopingIds[id][1] = sounds[id][1].loop(volume*0.7f);
//			}else{
//				if(loopingIds[id][0] != -1)
//					sounds[id][0].stop(loopingIds[id][0]);
//				loopingIds[id][0] = sounds[id][0].loop(volume);
//			}
//			
//			looping[id] = true;
//		}
	}
	public boolean isLooping(int id){
		return looping[id];
	}
	public void setVoumeforLoopingS(int id, float volume){
//		if(!looping[id])
//			return;
//		this.volume[id] = volume;
//		if(GYRO.gState.bData[GameState.B_RETRO])
//			sounds[id][1].setVolume(loopingIds[id][1], volume);
//		else
//			sounds[id][0].setVolume(loopingIds[id][0], volume);
	}
	public float getVolume(int id){
		return this.volume[id];
	}
	public void stop(int id){
		if(loopingIds[id][1] != -1)
			sounds[id][1].stop(loopingIds[id][1]);
		if(loopingIds[id][0] != -1)
			sounds[id][0].stop(loopingIds[id][0]);
		loopingIds[id][1] = -1;
		loopingIds[id][0] = -1;
		sounds[id][0].stop();
		sounds[id][1].stop();
		looping[id]=false;
	}
	public void stopAll(){
		for(int i=0; i<sounds.length; i++)
			stop(i);
	}

	public void pauseAllLooping(){
		for(int i=0; i<sounds.length; i++)
			if(looping[i]){
				sounds[i][0].stop();
				sounds[i][1].stop();
			}	
	}
	public void stopAllLooping(){
		for(int i=0; i<sounds.length; i++)
			if(looping[i])
				stop(i);
	}
	public void resumeAllLooping(){
		resumeAllLoopingExceptId(-1);
	}
	public void resumeAllLoopingExceptId(int id){
//		for(int i=0; i<sounds.length; i++)
//			if(i != id && looping[i]){
//				if(GYRO.gState.bData[GameState.B_RETRO])
//					sounds[i][1].loop(volume[i]);
//				else
//					sounds[i][0].loop(volume[i]);
//			}
	}
}
