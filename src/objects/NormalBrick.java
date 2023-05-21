package objects;

import handlers.Couleurs;

import java.awt.Color;

public class NormalBrick extends Brick {

	public NormalBrick(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		color = Couleurs.GRISPALE;
	}

}
