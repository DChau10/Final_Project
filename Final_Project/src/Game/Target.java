package Game;

import java.awt.Graphics;

public abstract class Target  {
	
	protected int x, y;
	
	
	public Target(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return 0;
	}
	
	public int getY() {
		return 0;
	}
	
	public void Draw(Graphics g) {
		
	}
}
