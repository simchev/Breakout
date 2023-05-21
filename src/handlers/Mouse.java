package handlers;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Mouse {
	
	private static Point currentPosition = null;
	private static Point previousPosition = null;
	
	private static boolean wasPressed;
	private static boolean isPressed;
	private static boolean isReleased;
	private static boolean isClicked;
	
	public static void update(Point p) {
		// position
		previousPosition = currentPosition;
		currentPosition = p;
		
		isClicked = false;
		isReleased = false;
		wasPressed = isPressed;
	}
	
	public static boolean isMoving() {
		if (!onScreen()) return false;
		return (previousPosition.x != currentPosition.x || previousPosition.y != currentPosition.y);
	}
	
	public static boolean isPressed() { 
		if (!onScreen()) return false;
		return isPressed && !wasPressed; 
	}
	
	public static boolean isClicked() {
		if (!onScreen()) return false;
		return isClicked; 
	}
	
	public static boolean isReleased() { 
		if (!onScreen()) return false;
		return isReleased; 
	}
	
	public static int getX() throws NullPointerException { 
		if (!onScreen()) throw new NullPointerException();
		return currentPosition.x; 
	}
	
	public static int getY() throws NullPointerException {
		if (!onScreen()) throw new NullPointerException();
		return currentPosition.y; 
	}
	public static void setPress() { isPressed = true; }
	public static void setClick() { isClicked = true; }
	public static void setReleased() { isPressed = false; isReleased = true; }
	
	public static boolean intersectsWith(Rectangle2D rec) {
		if (!onScreen() || rec == null) return false;
		int x = currentPosition.x;
		int y = currentPosition.y;
		return (x >= rec.getX() && x < (rec.getX() + rec.getWidth()) && y >= rec.getY() && y < (rec.getY() + rec.getHeight()));
	}
	
	public static boolean onScreen() {
		if (currentPosition != null) return true;
		else return false;
	}
}
