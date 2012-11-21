package Game;

public abstract class Coordinates {

	public double xCoordinate;
	public double yCoordinate;
	
	public Coordinates() {
		xCoordinate = 0;
		yCoordinate = 0;
	}

	public Coordinates(double xCoordinate, double yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	public void setXY(double x, double y) {
		xCoordinate = x;
		yCoordinate = y;
	}
}
