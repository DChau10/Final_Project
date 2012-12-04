package Game;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Projectile {
	
	// real coordinates
	static final int PROJECTILE_SIZE = 3;
	
	private int x, y, radius;
	private int angle, velocity;
	private double currentAngle;
	private int drawIncrement;
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
		this.angle = angle;
		this.velocity = velocity;
		this.image = image;
		setInitialPositions(new RealCoordinates(0, 0));
		calculateVelocity(velocity, angle);
		timeElapsed = 0;
		drawIncrement = 0;
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
		System.out.println(currentAngle);
		updateFollowPath(currentPosition);
	}
	
	protected RealCoordinates calculatePosition() {
		double xCoordinate = velocityComponents.get(0) * timeElapsed + initialPosition.xCoordinate;
		double yCoordinate = velocityComponents.get(1) * timeElapsed + 0.5 * (-9.8) * Math.pow(timeElapsed, 2) + initialPosition.yCoordinate;
		return new RealCoordinates(xCoordinate, yCoordinate);
	}
	
	
	public void updateFollowPath(RealCoordinates position) {
		drawIncrement++;
//		if (position.yCoordinate > 0 && (drawIncrement % 4) == 0) {
//			trajectory.add(new PixelCoordinates(position));
//		}
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
		
	//	return velocityComponents;
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
//		System.out.println((-9.8) * currentPosition.xCoordinate);
//		g.fillOval((int) pixelCoords.xCoordinate - radius, (int) pixelCoords.yCoordinate - radius, radius * 2, radius * 2);
//		
//		g.drawImage(image,(int) pixelCoords.xCoordinate - 20, (int)pixelCoords.yCoordinate - 20, 40, 40, null);
		
		g2d.translate(pixelCoords.xCoordinate, pixelCoords.yCoordinate);
		g2d.rotate(Math.toRadians(currentAngle));
		
		g.drawImage(image, -20, -20, 40, 40, null);
		
		g2d.rotate(Math.toRadians(360 - currentAngle));
		g2d.translate(-pixelCoords.xCoordinate, -pixelCoords.yCoordinate);
		
		
//		g.setColor(Color.ORANGE);
//		g.fillOval((int)currentPosition.xCoordinate - radius, (int)currentPosition.yCoordinate - radius, radius*2, radius*2);
		
		// draw bounding box DEBUGNESS
//		PixelCoordinates z = new PixelCoordinates(new RealCoordinates(getBounds().x, getBounds().y));
//		g.drawRect((int)z.xCoordinate, (int)z.yCoordinate, radius * 2, radius * 2);
		
//		Rectangle shenanigans = getBounds();
//		PixelCoordinates dos = new PixelCoordinates(new RealCoordinates(shenanigans.x, shenanigans.y));
//		g.setColor(Color.RED);
//		g.fillRect((int) dos.xCoordinate, (int)dos.yCoordinate, radius*2, radius*2);
	}
	
	public void drawFollowPath(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		for (PixelCoordinates pixelCoordinates : trajectory) {
			g2d.translate(pixelCoordinates.xCoordinate, pixelCoordinates.yCoordinate);
			g2d.rotate(Math.toRadians(pixelCoordinates.getAngle()));
			
		//	g.fillOval((int) pixelCoordinates.xCoordinate - 1, (int) pixelCoordinates.yCoordinate - 1, 2, 2);
		//	g.drawImage(pathImage, (int) pixelCoordinates.xCoordinate - radius + 5, (int) pixelCoordinates.yCoordinate - radius + 5, radius*2, radius*2, null);
			
		//	g.drawImage(pathImage, (int) pixelCoordinates.xCoordinate - 18, (int) pixelCoordinates.yCoordinate - 10, 20, 20, null);
			g.drawImage(pathImage, -15, -10, 20, 20, null);
			
			g2d.rotate(Math.toRadians(360 - pixelCoordinates.getAngle()));
			g2d.translate(-pixelCoordinates.xCoordinate, -pixelCoordinates.yCoordinate);
		}
	}
	
	public Rectangle getBounds() {
		//System.out.println(((int)currentPosition.xCoordinate - radius) + "  " +  ((int)currentPosition.yCoordinate - radius) + "  ,  " + 
				//((int)currentPosition.xCoordinate + radius) + "  " +  ((int)currentPosition.yCoordinate + radius));
		return new Rectangle((int)(currentPosition.xCoordinate - PROJECTILE_SIZE), (int)(currentPosition.yCoordinate - PROJECTILE_SIZE), PROJECTILE_SIZE*2, PROJECTILE_SIZE*2);
				//(int)currentPosition.xCoordinate + radius, (int)currentPosition.yCoordinate + radius);
	}
}
