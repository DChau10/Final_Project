package Game;
import java.util.ArrayList;


public class Launcher {
	
	private int intialVelocity;
	private int angle;
	private int x, y;
	
	public ArrayList<Integer> calculateVelocity(int initialVelocity, int angle) {
		ArrayList<Integer> fo = new ArrayList<Integer>();
		fo.add(1);
		fo.add(2);
		return fo;
	}
	
	public ArrayList<Integer> calculateDestination() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(1);
		temp.add(2);
		return temp;
	}
	
	public int calculateAngle(int initialVelocity, Target target) {
		return 0;
	}
	
	public int calculateInitialVelocity(int angle, Target target) {
		return 0;
	}
}
