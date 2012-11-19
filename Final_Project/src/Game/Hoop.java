package Game;


public class Hoop extends Target{
	
	private int radius;
	
	public Hoop(int x, int y, int radius) {
		super(x, y);
		this.radius = radius;
	}
	
	
//	public int getX() {
//		return x;
//	}
//	
//	public void setX(int x) {
//		this.x = x;
//	}
//
//	public int getY() {
//		return y;
//	}
//
//	public void setY(int y) {
//		this.y = y;
//	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void Draw() {
		
	}
	
}
