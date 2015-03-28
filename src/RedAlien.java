
public class RedAlien extends Alien {
	//alternates speed between 2 and 4
	
	public RedAlien(float x, float y) {
		super(x, y);
		sprite = Alien.g2;
		timer = 0;
		speed = 2;
	}
	
	public void display()
	{
		// only draw an alien if it is alive
		if (alive)
		{
			//arbitrary reset timer 
			if (++timer > 40) {
				timer = 0;
				speed /= 2;
			}
			
			//adjust speed
			if (timer == 20) speed *= 2;
			
			canvas.image(this.sprite, this.x, this.y);
		}
	}
	
	
}
