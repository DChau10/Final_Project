package Game;
import java.awt.BorderLayout;

import javax.swing.*;

public class DisplayPanel extends JFrame {
	
	Game game;
	
	public DisplayPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Fire ze missiles!");
		setSize(1024, 740);
		setResizable(false);
		
		game = new Game();
		add(game, BorderLayout.NORTH);
		add(new ControlPanel(game), BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		DisplayPanel display = new DisplayPanel();
	}
}
