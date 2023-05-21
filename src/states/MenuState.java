package states;

import handlers.Couleurs;
import handlers.Mouse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;

import util.Drawer;
import main.Game;

public class MenuState extends GameState {
	
	// bounds
	private int width = Game.WIDTH;
	private int height = Game.HEIGHT;

	// options
	private String[] options = { "Start", "Quit" };
	private Font[] fonts = new Font[options.length];
	private Color[] colors = new Color[options.length];
	private Rectangle[] recs = new Rectangle[options.length];
	private Font font;
	private int currentOption;
	private int separator;
	private int dx;
	private int dy;
	
	// title
	private Font titleFont;
	private String title;
	
	// background
	private Color bgColor;
	
	public MenuState(StateManager sm) {
		this.sm = sm;
		
		currentOption = 0;
		font = new Font("Broadway", Font.PLAIN, 50);
		titleFont = new Font("Arial", Font.BOLD, 100);
		title = "Breakout";
		separator = 30;
		bgColor = Color.black;
		
		dx = 0;
		dy = 40;
		
		updateOptions();
	}
	
	private void select() {
		switch (currentOption) {
		case 0:
			sm.setState(StateManager.BREAKSTATE);
			break;
		case 1:
			System.out.println("<terminated>");
			System.exit(0);
			break;
		}
	}
	
	public void draw(Graphics2D g) {
		Drawer.fillComponent(g, bgColor, Game.WIDTH, Game.HEIGHT);
		Drawer.drawStringCenterH(g, title, titleFont, Couleurs.ROUGE, Game.WIDTH, 180);
		Drawer.drawCenteredStrings(g, options, fonts, colors, width, height, separator, dx, dy);
	}
	
	public void update() { 
		updateOptions();
	}
	
	private void updateOptions() {
		// update currentOption
		for (int i = 0; i < recs.length; i++) {
			if (Mouse.intersectsWith(recs[i])) {
				currentOption = i;
				break;
			}
			else currentOption = -1;
		}
		
		// update fonts and colors
		for (int i = 0; i < options.length; i++) {
			if (i == currentOption) { fonts[i] = font.deriveFont(font.getSize() + 5F); colors[i] = Couleurs.ROUGE; }
			else { fonts[i] = font; colors[i] = Couleurs.GRISPALE; }
		}
		
		// update screen Recs
		double[] widths = new double[recs.length];
		double totalHeight = 0;
		double moyHeight = 0;
		
		// calculate totalHeight
		for (int i = 0; i < recs.length; i++) {
			GlyphVector gv = fonts[i].layoutGlyphVector(new FontRenderContext(fonts[i].getTransform(), true, true), 
					options[i].toCharArray(), 0, options[i].length(), Font.LAYOUT_LEFT_TO_RIGHT);
			totalHeight += gv.getVisualBounds().getHeight();
			widths[i] = gv.getVisualBounds().getWidth();
		}
		
		moyHeight = totalHeight / recs.length;
		totalHeight += separator * (recs.length - 1);
		
		for (int i = 0; i < recs.length; i++) {
			int x = (int) (width / 2 - widths[i] / 2);
			int y = (int) (height / 2 - totalHeight / 2 + i * moyHeight + i * separator);
			recs[i] = new Rectangle(x + dx, y + dy, (int)widths[i], (int)moyHeight);
		}
		
		if (Mouse.isPressed()) {
			select();
			/*System.out.println("-------------------------------------------------------------------");
			System.out.println("x : " + recs[0].getX() + "   y : " + recs[0].getY());
			System.out.println("width : " + (recs[0].getX() + recs[0].getWidth()) + "   height : " + (recs[0].getY() + recs[0].getHeight()));
			System.out.println("\nx : " + recs[1].getX() + "   y : " + recs[1].getY());
			System.out.println("width : " + (recs[1].getX() + recs[1].getWidth()) + "   height : " + (recs[1].getY() + recs[1].getHeight()));
			System.out.println("\nMouse position : " + Mouse.getX() + "," + Mouse.getY());
			System.out.println("-------------------------------------------------------------------");*/
		}
	}
}
