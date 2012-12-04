package Game;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Projectile {
	
	// real coordinates
	static final int PROJECTILE_SIZE = 3;
	
	private int x, y, radius;
	private double currentAngle;
	private RealCoordinates initialPosition, currentPosition;
	protected double timeElapsed;
	private ArrayList<Double> velocityComponents;
	private ArrayList<PixelCoordinates> trajectory = new ArrayList<PixelCoordinates>();
	private Image image;
	private Image pathImage;
	
	public Projectile() {
		setInitialPositions(new RealCoordinates(0, 0));
		timeElapsed = 0;
	}
	
	public Projectile(int angle, int velocity, Image image) {
		this.image = image;
		setInitialPositions(new RealCoordinates(0, 0));
		calculateVelocity(velocity, angle);
		timeElapsed = 0;
		radius = PROJECTILE_SIZE;
		PixelCoordinates pixelCoordOrigin = new PixelCoordinates(new RealCoordinates(0,0));
		radius = (int)pixelCoordOrigin.getPixelLength(radius);
		
		pathImage = new ImageIcon(this.getClass().getResource("RainbowStrip1.png")).getImage();
	}
	
	public void setInitialPositions(RealCoordinates position) {
		initialPosition = position;
		currentPosition = position;
	}

	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	
	public void setVelocityComponents(ArrayList<Double> velocityComponents) {
		this.velocityComponents = velocityComponents;
	}
	
	public ArrayList<Double> getVelocityComponents() {
		return velocityComponents;
	}
	
	public void incrementTimeBy(double timestep) {
		timeElapsed += timestep;
		// update position
		currentPosition = calculatePosition();
		currentAngle = calculateAngle(timeElapsed);	
		updateFollowPath(currentPosition);
	}
	
	protected RealCoordinates calculatePosition() {
		double xCoordinate = velocityComponents.get(0) * timeElapsed + initialPosition.xCoordinate;
		double yCoordinate = velocityComponents.get(1) * timeElapsed + 0.5 * (-9.8) * Math.pow(timeElapsed, 2) + initialPosition.yCoordinate;
		return new RealCoordinates(xCoordinate, yCoordinate);
	}
	
	
	public void updateFollowPath(RealCoordinates position) {
//		drawIncrement++;
		if (position.yCoordinate > 0) {
			trajectory.add(new PixelCoordinates(position));
			trajectory.get(trajectory.size()-1).setAngle(currentAngle);
		}
	}
	
	public ArrayList<PixelCoordinates> getTrajectory() {
		return trajectory;
	}
	
	public void calculateVelocity(int initialVelocity, int angle) {
		velocityComponents = new ArrayList<Double>();
		
		double angle_radians = Math.toRadians(angle);
		velocityComponents.add((initialVelocity * Math.cos(angle_radians)));
		velocityComponents.add((initialVelocity * Math.sin(angle_radians)));
		
	}	
	
	public void setCurrentPosition(RealCoordinates coordinates) {
		currentPosition = coordinates;
	}
	
	public RealCoordinates getCurrentPosition() {
		return currentPosition;
	}
	
	public double getCurrentAngle() {
		return currentAngle;
	}
	
	public double calculateAngle(double time) {
		//return Math.toDegrees(Math.atan(((-9.8) * (currentPosition.xCoordinate / velocityComponents.get(0))) + currentPosition.xCoordinate));
		return Math.toDegrees(Math.atan(((-9.8 / (velocityComponents.get(0) * velocityComponents.get(0))) * currentPosition.xCoordinate + (velocityComponents.get(1) / velocityComponents.get(0)))));
	}
	
	public void Draw(Graphics g) {
		PixelCoordinates pixelCoords = new PixelCoordinates(currentPosition);
		Graphics2D g2d = (Graphics2D) g;
		
		
		g.setColor(Color.BLACK);
		
		g2d.translate(pixelCoords.xCoordinate, pixelCoords.yCoordinate);
		g2d.rotate(Math.toRadians(currentAngle));
		
		g.drawImage(image, -20, -20, 40, 40, null);
		
		g2d.rotate(Math.toRadians(360 - currentAngle));
		g2d.translate(-pixelCoords.xCoordinate, -pixelCoords.yCoordinate);
	}
	
	public void drawFollowPath(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		for (PixelCoordinates pixelCoordinates : trajectory) {
			g2d.translate(pixelCoordinates.xCoordinate, pixelCoordinates.yCoordinate);
			g2d.rotate(Math.toRadians(pixelCoordinates.getAngle()));
			g.drawImage(pathImage, -15, -10, 20, 20, null);
			
			g2d.rotate(Math.toRadians(360 - pixelCoordinates.getAngle()));
			g2d.translate(-pixelCoordinates.xCoordinate, -pixelCoordinates.yCoordinate);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)(currentPosition.xCoordinate - PROJECTILE_SIZE), (int)(currentPosition.yCoordinate - PROJECTILE_SIZE), PROJECTILE_SIZE*2, PROJECTILE_SIZE*2);
	}
}
