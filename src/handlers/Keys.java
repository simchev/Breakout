package handlers;

import java.awt.event.KeyEvent;

public class Keys {

	public static final int NUM_KEYS = 6;
	
	public static boolean[] keyState = new boolean[NUM_KEYS];
	public static boolean[] prevKeyState = new boolean[NUM_KEYS];
	
	public static int UP = 0;
	public static int LEFT = 1;
	public static int DOWN = 2;
	public static int RIGHT = 3;
	public static int ENTER = 4;
	public static int ESCAPE = 5;
	
	public static void keySet(int i, boolean b) {
		if (i == KeyEvent.VK_UP) keyState[UP] = b;
		else if (i == KeyEvent.VK_LEFT) keyState[LEFT] = b;
		else if (i == KeyEvent.VK_DOWN) keyState[DOWN] = b;
		else if (i == KeyEvent.VK_RIGHT) keyState[RIGHT] = b;
		else if (i == KeyEvent.VK_ENTER) keyState[ENTER] = b;
		else if (i == KeyEvent.VK_ESCAPE) keyState[ESCAPE] = b;
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}
		
	public static boolean isDown(int i) {
		return keyState[i];
	}
	
	public static boolean isPressed(int i) {
		return keyState[i] && !prevKeyState[i];
	}
	
	public static boolean isUnpressed(int i) {
		return prevKeyState[i] && !keyState[i];
	}
	
}
