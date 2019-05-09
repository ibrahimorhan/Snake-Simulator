package SnakeSimulator;


	import game.Direction;


	import java.util.HashMap;
	import java.util.List;

import Snake.Cell;


	/**
	 * Class representing the information a creature has about its surroundings.
	 * Automatically created and passed by the game to each creature at each timer tick.
	 *
	 * IMPORTANT: Please do not modify this class in any way,
	 * it will be reset when grading your project.
	 */
	public class LocalInformation {

	    private int gridWidth;
	    private int gridHeight;
	    private int foodLocationx;
	    private int foodLocationy;
	    private Cell snakeHead;

	    private HashMap<Direction, Cell> cells;
	    private List<Direction> freeDirections;

	    /**
	     * Constructs the local information for a creature
	     * @param gridWidth width of the grid world
	     * @param gridHeight height of the grid world
	     * @param creatures mapping of directions to neighbor creatures
	     * @param freeDirections list of free directions
	     */
	    LocalInformation(int gridWidth, int gridHeight,
	                     HashMap<Direction, Cell> cells, List<Direction> freeDirections
	                     ,int foodLocationx,int foodLocationy,Cell snakeHead) {
	        this.gridWidth = gridWidth;
	        this.gridHeight = gridHeight;
	        this.cells = cells;
	        this.freeDirections = freeDirections;
	        this.foodLocationx=foodLocationx;
	        this.foodLocationy=foodLocationy;
	        this.snakeHead=snakeHead;
	    }

	    /**
	     * Getter for the width of the grid world.
	     * Can be used to assess the boundaries of the world.
	     * @return number of grid squares along the width
	     */
	    public int getGridWidth() {
	        return gridWidth;
	    }

	    /**
	     * Getter for the height of the grid world.
	     * Can be used to assess the boundaries of the world.
	     * @return number of grid squares along the height
	     */
	    public int getGridHeight() {
	        return gridHeight;
	    }

	    /**
	     * Returns the neighbor creature one square up
	     * @return creature or null if no creature exists
	     */
	    public Cell getCellUp() {
	        return cells.get(Direction.UP);
	    }

	    /**
	     * Returns the neighbor creature one square down
	     * @return creature or null if no creature exists
	     */
	    public Cell getCellDown() {
	        return cells.get(Direction.DOWN);
	    }

	    /**
	     * Returns the neighbor creature one square left
	     * @return creature or null if no creature exists
	     */
	    public Cell getCellLeft() {
	        return cells.get(Direction.LEFT);
	    }

	    /**
	     * Returns the neighbor creature one square right
	     * @return creature or null if no creature exists
	     */
	    public Cell getCellRight() {
	        return cells.get(Direction.RIGHT);
	    }

	    /**
	     * Returns the list of free directions around the current position.
	     * The list does not contain directions out of bounds or containing a creature.
	     * Can be used to determine the directions available to move or reproduce.
	     * @return creature or null if no creature exists
	     */
	    public List<Direction> getFreeDirections() {
	        return freeDirections;
	    }

	    /**
	     * Utility function to get a randomly selected direction among multiple directions.
	     * The selection is uniform random: All directions in the list have an equal chance to be chosen.
	     * @param possibleDirections list of possible directions
	     * @return direction randomly selected from the list of possible directions
	     */
	    public static Direction getRandomDirection(List<Direction> possibleDirections) {
	        if (possibleDirections.isEmpty()) {
	            return null;
	        }
	        int randomIndex = (int)(Math.random() * possibleDirections.size());
	        return possibleDirections.get(randomIndex);
	    }
	    public Direction findFood() {
	    	if(foodLocationx-snakeHead.getX()>0) {
	    		if(freeDirections.contains(Direction.RIGHT)) {
	    			return Direction.RIGHT;
	    		}
	    	}if(foodLocationx-snakeHead.getX()<0) {
	    		if(freeDirections.contains(Direction.LEFT)) {
	    			return Direction.LEFT;
	    		}
	    	}if(foodLocationy-snakeHead.getY()<0) {
	    		if(freeDirections.contains(Direction.UP)) {
	    			return Direction.UP;
	    		}
	    	}
	    	if(foodLocationy-snakeHead.getY()>0) {
	    		if(freeDirections.contains(Direction.DOWN)) {
	    			return Direction.DOWN;
	    		}
	    	}
	    	return getRandomDirection(freeDirections);
	    }
	
}
