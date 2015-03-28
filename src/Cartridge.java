import processing.core.PApplet;


public class Cartridge {
	
	public PlayerMissile[] missiles;
	private int capacity;
	public int available;
	
	public Cartridge(int capacity) {
		this.capacity = capacity;
		missiles = new PlayerMissile[capacity];
	}
	
	//if shots available, return true, if not return false
	public PlayerMissile shoot() {
		for (int i = 0; i < capacity; i++) {
			if (!missiles[i].isShot()) {
				missiles[i].setShot(true);
				return missiles[i];
			}
		}
		//no shots left
		return null;
	}
	
	public int currentAvailable() {
		int total = 0;
		for (int i = 0; i < capacity; i++) {
			if (!missiles[i].isShot()) total++;
		}
		return total;
	}
	
	public boolean isEmpty() {
		for (int i = 0; i < capacity; i++) {
			//if available missile, return true
			if (!missiles[i].isShot()) {
				return false;
			}
		}
		//no shots left
		return true;
	}
	
	public void load(float x, float y, PApplet canvas) {
		for (int i = 0; i < capacity; i++) {
			missiles[i] = new PlayerMissile(x, y, canvas);
		}
	}
	
	
}
