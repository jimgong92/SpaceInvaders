import processing.core.PApplet;


public class PlayerMissile extends Missile {
	
	private boolean shot;
	
	public PlayerMissile(float x, float y, PApplet canvas) {
		super(x, y, canvas);
		speed = 10;
		shot = false;
	}
	
	public void move()
	{
		// this missile is always moving up
		this.y -= this.speed;
	}
	
	// see if we hit an Alien
	public boolean checkHit(Alien badGuy)
	{
		if (this.x >= badGuy.x && this.x <= badGuy.x+50 && this.y >= badGuy.y && this.y <= badGuy.y+50)
		{
			return true;
		}
		return false;
	}
	
	public void display()
	{
		this.canvas.fill(0,255,0);
		this.canvas.rect(this.x, this.y, 5, 25);
	}
	
	public boolean isShot() {
		return shot;
	}
	
	public void setShot(boolean available) {
		shot = available;
	}
}
