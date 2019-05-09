package SnakeSimulator;

import game.Direction;

public class Action {
	 public enum Type {
	        MOVE,
	        REPRODUCE,
	        EAT,
	 }
	 private  final Type type;
	    private  Direction direction;
	    public Action(Type type,Direction direction) {
	    	this.type = type;
	        this.direction = direction;
	    }
	    
	    public Action(Type type) {
			
			this.type = type;
		}

		public Type getType() {
	        return type;
	    }

	    /**
	     * Getter for the direction of the action
	     * @return direction or null if a direction does not exist
	     */
	    public Direction getDirection() {
	        return direction;
	    }

}
