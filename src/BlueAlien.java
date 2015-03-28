
public class BlueAlien extends Alien {
	/*
	 * Grow and shrink
	 */
	public BlueAlien(float x, float y) {
		super(x, y);
		sprite = Alien.g1;
		timer = 25;
		tick = 1;
	}
	
	//grow and shrink effect based on timer
	public void display()
	{
		// only draw an alien if it is alive
		if (alive)
		{
			if (timer > 50 || timer < 25) tick *= -1;
			timer += tick;
			canvas.image(this.sprite, this.x, this.y, timer, timer);
		}
	}

}
