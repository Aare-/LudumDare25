package pl.subfty.sub.vision;

import com.badlogic.gdx.graphics.Color;

public class SColor extends Color{
	
	public SColor(){
		super();
	}
	public SColor(Color c){
		super(c);
	}
	public SColor(float r, float g, float b, float a){
		super(r,g,b,a);
	}
	
	public void interp(float r, float g, float b, float value){
		this.r = this.r*(1-value)+r*value;
		this.g = this.g*(1-value)+g*value;
		this.b = this.b*(1-value)+b*value;
	}
	public void interp(Color c, float value){
		interp(c.r, c.g, c.b, value);
	}
}
