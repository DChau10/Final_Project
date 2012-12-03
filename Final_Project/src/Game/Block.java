package Game;
import java.awt.*;

public class Block extends Target {
	
	private int length, width;
	RealCoordinates realCoords, realCoords2;
	PixelCoordinates pixelCoords, pixelCoords2;
	private Image image;
	
	// x and y denotes TOP LEFT CORNER of box, NOT CENTER
	// like it is in Projectile
	// x and y are in meters
	// width and legnth are also in memters
	public Block(int x, int y, int width, int length, Image image) {
		super(x, y);
		this.length = length;
		this.width = width;
		this.image = image;
		realCoords = new RealCoordinates(x, y);
		realCoords2 = new RealCoordinates(width, length);
		pixelCoords = new PixelCoordinates(realCoords);
		pixelCoords2 = new PixelCoordinates(realCoords2);
		PixelCoordinates pixelCoordOrigin = new PixelCoordinates(new RealCoordinates(0,0));
		System.out.println(pixelCoords2.xCoordinate + " " + pixelCoords2.yCoordinate);
		pixelCoords2.xCoordinate = pixelCoords2.xCoordinate - pixelCoordOrigin.xCoordinate;
		pixelCoords2.yCoordinate = Math.abs(pixelCoords2.yCoordinate - pixelCoordOrigin.yCoordinate);
		System.out.println(pixelCoordOrigin.xCoordinate + " " + pixelCoordOrigin.yCoordinate);
		System.out.println(pixelCoords2.xCoordinate + " " + pixelCoords2.yCoordinate);
	}
	
	public Rectangle getBounds() {
	
		return new Rectangle(x, y, width, length);
	}
	
	@Override
	public void Draw(Graphics g) {
		
		g.setColor(Color.BLACK);
	//	g.drawRect((int)pixelCoords.xCoordinate, (int)pixelCoords.yCoordinate, (int)pixelCoords2.xCoordinate, (int)pixelCoords2.yCoordinate);
		//g.drawImage((int)pixelCoords.xCoordinate, (int)pixelCoords.yCoordinate, (int)pixelCoords2.xCoordinate, (int)pixelCoords2.yCoordinate, null);
		g.drawImage(image, (int)pixelCoords.xCoordinate, (int)pixelCoords.yCoordinate, (int)pixelCoords2.xCoordinate, (int)pixelCoords2.yCoordinate, null);
		
	}

}
