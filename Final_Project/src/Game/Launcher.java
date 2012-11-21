package Game;
import java.util.ArrayList;
import java.awt.*;


public class Launcher {
	
	private int initialVelocity, angle;
	private int x, y;
	private double timeElapsed;
//	private Projectile projectile;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Double> velocityComponents;
	private RealCoordinates initialPosition, currentPosition;
	
	//constructor
	public Launcher(int angle, int velocity, ArrayList<Projectile> projectiles) {
		this.angle = angle;
		this.projectiles = projectiles;
		initialVelocity = velocity;
//		velocityComponents = calculateVelocity(velocity, angle);
		setInitialPosition(new RealCoordinates(0, 0));
		
	}

	//set initial positions
	private void setInitialPosition(RealCoordinates somePosition) {
		initialPosition = somePosition;
		currentPosition = somePosition;
	}
	
	//find velocity components
	public void calculateVelocity(int initialVelocity, int angle) {
		velocityComponents = new ArrayList<Double>();
		
		double angle_radians = Math.toRadians(angle);
		velocityComponents.add((initialVelocity * Math.cos(angle_radians)));
		velocityComponents.add((initialVelocity * Math.sin(angle_radians)));
		
	//	return velocityComponents;
	}
	
	public void incrementTimeBy(double timestep) {
		timeElapsed += timestep;
		// update position
		double xCoordinate = velocityComponents.get(0) * timeElapsed + initialPosition.xCoordinate;
		double yCoordinate = velocityComponents.get(1) * timeElapsed + 0.5 * (-9.8) * Math.pow(timeElapsed, 2) + initialPosition.yCoordinate;
		currentPosition.setXY(xCoordinate, yCoordinate);
	}
	
	//determine where the projectile will land
	public ArrayList<Integer> calculateDestination() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(1);
		temp.add(2);
		return temp;
	}
	
	public int calculateAngle(int initialVelocity, Target target) {
		return 0;
	}
	
	public int calculateInitialVelocity(int angle, Target target) {
		return 0;
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public void addProjectiles(Projectile projectile) {
		projectiles.add(projectile);
	}
	
	public void removeProjectiles(int index) {
		projectiles.remove(index);
	}
	public void DrawLauncher(Graphics g) {

	}
	
	public void incrementProjectileTime(double time) {
		for (Projectile projectile : projectiles) {
			projectile.incrementTimeBy(time, velocityComponents);
		}
	}
	public void DrawProjectile(Graphics g) {
		int i = 0;
		for (Projectile projectile : projectiles) {
			projectile.Draw(g);
			System.out.println("Projectile: " + i + " should draw");
			i++;
		}
	}	
}
