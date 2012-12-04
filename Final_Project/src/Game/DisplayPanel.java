package Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

public class DisplayPanel extends JFrame {
	
	private static final long serialVersionUID = 1L;
	Game game;
	static CPSound bgMusic = new CPSound();
	TargetPanel targetPanel;
	public DisplayPanel() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Nyan Cannon!!");
		Dimension frame = Game.screenSize;
		frame.setSize(frame.getWidth() - 100, frame.getHeight() - 100);
		setSize(frame);
		setResizable(false);
		
		game = new Game();
		add(game, BorderLayout.CENTER);
		add(new ControlPanel(game), BorderLayout.SOUTH);
		
		setVisible(true);
		
		addMouseListener(new targetListener(game));
	}
	
	private class targetListener implements MouseListener {
		Game game;
		public targetListener(Game game) {
			this.game = game;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
		
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent e) {
			ArrayList<Block>targets = game.getTargets();
			Point clickedPoint = e.getPoint();
			clickedPoint.y = game.getFrameHeight() - clickedPoint.y;
			System.out.println(clickedPoint.x + " ,,," + clickedPoint.y);
			for (Block block : targets) {
				Rectangle target = new Rectangle((int)block.pixelCoords.xCoordinate, (int)block.pixelCoords.yCoordinate, (int)block.pixelCoords2.xCoordinate, (int)block.pixelCoords2.yCoordinate);
				System.out.println ((int)block.pixelCoords.xCoordinate + " , " + (int)block.pixelCoords2.xCoordinate);
				
				target.setBounds((int)block.pixelCoords.xCoordinate, (int)block.pixelCoords.yCoordinate, (int)block.pixelCoords2.xCoordinate, (int)block.pixelCoords2.yCoordinate);
				System.out.println("Bounds" + (int)block.pixelCoords.xCoordinate + " " + (int)block.pixelCoords.yCoordinate + " " + (int)block.pixelCoords2.xCoordinate + " " +
						(int)block.pixelCoords2.yCoordinate);
				
				if (target.contains(clickedPoint)) {
					targetPanel = new TargetPanel(block, game);
					targetPanel.setVisible(true);
					System.out.println("CLI=CKLKJ:LKJ");
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		DisplayPanel display = new DisplayPanel();
		bgMusic.play("nyancat.wav");
	}	
}
