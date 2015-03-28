import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class SpaceInvaders extends PApplet
{
	// our player
	Player thePlayer;
	
	// our aliens
	Alien[] theAliens;
	int aliensPerRow = 12;
	int rows = 4;
	
	//random
	Random random = new Random();
	
	//cartridge to hold missiles
	Cartridge cartridge;
	
	ArrayList<CartridgeRefresh> refreshers = new ArrayList<CartridgeRefresh>();
	
	//Alien missiles
	AlienMissile[] alienMissiles;
	
	// sound manager
	SoundManager beatBox;
	
	//manage screens
	//0 -> Title, 1 -> Win, 2 -> Lose, 3 -> Play
	private int screenState;
	private PImage[] screens = {
			this.loadImage("screen_title.png"),
			this.loadImage("screen_youwin.png"),
			this.loadImage("screen_youlose.png"),
	};
	
	public void setup()
	{
		// size our canvas
		size(800, 500);
		
		// set up our sounds
		this.beatBox = new SoundManager();
		this.beatBox.startup(this);
		
		// create our player
		this.thePlayer = new Player(400, 450, this);
		
		// create the missile on top of the player
		cartridge = new Cartridge(10);
		cartridge.load(800, 550, this);
		
		//startup Cartridge refresh
		CartridgeRefresh.startup(this);
		
		// startup the Alien class
		Alien.startup(this);
		
		// create our aliens
		this.theAliens = new Alien[aliensPerRow * rows];
		this.alienMissiles = new AlienMissile[theAliens.length];
		
		for (int i = 0; i < this.theAliens.length; i++)
		{
			// random chance for a bieber alien!
			if (Math.random() > 0.75)
			{
				this.theAliens[i] = new BieberAlien((i % aliensPerRow) * 50, 50 * (i / aliensPerRow));
			}
			else
			{
				// spread out the aliens a bit by multiplying i*50
				//blue alien -> top row
				if (i / aliensPerRow == 0) {
					this.theAliens[i] = new BlueAlien((i % aliensPerRow) * 50, 50 * (i / aliensPerRow));
				}
				//red alien -> second row
				else if (i / aliensPerRow == 1) {
					this.theAliens[i] = new RedAlien((i % aliensPerRow) * 50, 50 * (i / aliensPerRow));
				}
				//green alien - third row
				else if (i / aliensPerRow == 2) {
					this.theAliens[i] = new GreenAlien((i % aliensPerRow) * 50, 50 * (i / aliensPerRow));
				}
				else {
					this.theAliens[i] = new OrangeAlien((i % aliensPerRow) * 50, 50 * (i / aliensPerRow));
				}
			}
			//create missiles offscreen
			this.alienMissiles[i] = new AlienMissile(800, 500, this, this.thePlayer);
		}
		
		
		//Set to title screen
		screenState = 0;
	}
	
	public void draw()
	{
		
		// erase the world
		background(0);
		
		//Display splash screen
		if (screenState != 3) image(screens[screenState], 0, 0);
		
		//Title splash screen, press key to go to play mode
		if (screenState == 0) {
			if (keyPressed) screenState = 3;
		}
		
		//Win splash screen, press key to restart
		else if (adjustPoints() == theAliens.length) {
			screenState = 1;
			//allow for replay
			if (keyPressed) {
				setup();
			}
		}
		
		//Lose splash screen, press key to restart
		else if (screenState == 2) {
			//allow for replay
			if (keyPressed) {
				setup();
			}
		}
		
		else {
			// title
			fill(255);
			text("Space Invaders! Press 'a' and 'd' to move, 'm' to fire!", 10, 10);
			text("Points: " + adjustPoints(), 470, 10);
			text("Remaining Shots: " + cartridge.currentAvailable(), 470, 20);
			
			if (random.nextInt(100) == 0) {
				refreshers.add(new CartridgeRefresh(random.nextInt(800), this.thePlayer));
			}
			
			//display cartridge refresher(s)
			for (int i = 0; i < refreshers.size(); i++) {
				refreshers.get(i).display();
				if (refreshers.get(i).checkHit(this.thePlayer)) {
					cartridge.load(800, 550, this);
				}
				refreshers.get(i).fall();					
			}
			
			// display aliens
			for (int i = 0; i < this.theAliens.length; i++)
			{
				// did the missile hit any of the aliens?
				for (PlayerMissile m : cartridge.missiles) {
					if (m.checkHit( this.theAliens[i] ))
					{
						this.theAliens[i].die();
					}				
				}
				this.theAliens[i].move();
				this.theAliens[i].display();
				
				//only alive aliens shoot
				if (theAliens[i].alive) {
					if (random.nextInt(1500) == 0) this.alienMissiles[i].reload(this.theAliens[i].x + 20, this.theAliens[i].y + 20);
					this.alienMissiles[i].move();
					this.alienMissiles[i].display();
					//if player hit or aliens landed, game over
					if (this.alienMissiles[i].checkHit() || this.theAliens[i].hasLanded()) {
						screenState = 2;
					}
				}
				
			}
			
			// move the character, if necessary
			if (keyPressed)
			{
				// send the current key into the Player object for evaluation
				this.thePlayer.move( key );
				
			}
			
			// draw the player
			this.thePlayer.display();
			
			for (PlayerMissile m : cartridge.missiles) {
				m.move();
				m.display();
			}
			
		}
	}

	public void keyPressed()
	{
		// reload the missile, if necessary
		if (key == 'm')
		{
			if(!cartridge.isEmpty()) {
				cartridge.shoot().reload(this.thePlayer.x + this.thePlayer.w / 2,  this.thePlayer.y);
				// play our explosion sound
				this.beatBox.playMissileFireSound();
			}

		}
	}
	
	public int adjustPoints() {
		int points = 0;
		for (int i = 0; i < theAliens.length; i++) {
			if (!theAliens[i].alive) points++;
		}
		return points;
	}
	
}
