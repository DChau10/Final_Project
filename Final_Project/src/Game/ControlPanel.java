package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	Game game;
	JSlider forceSliderInput = new JSlider(40, 80);
	JSlider angleSliderInput = new JSlider(5, 85);
	JTextField forceInput = new JTextField(5);
	JTextField angleInput = new JTextField(5);
	JButton launchButton = new JButton("Launch");
	JButton generateButton = new JButton("Generate Targets");
	JButton pathButton = new JButton("Show Path");
	SliderListener sliderListener = new SliderListener();
	TextboxListener textboxListener = new TextboxListener();
	
	public ControlPanel(Game game) {
		this.game = game;
		
		
		add(new JLabel("Velocity (m/s): "));
		forceSliderInput.setMajorTickSpacing(5);
		forceSliderInput.setPaintTicks(true);
		forceSliderInput.createStandardLabels(5);
		forceSliderInput.setPaintLabels(true);
		forceSliderInput.addChangeListener(sliderListener);
		add(forceSliderInput);
		add(new JLabel("Angle (degrees): "));
		angleSliderInput.setMajorTickSpacing(10);
		angleSliderInput.createStandardLabels(10);
		angleSliderInput.setPaintLabels(true);
		angleSliderInput.setPaintTicks(true);
		angleSliderInput.addChangeListener(sliderListener);
		add(angleSliderInput);
		launchButton.addActionListener(new ButtonListener());
		add(launchButton);
		generateButton.addActionListener(new ButtonListener());
		add(generateButton);
		
		forceInput.addActionListener(textboxListener);
		add(forceInput);
		angleInput.addActionListener(textboxListener);
		add(angleInput);
		
		
		// set border
		setBorder(new TitledBorder(new EtchedBorder(), "Control Panel"));
		
		// setVisible
		setVisible(true);
	}
	
	private class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == launchButton) {
				int velocity = Integer.valueOf(forceSliderInput.getValue());
				int angle = Integer.valueOf(angleSliderInput.getValue());
				game.launch(angle, velocity);
			//	game.launch(Integer.parseInt(angleInput.getText()), Integer.parseInt(forceInput.getText()));
			}
			if (e.getSource() == generateButton) {
				game.generateTargets();
			}
			if (e.getSource() == pathButton) {
				
			}
		}
	}
	
	private class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			game.updatePath(angleSliderInput.getValue(), forceSliderInput.getValue());
			game.launcher.updateAngle(angleSliderInput.getValue());
			
			forceInput.setText(Integer.toString(forceSliderInput.getValue()));
			angleInput.setText(Integer.toString(angleSliderInput.getValue()));
		}		
	}
	
	private class TextboxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angleSliderInput.setValue(Integer.parseInt(angleInput.getText()));
			forceSliderInput.setValue(Integer.parseInt(forceInput.getText()));
			
		}
		
	}
}
