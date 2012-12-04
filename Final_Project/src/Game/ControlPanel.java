package Game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	CPSound cannon_sound = new CPSound();
	Game game;
	JSlider velocitySliderInput = new JSlider(40, 80);
	JSlider angleSliderInput = new JSlider(5, 85);
	JTextField velocityInput = new JTextField(5);
	JTextField angleInput = new JTextField(5);
	JButton launchButton = new JButton("Launch");
	JButton generateButton = new JButton("Generate Targets");
	JButton pathButton = new JButton("Path On/Off");
	SliderListener sliderListener = new SliderListener();
	TextboxListener textboxListener = new TextboxListener();
	ButtonListener buttonListener = new ButtonListener();
	
	JPanel buttonPanel = new JPanel();
	JPanel anglePanel = new JPanel();
	JPanel velocityPanel = new JPanel();
	
	public ControlPanel(Game game) {
		this.game = game;
		
		
		
		velocitySliderInput.setMajorTickSpacing(5);
		velocitySliderInput.setPaintTicks(true);
		velocitySliderInput.createStandardLabels(5);
		velocitySliderInput.setPaintLabels(true);
		velocitySliderInput.addChangeListener(sliderListener);		
		velocityInput.addActionListener(textboxListener);
		
		velocityPanel.setLayout(new BorderLayout());
		velocityPanel.add(velocitySliderInput, BorderLayout.CENTER);
		velocityPanel.add(velocityInput, BorderLayout.SOUTH);
		velocityPanel.setBorder(new TitledBorder(new EtchedBorder(), "Velocity (m/s) "));
		
	
		
		angleSliderInput.setMajorTickSpacing(10);
		angleSliderInput.createStandardLabels(10);
		angleSliderInput.setPaintLabels(true);
		angleSliderInput.setPaintTicks(true);
		angleSliderInput.addChangeListener(sliderListener);
		angleInput.addActionListener(textboxListener);
		
		anglePanel.setLayout(new BorderLayout());
		anglePanel.add(angleSliderInput, BorderLayout.CENTER);		
		anglePanel.add(angleInput, BorderLayout.SOUTH);
		anglePanel.setBorder(new TitledBorder(new EtchedBorder(), "Angle (degrees) "));
		
		
		launchButton.addActionListener(buttonListener);
		pathButton.addActionListener(buttonListener);
		generateButton.addActionListener(buttonListener);
		
		GridLayout buttonGrid = new GridLayout(0, 3);
		buttonPanel.setLayout(buttonGrid);
		buttonPanel.add(launchButton);
		buttonPanel.add(generateButton);
		buttonPanel.add(pathButton);
		
		GridLayout controlGrid = new GridLayout(0, 3);
		setLayout(controlGrid);
		add(velocityPanel);
		add(anglePanel);
		add(buttonPanel);
		
		// setVisible
		setVisible(true);
	}
	
	private class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == launchButton) {
				cannon_sound.play("cannon.wav");
				int velocity = Integer.valueOf(velocitySliderInput.getValue());
				int angle = Integer.valueOf(angleSliderInput.getValue());
				game.launch(angle, velocity);			
			}
			if (e.getSource() == generateButton) {
				game.generateTargets();
			}
			if (e.getSource() == pathButton) {
				if (game.draw_path == false) { game.draw_path = true;}
				else if (game.draw_path == true) { game.draw_path = false;}
			}
		}
	}
	
	private class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			game.updatePath(angleSliderInput.getValue(), velocitySliderInput.getValue());
			game.launcher.updateAngle(angleSliderInput.getValue());
			
			velocityInput.setText(Integer.toString(velocitySliderInput.getValue()));
			angleInput.setText(Integer.toString(angleSliderInput.getValue()));
		}		
	}
	
	private class TextboxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angleSliderInput.setValue(Integer.parseInt(angleInput.getText()));
			velocitySliderInput.setValue(Integer.parseInt(velocityInput.getText()));
			
		}
		
	}
}
