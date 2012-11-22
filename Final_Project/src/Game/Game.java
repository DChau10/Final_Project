package Game;
//import game.Projectile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class Game extends JPanel {
	
	private static final long serialVersionUID = 1L;
	final double time = (1000.0 / 60.0) / 1000.0;
	int numTries;
	Hoop hoop;
	public Launcher launcher = new Launcher();
	PixelCoordinates origin = new PixelCoordinates(new RealCoordinates(0, 0));
	ProjectilePath projectilePath = new ProjectilePath(45, 25, time);
	
	public Game() {
		// init
		setPreferredSize(new Dimension(1024, 640));

		Timer timer = new Timer(1000/60, drawFrame);
		timer.start();
		
		// setVisible
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// draw origin
		g.setColor(Color.BLUE);
		g.drawLine((int) origin.xCoordinate - 50, (int) origin.yCoordinate, (int) origin.xCoordinate + 875, (int) origin.yCoordinate);
		g.drawLine((int) origin.xCoordinate, (int) origin.yCoordinate + 50, (int) origin.xCoordinate, (int) origin.yCoordinate - 500);
		
		// draw path
		projectilePath.draw(g);		
		
		// draw each projectile
		launcher.DrawProjectile(g);
		
		
		// remove projectile if off screen
		for (int i = 0; i < launcher.getProjectiles().size(); i++) {
			if (launcher.getProjectiles().get(i).getCurrentPosition().yCoordinate <= 0) {
				launcher.removeProjectiles(i);
		//		System.out.println("Projectile: " + i + " removed");
				i = 0;
			}
		}
	}
	
	public void launch(int angle, int velocity) {
	//	launcher.calculateVelocity(velocity, angle);
		launcher.addProjectiles(new Projectile(angle, velocity));
	}
	public void updatePath(int angle, int velocity) {
		projectilePath.recalculatePath(angle, velocity, time);
	}
	
	public boolean checkCollision() {
		return false;
	}	
	
	public boolean checkHoop(Projectile projectile, Target hoop) {
		
		return false;
	}
	
	ActionListener drawFrame = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			launcher.incrementProjectileTime(time);
			repaint();
		}
	};
	
}
