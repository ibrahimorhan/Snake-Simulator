package game;
import ui.GridPanel;
public interface Drawable {


	    /**
	     * Method that is called when an object needs to be drawn on the grid panel.
	     * A class implementing this method should draw itself on the given panel.
	     * @param panel grid panel to draw on
	     */
	    void draw(GridPanel panel);
	
}
