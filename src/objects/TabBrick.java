package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TabBrick {
	
	private int row;
	private int col;
	private int width;
	private int height;
	private Brick[][] bricks;

	public TabBrick(int row, int col, int width, int height) {
		this.width = width;
		this.height = height;
		this.row = row;
		this.col = col;
		bricks = new Brick[row][col];
		
		for (int r = 0; r < bricks.length; r++) {
			for (int c = 0; c < bricks[r].length; c++) {
				bricks[r][c] = new NormalBrick(c * width, r * height, width, height);
			}
		}
	}
	
	public void setRow(int brick, int r) {
		for (int i = 0; i < col; i++) {
			bricks[r][i] = new NormalBrick(i * width, r * height, width, height);
		}
	}
	
	public void setCol(int brick, int c) {
		for (int i = 0; i < row; i++) {
			bricks[i][c] = new NormalBrick(c * width, i * height, width, height);
		}
	}
	
	public void setBrick(int r, int c, int brick) {
		bricks[r][c] = new NormalBrick(c * width, r * height, width, height);
	}
	
	public void draw(Graphics2D g) {
		for (int row = 0; row < bricks.length; row++) {
			for (int col = 0; col < bricks[row].length; col++) {
				if (bricks[row][col] != null)
					bricks[row][col].draw(g);
			}
		}
	}
	
	public void checkCollision(Ball ball) {
		Rectangle ballRec = ball.getRec();
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (bricks[i][j] != null && ballRec.intersects(bricks[i][j].getRec())) {
					ball.invertY();
					bricks[i][j] = null;
					break;
				}
			}
		}
	}
	
}
