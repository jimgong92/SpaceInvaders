import processing.core.PApplet;


public class AlienMissile extends Missile {
	
	//Give reference to player
	private Player player;
	
	public AlienMissile(float x, float y, PApplet canvas, Player player) {
		super(x, y, canvas);
		this.player = player;
		speed = 5;
	}
	
	public void move()
	{
		// this missile is always moving down
		this.y += this.speed;
	}
	
	// see if we hit Player
	public boolean checkHit()
	{
		if (this.x >= player.x && this.x <= player.x+player.w && this.y >= player.y && this.y <= player.y+player.h)
		{
			return true;
		}
		return false;
	}
	
	// draw missile
	public void display()
	{
		this.canvas.fill(255, 255, 255);
		this.canvas.rect(this.x, this.y, 2, 15);
	}
	
}
