package Snake;

import java.awt.Color;

import game.Drawable;
import ui.GridPanel;

public class Food extends Cell implements Drawable{

	public Food(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void draw(GridPanel panel) {
		// TODO Auto-generated method stub
		panel.drawSquare(this.getX(),this.getY(), Color.YELLOW);
	}
	
}
