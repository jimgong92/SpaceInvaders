
public class BieberAlien extends Alien
{
	// constructor - make sure to call superclass
	public BieberAlien(float x, float y)
	{
		super(x, y);
		sprite = Alien.bieberImage;
	}
	
	// override the display method for 'Biebs'
	@Override
	public void display()
	{
		if (this.alive)
		{
			this.canvas.image(this.sprite, this.x, this.y, 50, 50);
		}
	}
	

}
