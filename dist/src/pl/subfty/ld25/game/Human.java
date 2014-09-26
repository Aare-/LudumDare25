package pl.subfty.ld25.game;

import pl.subfty.ld25.GameOver;
import pl.subfty.ld25.LD25;
import pl.subfty.ld25.game.BadKittyGame.LEVEL_DEF;
import pl.subfty.sub.vision.Art.SPRITES;
import pl.subfty.sub.vision.SColor;
import pl.subfty.sub.vision.actors.SpriteActor;
import box2dLight.ConeLight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;

public class Human extends Group
				   implements RayCastCallback{
	private static float RADIUS = 1.0f,
						 FIELD_OF_VISION_DISTANCE = 15,
						 CONE_DEGREE = 30;
	private static Color FIELD_OF_VISION_COLOUR = new Color(1,1,1,1);
	
	private LEVEL_DEF level;
	SpriteActor bgImg;
	private Vector2 tmp = new Vector2(0,1);
	private Kitty kitty;
	
	private int id;
	
  //RAGING
	private float rage;
	private SColor rageColor;
	private boolean purrPurr;
	
	private boolean kittehInRange;
	
  //STATES
	private boolean moving=false,
					rotating=false;
	public boolean searchingNewDirection,
				   rotDirection;
	
  //BOX2D STUFF
	private World world;
	private Body body;
	private ConeLight fieldOfVision;
	private Vector2 tmp1 = new Vector2(),
					tmp2 = new Vector2();
	
	private int rayMode;
	private float minLen;
	
	public Human(World world, Kitty kitty){
		this.world = world;
		this.kitty = kitty;
		
		bgImg = new SpriteActor();
		this.addActor(bgImg);
		rageColor = new SColor();
		
	  //CREATING BODY
		BodyDef d = new BodyDef();
		d.type = BodyType.DynamicBody;
		d.linearDamping = 2.5f;
		
		CircleShape shape = new CircleShape();
		shape.setRadius(RADIUS);
		shape.setPosition(new Vector2(0,0));
		
		body = this.world.createBody(d);
		body.createFixture(shape, 10.0f);
		body.setUserData(this);
		
	  //CREATING FIELD OF VISION
		FIELD_OF_VISION_COLOUR.a = 0.5f;
		fieldOfVision = new ConeLight(BadKittyGame.rH, 80, FIELD_OF_VISION_COLOUR, 
									  FIELD_OF_VISION_DISTANCE, 
									  this.x, this.y, 
									  this.rotation + CONE_DEGREE/2, CONE_DEGREE);
		fieldOfVision.setSoft(false);
		fieldOfVision.setContactFilter((short)1, (short)1, (short)2);
	}
	
	public void restart(LEVEL_DEF level, int human_id){
		this.level = level;
		this.id = human_id;
		
		bgImg.setSprite(SPRITES.values()[SPRITES.HUMAN1.ordinal()+human_id], 85*0.025f);
		
		bgImg.x = -bgImg.width/2;
		bgImg.y = -bgImg.height/2;
		
		float SC = 0.025f;
		switch(human_id){
		case 0: body.setTransform(this.x = (885)*SC, this.y = (1024-250)*SC, 0); break;
		case 1: body.setTransform(this.x = (492)*SC, this.y = (1024-618)*SC, 0); break;
		case 2: body.setTransform(this.x = (186)*SC, this.y = (1024-744)*SC, 0); break;
		case 3: body.setTransform(this.x = (265)*SC, this.y = (1024-300)*SC, 0); break;
		}
		
		kittehInRange=false;
		rage = 0;
		
		this.rotation = 360*LD25.rand.nextFloat();
		moving=true;
		rotating=false;
		searchingNewDirection=false;
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		
		
		
		this.x = body.getPosition().x;
		this.y = body.getPosition().y;
		
		tmp.set(0,1);
		tmp.rotate(this.rotation);
		
		fieldOfVision.setConeDegree(CONE_DEGREE);
		fieldOfVision.setDistance(FIELD_OF_VISION_DISTANCE);
		fieldOfVision.setPosition(this.x+tmp.x*(0.9f),
								  this.y+tmp.y*(0.9f));
		
		fieldOfVision.setDirection(this.rotation-90);
		
	  //CHECKING WHETHER KITTY IS IN EYESIGHT
		tmp1.set(kitty.body.getWorldCenter().x-this.body.getWorldCenter().x, 
				kitty.body.getWorldCenter().y-this.body.getWorldCenter().y);
		kittehInRange=false;
		if(tmp1.len() < FIELD_OF_VISION_DISTANCE*0.6f){
			tmp1.nor();
			float angleA = (this.rotation-CONE_DEGREE+270)%360,
				  angleB = (this.rotation+CONE_DEGREE+270)%360;
			if(angleA < 0) angleA += 360;
			if(angleB < 0) angleB += 360;
			boolean b = tmp1.angle() >= angleA && tmp1.angle() <= angleB;
			if(angleA > angleB)
				b = !(tmp1.angle() <= angleA && tmp1.angle() >= angleB);
			
			if(b){
				rayMode = 0;
				kittehInRange=true;
				tmp1.set(this.body.getWorldCenter());
				tmp2.set(kitty.body.getWorldCenter());
				world.rayCast(this, tmp1, tmp2);
			}
		}
		
	  //CHECKING WHETHER IN SIGHT IS ALSO A POOP THAT CAN BLE BLAMED FOR CAT
		boolean badKiteh=false;
		//TODO: change this !!!
		if(kittehInRange && 
		   (kitty.isPooInRange() || (kitty.getPooMeterFillPercent() > 0 && Gdx.input.isKeyPressed(Input.Keys.SPACE))))
			badKiteh = true;
		
	  //SHOWING RAGE
		rageColor.set(1.0f, 1.0f, 1.0f, 0.5f);
		rageColor.interp(1,0,0, rage);
		fieldOfVision.setColor(rageColor);
		rageColor.set(1.0f, 1.0f, 1.0f, 1f);
		rageColor.interp(1,0,0, rage*0.3f);
		bgImg.color.set(rageColor);
		
		if(badKiteh)
			rage += 0.8f*delta;
		else if(purrPurr)
			rage -= 0.05f;
		purrPurr=false;
		
		rage = Math.min(1, Math.max(0,rage));
		if(rage >= 1){
			GameOver.victory=false;
			LD25.bk.startDeathAnim();
		}
		
	  //MOVING
		if(getNearestObjectInThatDirection(this.rotation) < 1 ||
		   getNearestObjectInThatDirection(this.rotation) < 1  ||
		   getNearestObjectInThatDirection(this.rotation) < 1){
			collidedSearchNewDirection();
		}
			
			
		
		if(moving){
			tmp.set(0, -1);
			tmp.rotate(this.rotation);
			tmp.nor();
			tmp.mul(3);
			body.setLinearVelocity(tmp);
		}
		if(rotating)
			this.rotation += 300 * delta * (rotDirection ? 1 : -1);
		if(searchingNewDirection){
			if(getNearestObjectInThatDirection(this.rotation) > 3 &&
			   getNearestObjectInThatDirection(this.rotation-15) > 3 &&
			   getNearestObjectInThatDirection(this.rotation+15) > 3){
					searchingNewDirection=false;
					rotating=false;
					moving=true;
				}
			}
			
	}
	
	private float getNearestObjectInThatDirection(float angle){
		rayMode = 1;
		minLen = 666;
		
		tmp1.set(this.body.getWorldCenter());
		tmp2.set(0,-1);
		tmp2.rotate(angle);
		tmp2.nor();
		tmp2.mul(50);
		tmp2.add(tmp1);
		
		world.rayCast(this, tmp1, tmp2);
		return minLen;
	}

	public void collidedSearchNewDirection(){
		if(!searchingNewDirection){
			searchingNewDirection=true;
			moving=false;
			rotating = true;
			rotDirection=LD25.rand.nextBoolean();
		}
	}
	
  //GAME ACTIONS
	public void purrPurr(){
		purrPurr=true;
	}
	
	@Override
	public float reportRayFixture(Fixture fixture, Vector2 point,
								  Vector2 normal, float fraction) {
		switch(rayMode){
		case 0:
			Object o = fixture.getBody().getUserData();
			
			if(!(o != null &&o.getClass().getName().equals(Kitty.class.getName())))
				kittehInRange = false;
			
			return 1;
		case 1:
			minLen = Math.min(minLen, point.sub(this.body.getWorldCenter()).len());
			return 1;
		}
		
		return 0;
	}
}
