package GameTests;
import Game.*;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameTests {
	private static Game game;
	@BeforeClass
	public static void initialize() {
		game = new Game();
	}
	
	
	@Test
	public void testCalculateVelocityComponents() {
		//given initial velocity of 10, @30 degrees
		//test x and y initial velocity
//		assertEquals(10*Math.cos(30), game.launcher.calculateVelocity(10, 30).get(0), 1);
//		assertEquals(10*Math.sin(30), game.launcher.calculateVelocity(10, 30).get(1), 1);
//		
		
	}
	//physics*********************************************
	// r = ( (initial velocity) ^ 2 * sin ( 2 * angle) ) / g
	//?derp?***********************************************
	
	@Test
	public void testCalculateDestination() {
		//given intial velocity of 10, @30 degrees, test landing destination
//		double time = (2*game.launcher.calculateVelocity(10, 30).get(1))*9.8;
//		int x = (int) (game.launcher.calculateVelocity(10, 30).get(0) * time);
//		int y = (int) (game.launcher.calculateVelocity(10, 30).get(1) * time - .5*9.82*time*time);
//		assertEquals(x, game.launcher.calculateDestination().get(0), 1);
//		assertEquals(10, game.launcher.calculateDestination().get(1), 1);
	}
	
	@Test
	public void testCalculateAngle() {
		//given intial velocity and destination, calculate angle 
		//initial velocity of 30, 50 meters away
		Target target = (Target) new Hoop(50, 0, 10);
		assertEquals(16, game.launcher.calculateAngle(20, target), 1);
	}
	
	@Test 
	public void testPassHoop() {
		//Given a trajectile path and target, check to see if the path crosses the target
		Projectile trajectile = new Projectile(30, 16);
		Target target = new Hoop(50, 0, 10);
		
		assertTrue(game.checkHoop(trajectile, target));
		
	}
	
	@Test
	public void testCalculateInitialVelocity() {
		//given initial angle and destination, calculate initial velocity. 
		// initial velocity = sqrt( r * g / sin ( 2 * angle ) )		
		
		//with angle of 16, 50 meters away:
		Target target = new Hoop(50, 0, 10);
		assertEquals(30, game.launcher.calculateInitialVelocity(16, target));
		
	
	}
	
	

}
