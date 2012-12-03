package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


public class Pow {
	
	RealCoordinates currentPosition;
	double lifeTimer = 1.0;
	Image image;

	public Pow(RealCoordinates currentPosition, Image image) {
		this.currentPosition = currentPosition;
		this.image = image;
	}
	
	public boolean isDead() {
		return lifeTimer <= 0;
	}
	
	public void draw(Graphics g) {
		lifeTimer -= Game.TIME;
		PixelCoordinates pixelCoords = new PixelCoordinates(currentPosition);
		g.setColor(Color.BLACK);
		g.drawImage(image, (int) pixelCoords.xCoordinate - 20, (int) pixelCoords.yCoordinate - 20, 40, 40, null);
	}
	
}
