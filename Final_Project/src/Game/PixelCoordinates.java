package Game;

public class PixelCoordinates extends Coordinates {
	
	public static final double PIXELS_TO_METERS_RATIO = 950.0 / 250.0;
	public static final int RESOLUTION_Y = 640;

	public PixelCoordinates(RealCoordinates realCoordinates) {
		super();
		xCoordinate = (realCoordinates.xCoordinate * PIXELS_TO_METERS_RATIO) + 100;
		yCoordinate = (RESOLUTION_Y - (realCoordinates.yCoordinate * PIXELS_TO_METERS_RATIO)) - 100;
	}

}
