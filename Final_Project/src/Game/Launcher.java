package Game;
import java.util.ArrayList;
import java.awt.*;


public class Launcher {
	
	private int angle;
//	private int x, y;
//	private double timeElapsed;
//	private Projectile projectile;
	private ArrayList<Projectile> projectiles;
//	private ArrayList<Double> velocityComponents;
//	private RealCoordinates initialPosition, currentPosition;
	private Image image;
	//constructor
	public Launcher() {
		projectiles = new ArrayList<Projectile>();
	}
	
	public Launcher(Image image) {
		projectiles = new ArrayList<Projectile>();
		this.image = image;	
		angle = 45;
	}
	
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
	}
	
	public void removeProjectiles(int index) {
		projectiles.remove(index);
	}
	
	public void updateAngle(int angle) {
		this.angle = angle;
	}
	
	public void DrawLauncher(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.BLACK);
		PixelCoordinates origin = new PixelCoordinates(new RealCoordinates(0, 0));
		
		
		g2d.translate(origin.xCoordinate, origin.yCoordinate);
		g2d.rotate(Math.toRadians(angle));
		g.drawImage(image,(int) (0 - origin.getPixelLength(30)), (int) (0 - origin.getPixelLength(30)), 
				(int) (origin.getPixelLength(60)), (int) (origin.getPixelLength(60)), null);
		
		
		g2d.rotate(Math.toRadians(360 -angle));
		g2d.translate(-origin.xCoordinate, -origin.yCoordinate);
	}
	
	public void incrementProjectileTime(double time) {
		for (Projectile projectile : projectiles) {
			projectile.incrementTimeBy(time);
		}
	}
	public void DrawProjectile(Graphics g) {
		
		for (Projectile projectile : projectiles) {		
			projectile.drawFollowPath(g);			
			projectile.Draw(g);
		}
	}	
}
