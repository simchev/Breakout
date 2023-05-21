package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Brick extends MapObject {
	
	// bricks
	public static int NORMALBRICK = 0;
	public static int LIFEBRICK = 1;

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int)x + 1, (int)y + 1, width - 2, height - 2);
	}
}
