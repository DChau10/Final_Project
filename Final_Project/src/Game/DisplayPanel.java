package Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class DisplayPanel extends JFrame {
	
	Game game;
	static CPSound bgMusic = new CPSound();
	public DisplayPanel() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Fire ze missiles!");
	//	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(Game.screenSize);
		setResizable(false);
		
		game = new Game();
		add(game, BorderLayout.CENTER);
		add(new ControlPanel(game), BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		DisplayPanel display = new DisplayPanel();
	//	bgMusic.play("nyancat.wav");
	}
}
