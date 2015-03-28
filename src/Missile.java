import processing.core.PApplet;



public class Missile
{
	// the missile needs to know where it is on the canvas
	protected float x, y;
	
	// how fast is the missile moving?
	protected float speed;
	
	// the missile also needs to know about the canvas
	protected PApplet canvas;
	
	public Missile(float x, float y, PApplet canvas)
	{
		this.x = x;
		this.y = y;
		this.canvas = canvas;
		
	}
	
	protected void move(){
	}
	
	public void reload(float x, float y)
	{
		// move the missile to the reload spot
		this.x = x;
		this.y = y;
	}
	
	protected boolean checkHit(){
		return false;
	}
	
	// draw on canvas
	protected void display(){
	}

}
