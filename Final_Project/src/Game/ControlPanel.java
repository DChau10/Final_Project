package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	Game game;
	JSlider forceSliderInput = new JSlider(5, 50);
	JSlider angleSliderInput = new JSlider(5, 85);
//	JTextField forceInput = new JTextField(5);
//	JTextField angleInput = new JTextField(5);
	JButton launchButton = new JButton("Launch");
	SliderListener sliderListener = new SliderListener();
	
	public ControlPanel(Game game) {
		this.game = game;
		
		add(new JLabel("Velocity (m/s): "));
		forceSliderInput.setMajorTickSpacing(5);
		forceSliderInput.setPaintTicks(true);
		forceSliderInput.addChangeListener(sliderListener);
		add(forceSliderInput);
		add(new JLabel("Angle (degrees): "));
		angleSliderInput.setMajorTickSpacing(5);
		angleSliderInput.setPaintTicks(true);
		angleSliderInput.addChangeListener(sliderListener);
		add(angleSliderInput);
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
			if (e.getSource() == launchButton) {
				int velocity = Integer.valueOf(forceSliderInput.getValue());
				int angle = Integer.valueOf(angleSliderInput.getValue());
				game.launch(angle, velocity);
			}
		}
	}
	
	private class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			game.updatePath(angleSliderInput.getValue(), forceSliderInput.getValue());
		}
	}
}
