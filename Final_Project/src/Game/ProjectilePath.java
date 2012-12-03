package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class ProjectilePath extends Projectile {
	
	public ArrayList<PixelCoordinates> path = new ArrayList<PixelCoordinates>();
	public ArrayList<Projectile> pp = new ArrayList<Projectile>();
	
	public ProjectilePath(int angle, int velocity, double timestep) {
	//	super(angle, velocity);
		super();
		
		recalculatePath(angle, velocity, timestep);
	}
	
	public void recalculatePath(int angle, int velocity, double timestep) {
		path.clear();
	
		calculateVelocity(velocity, angle);
		timeElapsed = 0;
		// clean up later
		for (double i = 0; i < 15; i += timestep) {
			RealCoordinates temp = calculatePosition();
			if(temp.yCoordinate > 0) {
				path.add(new PixelCoordinates(temp));
			}
			timeElapsed += timestep;
		}
	}

	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		for (PixelCoordinates pixelCoordinates : path) {
			g.fillOval((int) pixelCoordinates.xCoordinate - 1, (int) pixelCoordinates.yCoordinate - 1, 2, 2);
		}
	}
}
