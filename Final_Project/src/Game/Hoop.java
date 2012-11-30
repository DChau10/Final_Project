package Game;

import java.awt.Graphics;
import java.awt.Graphics2D;


public class Hoop extends Target{
	
	private int radius;
	private int width, height;
	RealCoordinates coords;
	
	public Hoop(int x, int y, int radius) {
		super(x, y);
		height = radius;
		width = radius/4;
		this.radius = radius;
		coords = new RealCoordinates(x, y);
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public void Draw(Graphics g) {
		PixelCoordinates pixelCoords = new PixelCoordinates(coords);
		PixelCoordinates pixelCoords2 = new PixelCoordinates(new RealCoordinates(x + radius, y + radius));
		int hypotenuse = (int)(Math.sqrt(x*x + y*y));
		g.drawLine((int)pixelCoords.xCoordinate, (int)pixelCoords.yCoordinate, (int)(pixelCoords2.xCoordinate), (int)(pixelCoords2.yCoordinate));
	//	g.drawLine(x, y, x + radius, y + radius);
	//	g.drawLine(500, 300, 520, 320);
	}
	
}
