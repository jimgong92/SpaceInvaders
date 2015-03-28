import processing.core.PApplet;
import processing.core.PImage;


public class CartridgeRefresh {
	
	public float x, y;
	public float speed = 3;
	
	public boolean used;
	
	private static PImage Sprite;
	public static PApplet canvas;
	
	public CartridgeRefresh(float x, Player player) {
		this.x = x;
		this.y = 0;
	}
	
	public void display()
	{
		// only draw an alien if it is alive
		if (!used)
		{
			canvas.image(Sprite, this.x,  this.y);
		}
	}
	
	public void fall() {
		this.y += speed;
		if (this.y > 550) use();
	}
	
	public void use() {
		this.used = true;
	}
	
	// see if we hit Player
	public boolean checkHit(Player player)
	{
		if (this.x >= player.x && this.x <= player.x+player.w && this.y >= player.y && this.y <= player.y+player.h)
		{
			use();
			return true;
		}
		return false;
	}
	
	// static method - called to init our shared variables
	public static void startup(PApplet canvas)
	{
		// store a ref to the canvas
		CartridgeRefresh.canvas = canvas;
		
		// load in our artwork
		CartridgeRefresh.Sprite = canvas.loadImage("cartridge.png");
	}
}
