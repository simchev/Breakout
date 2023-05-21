package states;

import handlers.Couleurs;
import handlers.Keys;
import handlers.Mouse;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Game;
import objects.Ball;
import objects.Bar;
import objects.TabBrick;
import util.Chronometre;
import util.Drawer;

public class BreakState extends GameState {
	
	private static int row = 10;
	private static int col = 10;
	
	private TabBrick bricks;
	private Bar bar;
	private Ball ball;
	private Color bgColor;
	
	private Chronometre timer;
	private static int delay = 500;
	
	public BreakState(StateManager sm) {
		this.sm = sm;
		
		bgColor = Color.black;
		bricks = new TabBrick(row, col, Game.WIDTH / col, Game.WIDTH / row / row * 2);
		bar = new Bar(192, Game.WIDTH / row / row * 2, Game.HEIGHT - 50, Couleurs.GRISPALE);
		ball = new Ball(bar, 20, 20, Couleurs.GRISPALE);
		
		timer = new Chronometre();
		timer.start();
	}
	
	private void handleKeys() {
		if (Keys.isPressed(Keys.ESCAPE)) {
			sm.setState(StateManager.MENUSTATE);
		}
		if (Mouse.isPressed()) {
			if (!ball.isMoving()) ball.move();
		}
	}

	public void update() {
		handleKeys();
		bar.update();
		ball.update();
		bricks.checkCollision(ball);
		
		if (ball.getY() + ball.getHeight() > bar.getY() + bar.getHeight()) sm.setState(StateManager.BREAKSTATE);
	}
	
	public void draw(Graphics2D g) {
		Drawer.fillComponent(g, bgColor, Game.WIDTH, Game.HEIGHT);
		bricks.draw(g);
		bar.draw(g);
		ball.draw(g);
	}
	
}
