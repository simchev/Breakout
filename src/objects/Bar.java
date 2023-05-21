package objects;

import java.awt.Color;

import handlers.Couleurs;
import handlers.Mouse;
import main.Game;

public class Bar extends MapObject{
	
	public Bar(int width, int height, int y, Color color) {
		this.width = width;
		this.height = height;
		this.y = y;
		this.color = color;
	}
	
	public void setX(double x) { this.x = x; }
	public void setWidth(int width) { this.width = width; }
	
	public void update() {
		try {
			setX(Mouse.getX() - width / 2);
		} catch (NullPointerException e) {
			if (x < Game.WIDTH / 2 - width / 2) x = 0;
			else x = Game.WIDTH - width;
		}
		
		if (x < 0) x = 0;
		else if (x > Game.WIDTH - width) x = Game.WIDTH - width;
	}
	
	public int getMiddleX() { return (int)x + width / 2; }
}
