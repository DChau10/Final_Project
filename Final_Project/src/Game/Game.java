package Game;
//import game.Projectile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;



public class Game extends JPanel {	
			
	private static final long serialVersionUID = 1L;
	final double TIME = (1000.0 / 60.0) / 1000.0;
	int numTries;
	public boolean collided = false;
	Block box = new Block(80, 20, 40, 20);
	Rectangle b = new Rectangle(50, 50, 40, 20);
	public Launcher launcher = new Launcher();
	PixelCoordinates origin = new PixelCoordinates(new RealCoordinates(0, 0));
	ProjectilePath projectilePath = new ProjectilePath(45, 25, TIME);
	
	ArrayList<ArrayList<PixelCoordinates>> paths = new ArrayList<ArrayList<PixelCoordinates>>();
	ArrayList<Block> targets = new ArrayList<Block>();
	
	
	public Game() {
		// init
		setPreferredSize(new Dimension(1024, 640));

		Timer timer = new Timer(1000/60, drawFrame);
		timer.start();
		
		System.out.println(origin.yCoordinate);
		
		// setVisible
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		// flip along y
		Graphics2D g2d = (Graphics2D) g;
		g2d.scale(1, -1);
		g2d.translate(0, -getHeight());		
		  
		  
		g.setColor(Color.BLUE);
		g.drawLine((int) origin.xCoordinate, (int) origin.yCoordinate, (int) (origin.xCoordinate + origin.getPixelLength(800)), (int) origin.yCoordinate);
		g.drawLine((int) origin.xCoordinate, (int) origin.yCoordinate, (int) origin.xCoordinate, (int) (origin.yCoordinate + origin.getPixelLength(500)));
		
		//drawing a target
		box.Draw(g);
				
		// draw path
		projectilePath.draw(g);		
		
		// draw each projectile
		launcher.DrawProjectile(g);
		
		
		// remove projectile if off screen
		for (int i = 0; i < launcher.getProjectiles().size(); i++) {
			if (launcher.getProjectiles().get(i).getCurrentPosition().yCoordinate < 0) {				
				paths.add(launcher.getProjectiles().get(i).getTrajectory());
				launcher.removeProjectiles(i);				
				i = 0;
			}
		}
		
		for (int i = 0; i < paths.size(); i++) {
			for (PixelCoordinates pixelCoordinates : paths.get(i)) {
				g.setColor(Color.BLACK);
				g.fillOval((int) pixelCoordinates.xCoordinate - 1, (int) pixelCoordinates.yCoordinate - 1, 2, 2);
			}
		}
		collided = checkCollision();
		checkPreLaunchCollision(box);
	}
	
	public void drawX(Rectangle rect, Graphics g) {
		g.setColor(Color.CYAN);
		Rectangle boundingBox = box.getBounds();
		PixelCoordinates boxness_FUCK = new PixelCoordinates(new RealCoordinates(rect.x, rect.y));
		PixelCoordinates shit = new PixelCoordinates(new RealCoordinates(rect.width, rect.height));
		PixelCoordinates shtagain = new PixelCoordinates(new RealCoordinates(0,0));
		shit.xCoordinate -= shtagain.xCoordinate;
		shit.yCoordinate = Math.abs(shit.yCoordinate - shtagain.yCoordinate);
		
		g.drawRect((int) boxness_FUCK.xCoordinate, (int) boxness_FUCK.yCoordinate, (int) shit.xCoordinate, (int) shit.yCoordinate);
	//	System.out.println("assfuck" + rect.x + " " + rect.y + " , " + rect.width + "  " + rect.height);
		
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
		
	}
	
	public void launch(int angle, int velocity) {
	//	launcher.calculateVelocity(velocity, angle);
		launcher.addProjectiles(new Projectile(angle, velocity));
	}
	public void updatePath(int angle, int velocity) {
		projectilePath.recalculatePath(angle, velocity, TIME);
	}
	
	public boolean checkCollision() {
		Rectangle boundsForBox = box.getBounds().getBounds();
		for (int i = 0; i < launcher.getProjectiles().size(); i++) {
			
			
			Rectangle boundsForShot = launcher.getProjectiles().get(i).getBounds().getBounds();
			
			if (boundsForShot.intersects(boundsForBox)) {
				System.out.println("Collision detected");
				
				paths.add(launcher.getProjectiles().get(i).getTrajectory());				
				
				Projectile testProjectile = launcher.getProjectiles().get(i);				
				
//				System.out.println(testProjectile.getBounds().x + " " + testProjectile.getBounds().y + " , " + testProjectile.getBounds().width + "  " + testProjectile.getBounds().height);
//				System.out.println(boundsForShot.x + " " + boundsForShot.y + " , " + boundsForShot.width + "  " + boundsForShot.height);
//				System.out.println(box.getBounds().x + " " + box.getBounds().y + "  ,  " +  box.getBounds().width + "  " + box.getBounds().height);
				
				launcher.removeProjectiles(i);
				return true;				
			}
		}		
		return false;
	}	
	
	public boolean checkPreLaunchCollision(Block target) {
		for (int i = 0; i < projectilePath.pp.size(); i++) {
			if (projectilePath.pp.get(i).getBounds().intersects(target.getBounds())) {
				System.out.println("Heyo");
				return true;
			}
		}
		return false;
		
	}
//	public boolean checkHoop(Projectile projectile, Target target) {
//		if (checkCollision()) 
//			return true;
//		return false;
//	}
	
	ActionListener drawFrame = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			launcher.incrementProjectileTime(TIME);
			repaint();
		}
	};
	
}
