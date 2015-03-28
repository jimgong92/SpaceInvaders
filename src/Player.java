import processing.core.PApplet;


public class Player
{
	// position for our player
	public float x, y;
	
	public float w, h; //width & height
	
	// reference to the PApplet canvas
	public PApplet canvas;
	
	// our speed
	public float speed = 5;

	public Player(float x, float y, PApplet canvas)
	{
		this.x = x;
		this.y = y;
		this.canvas = canvas;
		this.w = 100;
		this.h = 20;
	}
	
	public void display()
	{
		// fill with a white color
		this.canvas.fill(255);
		
		// draw a rectangle here
		this.canvas.rect(this.x, this.y, this.w, this.h);
	}
	
	public void move(char k)
	{
		// if the key is the 'a' key on the keyboard, move left
		if (k == 'a')
		{
			this.x -= this.speed;
		}
		
		// go right
		if (k == 'd')
		{
			this.x += this.speed;
		}
		
		// wrap around
		if (this.x > this.canvas.width)
		{
			this.x = 0;
		}
		if (this.x < 0)
		{
			this.x = this.canvas.width;
		}
	}
}
