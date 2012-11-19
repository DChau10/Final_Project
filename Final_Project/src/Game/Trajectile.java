package Game;

public class Trajectile {
	private int x, y;
	private int angle, velocity;
	
	public Trajectile(int angle, int velocity) {
		this.angle = angle;
		this.velocity = velocity;
	}
	public void Draw() {
		
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	
}
