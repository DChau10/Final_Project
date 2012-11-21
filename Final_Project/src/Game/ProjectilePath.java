package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class ProjectilePath extends Projectile {
	
	public ArrayList<PixelCoordinates> path = new ArrayList<PixelCoordinates>();

	public ProjectilePath(int angle, int velocity) {
		super(angle, velocity);
		recalculatePath(angle, velocity);
	}
	public void recalculatePath(int angle, int velocity) {
		path.clear();
		HAZARDsetVelocityComponents(angle, velocity);
		// clean up later
		for (double i = 0; i < 2; i += (1000.0 / 60.0) / 1000.0) {
			path.add(new PixelCoordinates(calculatePosition()));
			timeElapsed += (1000.0 / 60.0) / 1000.0;
		}
	}
	private void HAZARDsetVelocityComponents(int angle, int velocity) {
		ArrayList<Double> velocityComponents = new ArrayList<Double>();
		double angle_radians = Math.toRadians(angle);
		velocityComponents.add((velocity * Math.cos(angle_radians)));
		velocityComponents.add((velocity * Math.sin(angle_radians)));
		setVelocityComponents(velocityComponents);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		for (PixelCoordinates pixelCoordinates : path) {
			g.fillOval((int) pixelCoordinates.xCoordinate - 1, (int) pixelCoordinates.yCoordinate - 1, 2, 2);
		}
//		PixelCoordinates pixelCoords = new PixelCoordinates(currentPosition);
//		g.setColor(Color.GRAY);
//		g.fillOval((int) pixelCoords.xCoordinate - 2, (int) pixelCoords.yCoordinate - 2, 4, 4);
	}
	

}
