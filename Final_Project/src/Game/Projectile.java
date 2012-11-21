package Game;

import java.awt.*;
import java.util.ArrayList;

public class Projectile {
	private int x, y;
	private int angle, velocity;
	private RealCoordinates initialPosition, currentPosition;
	private double timeElapsed;
	
	public Projectile(int angle, int velocity) {
		this.angle = angle;
		this.velocity = velocity;
		setInitialPositions(new RealCoordinates(0, 0));
		timeElapsed = 0;
	}
	
	public void setInitialPositions(RealCoordinates position) {
		initialPosition = position;
		currentPosition = position;
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
	
	public void incrementTimeBy(double timestep, ArrayList<Double> velocityComponents) {
		timeElapsed += timestep;
		// update position
		double xCoordinate = velocityComponents.get(0) * timeElapsed + initialPosition.xCoordinate;
		double yCoordinate = velocityComponents.get(1) * timeElapsed + 0.5 * (-9.8) * Math.pow(timeElapsed, 2) + initialPosition.yCoordinate;
		System.out.println("Time: " + timeElapsed + "( " + xCoordinate + " , " + yCoordinate + " )");
		currentPosition.setXY(xCoordinate, yCoordinate);
	}
	
	public void setCurrentPosition(RealCoordinates coordinates) {
		currentPosition = coordinates;
	}
	
	public RealCoordinates getCurrentPosition() {
		return currentPosition;
	}
	
	public void Draw(Graphics g) {
		PixelCoordinates pixelCoords = new PixelCoordinates(currentPosition);
		g.setColor(Color.BLACK);
		g.fillOval((int) pixelCoords.xCoordinate - 2, (int) pixelCoords.yCoordinate - 2, 4, 4);
	}
	
}
