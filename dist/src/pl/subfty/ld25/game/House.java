package pl.subfty.ld25.game;

import pl.subfty.ld25.game.BadKittyGame.LEVEL_DEF;
import pl.subfty.sub.vision.Art.SPRITES;
import pl.subfty.sub.vision.actors.SpriteActor;
import aurelienribon.bodyeditor.BodyEditorLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;

public class House extends Group{
	private BodyEditorLoader bLoader;
	private Body body;
	private World world;
	
	private SpriteActor background;
	
	public House(World world){
		this.world = world;
		background = new SpriteActor();
		
		this.addActor(background);
	}
	
	public void restart(LEVEL_DEF level){
		switch(level){
		case LEVEL_1:
			FixtureDef f = new FixtureDef();
			f.isSensor = false;
			f.restitution = 0.01f;
			f.density = 10;
			BodyDef b = new BodyDef();
			b.position.set(0, 0);
			b.gravityScale = 0;
			b.type = BodyType.StaticBody;
			
			if(body != null)
				world.destroyBody(body);
			body = world.createBody(b);
			body.setUserData(this);
			
			background.setSprite(SPRITES.LEVEL1BG, 26.31f);
			bLoader = new BodyEditorLoader(Gdx.files.internal("bodies/level1"));
			bLoader.attachFixture(body, "background", f, background.height);
			
			for(int i=0; i<body.getFixtureList().size(); i++){
			Filter fi = body.getFixtureList().get(i).getFilterData();
			fi.categoryBits = 3;
			body.getFixtureList().get(i).setFilterData(fi);
			}
			
			break;
		}
	}
	
	@Override
	public void act(float delta){
		body.setTransform(0, 0, 0);
		super.act(delta);
	}
}
