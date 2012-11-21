package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlPanel extends JPanel {

	Game game;
	JTextField forceInput = new JTextField(5);
	JTextField angleInput = new JTextField(5);
	JButton launchButton = new JButton("Launch");
	
	public ControlPanel(Game game) {
		this.game = game;
		
		add(new JLabel("Force (m/s): "));
		add(forceInput);
		add(new JLabel("Angle (degrees): "));
		add(angleInput);
		launchButton.addActionListener(new ButtonListener());
		add(launchButton);
		
		// set border
		setBorder(new TitledBorder(new EtchedBorder(), "Control Panel"));
		
		// setVisible
		setVisible(true);
	}
	
	private class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int velocity = Integer.valueOf(forceInput.getText());
			int angle = Integer.valueOf(angleInput.getText());
			game.launch(angle, velocity);
			
		}
	}
}
