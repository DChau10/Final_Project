package Game;

public class PixelCoordinates extends Coordinates {
	
	private double angle;	
	private double scale = Game.screenSize.getHeight() * .0035;
	
	public PixelCoordinates(RealCoordinates realCoordinates) {
		super();		
		xCoordinate = realCoordinates.xCoordinate * scale + 15 * scale;
		yCoordinate = realCoordinates.yCoordinate * scale + 15 * scale;
	}
	
	public double getPixelLength(int length) {
		return length * scale;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public double getAngle() {
		return angle;
	}
}
