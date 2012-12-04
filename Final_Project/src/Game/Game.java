package Game;
//import game.Projectile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;



public class Game extends JPanel {	
			
	private static final long serialVersionUID = 1L;
	public boolean draw_path = false;
	static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static final double TIME = (1000.0 / 60.0) / 1000.0;
	CPSound pow_meow = new CPSound();
	int numTries;
	public boolean collided = false, generated = false;
	public Launcher launcher;
	PixelCoordinates origin = new PixelCoordinates(new RealCoordinates(0, 0));
	ProjectilePath projectilePath = new ProjectilePath(45, 60, TIME);
	
	ArrayList<ArrayList<PixelCoordinates>> paths = new ArrayList<ArrayList<PixelCoordinates>>();
	ArrayList<Block> targets = new ArrayList<Block>();
	ArrayList<Pow> pows = new ArrayList<Pow>();
	
	Random randomGenerator = new Random();
	Image background, cannonball, crate, pow, cannon;
	Dimension backgroundSize;
	int targetX, targetY;
	
	TargetPanel targetPanel;
	
	public Game() {
		// init
		setPreferredSize(new Dimension(1024, 640));
		loadImages();
		launcher = new Launcher(cannon);
		
		Timer timer = new Timer(1000/60, drawFrame);
		timer.start();
		
		// setVisible
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		// flip along y
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, screenSize.width, screenSize.height, null);
		g2d.scale(1, -1);
		g2d.translate(0, -getHeight());		
		
		g.setColor(Color.BLUE);
		g.drawLine((int) origin.xCoordinate, (int) origin.yCoordinate, (int) (origin.xCoordinate + origin.getPixelLength(800)), (int) origin.yCoordinate);
		g.drawLine((int) origin.xCoordinate, (int) origin.yCoordinate, (int) origin.xCoordinate, (int) (origin.yCoordinate + origin.getPixelLength(500)));
		
		//drawing a target
		for (Block blocks : targets) {
			blocks.Draw(g);
		}
				
		// draw path
		if (draw_path == true){
			projectilePath.draw(g);		
		}
			
		// draw each projectile
		launcher.DrawProjectile(g);
		
		//draw launcher
		launcher.DrawLauncher(g);
		
		// draw pows
		for (Pow pow : pows) {
			pow.draw(g);
		}
		// remove pows if dead
		for (int i = 0; i < pows.size(); ++i) {
			if (pows.get(i).isDead()) {
				pows.remove(i);
				i = 0;
			}
		}
		
		// remove projectile if off screen
		for (int i = 0; i < launcher.getProjectiles().size(); i++) {
			if (launcher.getProjectiles().get(i).getCurrentPosition().yCoordinate < 0) {				
				paths.add(launcher.getProjectiles().get(i).getTrajectory());
				launcher.removeProjectiles(i);				
				i = 0;
			}
		}
		
//		for (int i = 0; i < paths.size(); i++) {
//			for (PixelCoordinates pixelCoordinates : paths.get(i)) {
//				g.setColor(Color.BLACK);
//				g.fillOval((int) pixelCoordinates.xCoordinate - 1, (int) pixelCoordinates.yCoordinate - 1, 2, 2);
//			}
//		}
		collided = checkCollision();
	}
	
	public int getFrameHeight() {
		return getHeight();
	}
	public void loadImages() {
		background = new ImageIcon(this.getClass().getResource("Background.jpg")).getImage();
		cannonball = new ImageIcon(this.getClass().getResource("nyan.png")).getImage();
		cannon = new ImageIcon(this.getClass().getResource("Cannon.png")).getImage();
		pow = new ImageIcon(this.getClass().getResource("Pow.png")).getImage();
		crate = new ImageIcon(this.getClass().getResource("Crate.png")).getImage();
	}
	
	public void generateTargets() {
		targets.clear();
		for (int i = 0; i < 5; i++) {
			targetX = randomGenerator.nextInt(200) + 170;
			targetY = randomGenerator.nextInt(80);
			targets.add(new Block(targetX, targetY, 15, 15, crate));
			Rectangle bounds = targets.get(i).getBounds();
			
			if (targets.size() > 1) {			
				for (int j = 0; j < targets.size()-1; j++) {
					if (targets.get(j).getBounds().intersects(bounds)) {
						targets.remove(i);
						targetX = randomGenerator.nextInt(200) + 170;
						targetY = randomGenerator.nextInt(80);
						targets.add(new Block(targetX, targetY, 15, 15, crate));
						bounds = targets.get(i).getBounds();
						j = 0;						
					} 
				}				
			}			
		}		
	}
	
	public ArrayList<Block> getTargets() {
		return targets;
	}

	
	public void launch(int angle, int velocity) {
		launcher.addProjectiles(new Projectile(angle, velocity, cannonball));
	}
	public void updatePath(int angle, int velocity) {
		projectilePath.recalculatePath(angle, velocity, TIME);
	}
	
	public boolean checkCollision() {
		
		for (int i = 0; i < launcher.getProjectiles().size(); i++) {
			Rectangle boundsForShot = launcher.getProjectiles().get(i).getBounds().getBounds();
			
			for (int j = 0; j < targets.size(); j++) {
				Rectangle boundsForBox = targets.get(j).getBounds().getBounds();			
			
				if (boundsForShot.intersects(boundsForBox)) {
					
					paths.add(launcher.getProjectiles().get(i).getTrajectory());							
					
					// Start POW animation					
					RealCoordinates someRealCoords = launcher.getProjectiles().get(i).getCurrentPosition();
					pows.add(new Pow(someRealCoords, pow));
					pow_meow.play("meow.wav");
					
					launcher.removeProjectiles(i);
					targets.remove(j);
					

					return true;				
				}
			}
		}		
		return false;
	}	

	
	ActionListener drawFrame = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			launcher.incrementProjectileTime(TIME);
			repaint();
		}
	};	
	
}
