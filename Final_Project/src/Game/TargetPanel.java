package Game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;

public class TargetPanel extends JDialog {
	
	private static final long serialVersionUID = 1L;

	CPSound cannon_sound = new CPSound();
	
	JButton exitButton = new JButton("Exit");
	JButton answerButton = new JButton("Check Answer");
	JButton velocityButton = new JButton("Generate velocity");
	JButton angleButton = new JButton("Generate angle");
	
	JTextField angleInput = new JTextField(150);
	JTextField velocityInput = new JTextField(150);
	
	JPanel infoPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel angleInputPanel = new JPanel();
	JPanel velocityInputPanel = new JPanel();
	JPanel inputPanel = new JPanel();
	
	Random randomGenerator = new Random();
	
	
	
	public TargetPanel(Block block, Game game) {
		ButtonListener buttonListener = new ButtonListener(game);
		
		setSize(200, 400);
		setResizable(false);
		setTitle("Selected Target");
		setAlwaysOnTop(true);
		
		GridLayout infoGrid = new GridLayout(2, 0);
		infoPanel.setLayout(infoGrid);
		infoPanel.add(new JLabel("Distance (x-meters): " + Integer.toString((int)block.getBounds().getCenterX())));
		infoPanel.add(new JLabel("Height (y-meters): " + Integer.toString((int)block.getBounds().getCenterY())));
		infoPanel.setBorder(new TitledBorder(new EtchedBorder(), "Target Center Location"));
		
		
		inputPanel.setLayout(new GridLayout(2, 2));
		inputPanel.add(new JLabel(" Angle (degrees) "));
		inputPanel.add(angleInput);
		inputPanel.add(new JLabel(" Velocity (m/s) "));
		inputPanel.add(velocityInput);
		
		angleButton.addActionListener(buttonListener);
		velocityButton.addActionListener(buttonListener);
		answerButton.addActionListener(buttonListener);
		exitButton.addActionListener(buttonListener);
		
		buttonPanel.setLayout(new GridLayout(4, 0));
		buttonPanel.add(angleButton);
		buttonPanel.add(velocityButton);
		buttonPanel.add(answerButton);
		buttonPanel.add(exitButton);
		
		setLayout(new GridLayout(3, 0));
		add(infoPanel);
		add(inputPanel);
		add(buttonPanel);		
		
	}
	private class ButtonListener implements ActionListener {
		Game game;
		
		public ButtonListener(Game game) {
			this.game = game;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == exitButton) {
				dispose();
			}
			if (e.getSource() == answerButton) {
				cannon_sound.play("cannon.wav");
				int velocity = Integer.valueOf(velocityInput.getText());
				int angle = Integer.valueOf(angleInput.getText());
				game.launch(angle, velocity);
				game.launcher.updateAngle(angle);
				
			}
			if (e.getSource() == velocityButton) {
				int randomVelocity = randomGenerator.nextInt(10) + 71;
				velocityInput.setText(Integer.toString(randomVelocity));
				velocityInput.setEditable(false);
				angleInput.setEditable(true);
			
			}
			if (e.getSource() == angleButton) {
				int randomAngle = randomGenerator.nextInt(10) + 36;
				angleInput.setText(Integer.toString(randomAngle));
				angleInput.setEditable(false);
				velocityInput.setEditable(true);
				
			}
		}
	}	
}
