import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Alien
{
	// aliens need to know their position in space
	public float x, y;
	
	// how fast am I going?
	public float speed = 3;
	
	// all aliens start off as alive
	public boolean alive = true;
	
	// all aliens need to know about the canvas - let's
	// make it static so they can share
	public static PApplet canvas;
	
	// all aliens will also need to know about the artwork
	// they should be using.  instead of having each alien
	// load each graphic file we should make these static as well
	public static PImage g1, g2, g3, g4;
	public static PImage bieberImage;
	
	//default sprite is g1 image
	protected PImage sprite = g1;
	
	//for duration effects
	protected int timer;
	protected int tick; //speed of timer
	
	public Alien(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	// all aliens need to be able to display themselves
	public void display()
	{
		// only draw an alien if it is alive
		if (alive)
		{
			canvas.image(this.sprite, this.x,  this.y);
		}
	}
	
	// was this alien hit?
	public void die()
	{
		this.alive = false;
	}
		
	// all aliens should be able to move
	public void move()
	{
		this.x += this.speed;
		
		// did I hit an edge?
		if (this.x >= canvas.width-50 || this.x <= 0)
		{
			// reverse speed and increase a tiny bit
			this.speed *= -1.05;
			
			// move down
			this.y += 50;
		}
	}
	
	// static method - called to init our shared variables
	public static void startup(PApplet canvas)
	{
		// store a ref to the canvas
		Alien.canvas = canvas;
		
		// load in our artwork
		Alien.g1 = canvas.loadImage("alien1.png");
		Alien.g2 = canvas.loadImage("alien2.png");
		Alien.g3 = canvas.loadImage("alien3.png");
		Alien.g4 = canvas.loadImage("alien4.png");
		Alien.bieberImage = canvas.loadImage("bieber.png");
	}
	
	public boolean hasLanded() {
		if (y >= 500) return true;
		return false;
	}

}
