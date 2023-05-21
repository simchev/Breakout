package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class MapObject {

	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	protected Color color;
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public Rectangle getRec() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public int getX() { return (int)x; }
	public int getY() { return (int)y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
