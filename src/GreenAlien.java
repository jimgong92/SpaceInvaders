
public class GreenAlien extends Alien{
	
	//Occasionally turns invisible
	public GreenAlien(float x, float y) {
		super(x, y);
		sprite = Alien.g3;
		timer = 0;
	}
	
	public void display()
	{
		// only draw an alien if it is alive
		if (alive)
		{
			if (++timer > 150) timer = 0;
			
			//goes invisible 50/150 frames
			if (timer > 100) canvas.image(sprite, x, y, 0, 0);
			else canvas.image(this.sprite, this.x, this.y);
		}
	}

}
