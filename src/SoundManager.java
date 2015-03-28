import processing.core.PApplet;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;


public class SoundManager
{
	// reference to our canvas
	public static PApplet canvas;
	
	// reference to our minim object (sound controller)
	public static Minim minim;
	
	// audio players - we need one of these for each mp3 file
	// we want to play
	public static AudioPlayer missileFireSound;

	// startup method
	public static void startup(PApplet canvas)
	{
		// store a ref to the canvas
		SoundManager.canvas = canvas;
		
		// set up our sound environment
		SoundManager.minim = new Minim(canvas);
		
		// load in our sounds
		SoundManager.missileFireSound = minim.loadFile("missile.mp3");
	}
	
	// play sounds
	public static void playMissileFireSound()
	{
		// rewind sound
		missileFireSound.rewind();
		
		// play!
		missileFireSound.play();
	}
}
