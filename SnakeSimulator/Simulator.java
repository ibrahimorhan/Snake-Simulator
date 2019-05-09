package SnakeSimulator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Snake.Cell;
import Snake.Food;
import Snake.Snake;
import game.Direction;
import game.GridGame;




public class Simulator extends GridGame {
	private List<Snake> snakes;
	private Cell[][] snakesMap;
	private Food food;
	public Simulator(int gridWidth, int gridHeight, int gridSquareSize, int frameRate){
		super(gridWidth, gridHeight, gridSquareSize, frameRate);
		snakes=new LinkedList();
		snakesMap=new Cell[gridWidth][gridHeight];
	}

	@Override
	protected void timerTick() {
		LinkedList<Snake> copySnake=new LinkedList(snakes);
		for(Snake snake:copySnake) {
			for(Cell cell:snake.getSnake()) {
				snakesMap[cell.getX()][cell.getY()]=null;
			}
			Action action=snake.chooseAction(createLocalInformationForCell(snake.getHead(),snake));

			if(action!=null) {
				if (action.getType() == Action.Type.MOVE) {
					snake.move(action.getDirection());
				}if (action.getType() == Action.Type.EAT) {
					Food food=(Food) getSnakeAtDirection(snake.getHead().getX(),snake.getHead().getY(),action.getDirection());
					snake.eat(food);
					this.removeFood(food);
					addFood(generateRandomFood(snake));
				}
				if (action.getType() == Action.Type.REPRODUCE) {
					addSnake(snake.reproduce());
				}
			}


			for(Cell cell:snake.getSnake()) {
				snakesMap[cell.getX()][cell.getY()]=cell;
			}
		}



	}
	public void addSnake(Snake snake) {
		for(Cell cell:snake.getSnake()) {
			if (snake != null) {
				if (isPositionInsideGrid(cell.getX(), cell.getY())) {
					if (snakesMap[cell.getX()][cell.getY()] == null) {
						snakesMap[cell.getX()][cell.getY()] = cell;
					} 
				}
			}
		}
		snakes.add(snake);
		addDrawable(snake);

	}
	public boolean addFood(Food food) {
		if (food != null) {
			if (isPositionInsideGrid(food.getX(), food.getY())) {
				if (snakesMap[food.getX()][food.getY()] == null) {
					addDrawable(food);
					snakesMap[food.getX()][food.getY()] = food;
					this.food=food;
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}
	private void removeFood(Food food) {
		if (food != null) {

			removeDrawable(food);

			if (isPositionInsideGrid(food.getX(), food.getY())) {
				snakesMap[food.getX()][food.getY()] = null;
			}

		}
	}
	private boolean isPositionInsideGrid(int x, int y) {
		return (x >= 0 && x < getGridWidth()) && (y >= 0 && y < getGridHeight());
	}
	private void updateCreaturesMap(int x, int y, Cell cell) {
		if (isPositionInsideGrid(x, y)) {
			snakesMap[x][y] = cell;
		}
	}
	private Cell getSnakeAtPosition(int x, int y) {
		if (!isPositionInsideGrid(x, y)) {
			return null;
		}
		return snakesMap[x][y];


	}
	private Cell getSnakeAtDirection(int x, int y, Direction direction) {
		if (direction == null) {
			return null;
		}
		int xTarget = x;
		int yTarget = y;
		if (direction == Direction.UP) {
			yTarget--;
		} else if (direction == Direction.DOWN) {
			yTarget++;
		} else if (direction == Direction.LEFT) {
			xTarget--;
		} else if (direction == Direction.RIGHT) {
			xTarget++;
		}
		return getSnakeAtPosition(xTarget, yTarget);
	}
	private boolean isPositionFree(int x, int y) {
		return isPositionInsideGrid(x, y) && getSnakeAtPosition(x, y) == null;
	}
	private boolean isDirectionFree(int x, int y, Direction direction) {
		if (direction == null) {
			return false;
		}
		int xTarget = x;
		int yTarget = y;
		if (direction == Direction.UP) {
			yTarget--;
		} else if (direction == Direction.DOWN) {
			yTarget++;
		} else if (direction == Direction.LEFT) {
			xTarget--;
		} else if (direction == Direction.RIGHT) {
			xTarget++;
		}
		return isPositionFree(xTarget, yTarget);
	}
	private LocalInformation createLocalInformationForCell(Cell cell,Snake snake) {
		int x = cell.getX();
		int y = cell.getY();

		HashMap<Direction, Cell> cells = new HashMap<>();
		cells.put(Direction.UP, getSnakeAtPosition(x, y - 1));
		cells.put(Direction.DOWN, getSnakeAtPosition(x, y + 1));
		cells.put(Direction.LEFT, getSnakeAtPosition(x - 1, y));
		cells.put(Direction.RIGHT, getSnakeAtPosition(x + 1, y));

		ArrayList<Direction> freeDirections = new ArrayList<>();
		if (cells.get(Direction.UP) == null && isPositionInsideGrid(x, y - 1)&&!isThereSnake(snake, x, y-1)) {
			freeDirections.add(Direction.UP);
		}
		if (cells.get(Direction.DOWN) == null && isPositionInsideGrid(x, y + 1)&&!isThereSnake(snake, x, y+1)) {
			freeDirections.add(Direction.DOWN);
		}
		if (cells.get(Direction.LEFT) == null && isPositionInsideGrid(x - 1, y)&&!isThereSnake(snake, x-1, y)) {
			freeDirections.add(Direction.LEFT);
		}
		if (cells.get(Direction.RIGHT) == null && isPositionInsideGrid(x + 1, y)&&!isThereSnake(snake, x+1, y)) {
			freeDirections.add(Direction.RIGHT);
		}

		return new LocalInformation(getGridWidth(), getGridHeight(), cells, freeDirections,food.getX(),food.getY(),cell);
	}
	private Food generateRandomFood(Snake snake) {
		int x = (int) (Math.random() * getGridWidth());
        int y = (int) (Math.random() * getGridHeight());
        while(snakesMap[x][y]!=null||isThereSnake(snake, x, y)) {
        	x = (int) (Math.random() * getGridWidth());
        	y = (int) (Math.random() * getGridHeight());
        }
        return new Food(x,y);
	}
	private boolean isThereSnake(Snake snake,int x,int y) {
		for(Cell cell:snake.getSnake()) {
			if(cell.getX()==x&&cell.getY()== y) {
				return true;
			}
		}
		return false;
	}
}
