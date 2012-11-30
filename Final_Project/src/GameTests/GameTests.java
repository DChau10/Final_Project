package GameTests;

import Game.*;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameTests {
	private static Game game;
	private static Projectile projectile;
	@BeforeClass
	public static void initialize() {
		game = new Game();
		projectile = new Projectile(30, 10);
	}	
	
	@Test
	public void testCalculateVelocityComponents() {
		//given initial velocity of 10, @30 degrees
		//test x and y initial velocity
		game.launcher.addProjectiles(projectile);
//		System.out.println("x" + game.launcher.getProjectiles().get(0).getVelocityComponents().get(0) + " = " + 10*Math.cos(Math.toRadians(30)));
		
		assertEquals(10*Math.cos(Math.toRadians(30)), game.launcher.getProjectiles().get(0).getVelocityComponents().get(0), 1);
		assertEquals(10*Math.sin(Math.toRadians(30)), game.launcher.getProjectiles().get(0).getVelocityComponents().get(1), 1);
		
	
	}
	//physics*********************************************
	// r = ( (initial velocity) ^ 2 * sin ( 2 * angle) ) / g
	//?derp?***********************************************
	
	@Test
	public void testCalculateDestination() {
		game.launcher.addProjectiles(projectile);
		
		//given intial velocity of 10, @30 degrees, test landing destination
		double time = (2*game.launcher.getProjectiles().get(0).getVelocityComponents().get(1))*9.8;
	
		int x = (int) (game.launcher.getProjectiles().get(0).getVelocityComponents().get(0) * time);
		int y = (int) (game.launcher.getProjectiles().get(0).getVelocityComponents().get(1) * time - .5*9.82*time*time);
		assertEquals(x, game.launcher.calculateDestination(projectile).get(0), 1);
		assertEquals(y, game.launcher.calculateDestination(projectile).get(1), 1);
		
	}
	
	
	@Test
	public void testCalculateAngle() {
		//given intial velocity and destination, calculate angle 
		//initial velocity of 30, 50 meters away
		Target target = (Target) new Hoop(50, 0, 10);		
		assertEquals(16, game.launcher.calculateAngle(30, target), 1);
	}
	

	
	@Test
	public void testCalculateInitialVelocity() {
		//given initial angle and destination, calculate initial velocity. 
		// initial velocity = sqrt( r * g / sin ( 2 * angle ) )		
		
		//with angle of 16, 50 meters away:
		Target target = new Hoop(50, 0, 10);
		assertEquals(30, game.launcher.calculateInitialVelocity(16, target), 1);
		
	
	}
	
//	@Test 
//	public void testPassHoop() throws InterruptedException {
//		double timestep = (1000.0 / 60.0) / 1000.0;
//		boolean collided = false;
//		//Given a trajectile path and target, check to see if the path crosses the target
//		//Projectile trajectile = new Projectile(45, 40);
//		Block target = new Block(50, 20, 200, 100);
//	//	for (double i = 0; i < 10; i += timestep)
//		
//		game.launch(45,  40);
//		wait(4);
//		assertTrue(game.collided);
//		
//	}

}
