package main;

import Snake.Food;
import Snake.Snake;
import SnakeSimulator.Simulator;

import ui.ApplicationWindow;

/**
 * The main class of the project.
 */
public class Main {

    /**
     * Main entry point for the application.
     *
     * @param args application arguments
     */
    public static void main(String[] args) {
    	Simulator game=new Simulator(80, 50, 10, 1000);
    	  // Create and add plants
        
            int x = (int) (Math.random() * game.getGridWidth());
            int y = (int) (Math.random() * game.getGridHeight());
            game.addSnake(new Snake());
            int a = (int) (Math.random() * game.getGridWidth());
            int b = (int) (Math.random() * game.getGridHeight());
            game.addFood(new Food(a, b));
        
    	 ApplicationWindow window = new ApplicationWindow(game.getGamePanel());
         window.getFrame().setVisible(true);
         game.start();
    }

}
