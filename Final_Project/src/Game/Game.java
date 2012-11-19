package Game;
import java.awt.*;
import javax.swing.*;



public class Game extends JPanel {
	
	int numTries;
	Hoop hoop;
	public Launcher launcher = new Launcher();
	Trajectile trajectile;
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	
	
	public void launch() {
		
	}
	
	public boolean checkCollision() {
		return false;
	}	
	
	public boolean checkHoop(Trajectile trajectile, Target hoop) {
		
		return false;
	}
	
}
