package Game;
import java.util.ArrayList;
import java.awt.*;


public class Launcher {
	
	private int initialVelocity, angle;
//	private int x, y;
//	private double timeElapsed;
//	private Projectile projectile;
	private ArrayList<Projectile> projectiles;
//	private ArrayList<Double> velocityComponents;
//	private RealCoordinates initialPosition, currentPosition;
	
	//constructor
	public Launcher() {
		projectiles = new ArrayList<Projectile>();
	}
	
	public Launcher(int angle, int velocity, ArrayList<Projectile> projectiles) {
		this.angle = angle;
		this.projectiles = projectiles;
		initialVelocity = velocity;		
	}

	//set initial positions
//	private void setInitialPosition(RealCoordinates somePosition) {
//		initialPosition = somePosition;
//		currentPosition = somePosition;
//	}
	
	//find velocity components
//	public void calculateVelocity(int initialVelocity, int angle) {
//		velocityComponents = new ArrayList<Double>();
//		
//		double angle_radians = Math.toRadians(angle);
//		velocityComponents.add((initialVelocity * Math.cos(angle_radians)));
//		velocityComponents.add((initialVelocity * Math.sin(angle_radians)));
//		
//	//	return velocityComponents;
//	}
	
//	public void incrementTimeBy(double timestep) {
//		timeElapsed += timestep;
//		// update position
//		double xCoordinate = velocityComponents.get(0) * timeElapsed + initialPosition.xCoordinate;
//		double yCoordinate = velocityComponents.get(1) * timeElapsed + 0.5 * (-9.8) * Math.pow(timeElapsed, 2) + initialPosition.yCoordinate;
//		currentPosition.setXY(xCoordinate, yCoordinate);
//	}
	
	//determine where the projectile will land
	public ArrayList<Integer> calculateDestination(Projectile projectile) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		double time = 2 * (projectile.getVelocityComponents().get(1)) * 9.8;
		
		temp.add((int) (projectile.getVelocityComponents().get(0) * time));
		temp.add((int) (projectile.getVelocityComponents().get(1) * time - .5*9.82*time*time));
		
		return temp;
	}
	
	public double calculateAngle(int initialVelocity, Target target) {
	
		double angle = Math.asin((double)target.getX() * 9.8 / (double)(initialVelocity * initialVelocity)) / 2.0 ;
		return Math.toDegrees(angle);
	}
	
	public double calculateInitialVelocity(int angle, Target target) {
		double velocity = Math.sqrt( target.getX() * 9.8 / Math.sin(Math.toRadians(angle * 2)));
		return velocity;
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public void addProjectiles(Projectile projectile) {
		projectiles.add(projectile);
	//	projectiles.get(projectiles.size()-1).setVelocityComponents(velocityComponents);
	}
	
	public void removeProjectiles(int index) {
		projectiles.remove(index);
	}
	public void DrawLauncher(Graphics g) {

	}
	
	public void incrementProjectileTime(double time) {
		for (Projectile projectile : projectiles) {
			projectile.incrementTimeBy(time);
		}
	}
	public void DrawProjectile(Graphics g) {
		int i = 0;
		for (Projectile projectile : projectiles) {
			projectile.Draw(g);
			projectile.drawFollowPath(g);
	//		System.out.println("Projectile: " + i + " should draw");
			i++;
		}
	}	
}
