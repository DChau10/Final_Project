package Game;

import java.awt.Dimension;
import java.awt.Toolkit;

public class PixelCoordinates extends Coordinates {
	
//	public static final double PIXELS_TO_METERS_RATIO = 950.0 / 250.0;
//	public static final int RESOLUTION_Y = 640;	
	
	public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private double scale = screenSize.getHeight() * .005;
	
	public PixelCoordinates(RealCoordinates realCoordinates) {
		super();
//		xCoordinate = (realCoordinates.xCoordinate * PIXELS_TO_METERS_RATIO) + 100;
//		yCoordinate = ((realCoordinates.yCoordinate * PIXELS_TO_METERS_RATIO)) + 100;
		
		
		xCoordinate = realCoordinates.xCoordinate * scale + 100;
		yCoordinate = realCoordinates.yCoordinate * scale + 100;
	}
	
	public double getPixelLength(int length) {
		return length * scale;
	}

}
