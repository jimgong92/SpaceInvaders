
public class OrangeAlien extends Alien {
	
	private int yVector = 1;
	
	public OrangeAlien(float x, float y) {
		super(x, y);
		sprite = Alien.g4;
		timer = 0;
	}
	
	@Override
	// all aliens should be able to move
	public void move()
	{
		this.x += this.speed;
		
		if (++timer > 25) {
			timer = 0;
			yVector *= -1;
		}
		
		this.y += yVector;
		
		// did I hit an edge?
		if (this.x >= canvas.width-50 || this.x <= 0)
		{
			// reverse speed and increase a tiny bit
			this.speed *= -1.05;
			
			// move down
			this.y += 50;
		}
	}
}
