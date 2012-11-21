package Game;

public class RealCoordinates extends Coordinates {

	public RealCoordinates(double xCoordinate, double yCoordinate) {
		super(xCoordinate, yCoordinate);
	}
	
	public void setXY(double x, double y) {
		xCoordinate = x;
		yCoordinate = y;
	}

}
