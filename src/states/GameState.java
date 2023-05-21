package states;

public abstract class GameState {

	protected StateManager sm;
	
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
}
