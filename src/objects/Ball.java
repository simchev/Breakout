package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import main.Game;

public class Ball extends MapObject {
	
	private Bar bar;
	private Point velocity;
	private double dx;
	private double dy;
	
	private boolean moving;
	private boolean right;
	private boolean down;

	public Ball(Bar bar, int width, int height, Color color) {
		this.bar = bar;
		this.x = bar.x + bar.width / 2 - width / 2;
		this.y = bar.y - 30;
		this.width = width;
		this.height = height;
		this.color = color;
		moving = false;
	}
	
	public void update() {
		if (!moving) {
			this.x = bar.x + bar.width / 2 - width / 2;
		}
		else {
			if (bar.getRec().intersects(getRec())) {
				invertY();
				
				double barX = bar.getMiddleX();
				double ballX = x + width / 2;
				double ratio = (ballX - barX) / (bar.getWidth() / 2);
				
				dx = velocity.x * ratio;
			}
			
			x += dx;
			y += dy;
			
			if (x < 0 && dx < 0) invertX();
			if (x > Game.WIDTH - width && dx > 0) invertX();
			if (y < 0 && dy < 0) invertY();
		}
	}
	
	public void setSpeedX(double dx) { this.dx = dx; }
	public void setSpeedY(double dy) { this.dy = dy; }
	public void invertX() { this.dx *= -1; }
	public void invertY() { this.dy *= -1; }
	
	public void move() { 
		moving = true;
		velocity = new Point(8, 8);
		dx = 0; 
		dy = -velocity.y;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.drawOval((int)x, (int)y, width - 1, height - 1);
	}
	
	public boolean isMoving() { return moving; }
}
