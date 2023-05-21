package states;

public class StateManager {

	private GameState[] gs;
	private int currentState;
	
	private final int states = 3;
	public static final int MENUSTATE = 0;
	public static final int BREAKSTATE = 1;
	public static final int PAUSEDSTATE = 2;
	
	public StateManager() {
		gs = new GameState[states];
		
		currentState = 0;
		loadState();
	}
	
	public void setState(int state) {
		gs[currentState] = null;
		currentState = state;
		loadState();
	}
	
	private void loadState() {
		if (currentState == MENUSTATE) { gs[MENUSTATE] = new MenuState(this); }
		else if (currentState == BREAKSTATE) { gs[BREAKSTATE] = new BreakState(this); }
	}
	
	public void update() {
		gs[currentState].update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		gs[currentState].draw(g);
	}
	
}
