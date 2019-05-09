package Snake;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import SnakeSimulator.Action;
import SnakeSimulator.LocalInformation;
import game.Direction;
import game.Drawable;
import ui.GridPanel;

public class Snake  implements Drawable {
	private LinkedList<Cell> snake=new LinkedList<Cell>();
	
	public Snake() {
		snake.add(new Cell(4,1));
		snake.add(new Cell(3,1));
		snake.add(new Cell(2,1));
		snake.add(new Cell(1,1));
	}
	public Snake(LinkedList snake) {
		this.snake=snake;
	}
	public LinkedList<Cell> getSnake() {
		return snake;
	}
	public void setSnake(LinkedList<Cell> snake) {
		this.snake = snake;
	}
	@Override
	public void draw(GridPanel panel) {
		// TODO Auto-generated method stub
		for(Cell cell :snake) {
			if(cell.equals(getHead())) {
				panel.drawSquare(cell.getX(),cell.getY(), Color.RED);
			}else {
				panel.drawSquare(cell.getX(),cell.getY(), Color.BLUE);
			}
		}
	}
	public Cell getHead() {
		return snake.get(0);
	}
	public void eat(Food food) {//direction is to locate new cell
		
		
		snake.add(0, new Cell(food.getX(),food.getY()));
		
		
	}
	public Snake reproduce() {
		LinkedList<Cell> temp=new LinkedList();
		for(int i=0;i<4;i++) {
			
			temp.add(snake.removeLast());
		}
		System.out.println(temp.size());
		return new Snake(temp);
			}
	public void move(Direction direction) {
//		int x=getHead().getX();
//		int y=getHead().getY();
//		for(Cell cell:snake) {
//			
//			if(cell.equals(getHead())) {
//				if(direction==Direction.UP) {
//					cell.setY(cell.getY()-1);
//				} if(direction==Direction.DOWN) {
//					cell.setY(cell.getY()+1);
//				} if(direction==Direction.LEFT) {
//					cell.setX(cell.getX()-1);
//				} if(direction==Direction.RIGHT) {
//					cell.setX(cell.getX()+1);
//				}
//				System.out.println(getHead().getY());
//				System.out.println(getHead().getX());
//			}
//			int tempx=cell.getX();
//			int tempy=cell.getY();
//			if(!cell.equals(getHead())) {
//				
//				cell.setY(y);
//				cell.setX(x);
//				}
//			x=tempx;
//			y=tempy;
//			}
		int x = 0;
		int y = 0;
		
		if(direction==Direction.UP) {
			y=-1;
		} if(direction==Direction.DOWN) {
			y=+1;
		} if(direction==Direction.LEFT) {
			x=-1;
		} if(direction==Direction.RIGHT) {
			x=+1;
		}
		int tempx=snake.getFirst().getX();
		int tempy=snake.getFirst().getY();
		this.getHead().setX(tempx+x);
		this.getHead().setY(tempy+y);
		Iterator<Cell> itr =snake.iterator();
		itr.next();
		while(itr.hasNext()) {
			Cell c=itr.next();
			int a=c.getX();
			int b=c.getY();
			c.setX(tempx);
			c.setY(tempy);
			tempx=a;
			tempy=b;
		}
		
		}
	public Action chooseAction(LocalInformation information) {
		List<Direction> freeDirections=information.getFreeDirections();
		Direction directionToAct=information.findFood();
		
	if(snake.size()==8) {
		return new Action(Action.Type.REPRODUCE);
	}if(information.getCellDown() instanceof Food) {
			return new Action(Action.Type.EAT,Direction.DOWN);
	}if(information.getCellLeft() instanceof Food) {
		return new Action(Action.Type.EAT,Direction.LEFT);
	}if(information.getCellUp() instanceof Food) {
		return new Action(Action.Type.EAT,Direction.UP);
	}if(information.getCellRight() instanceof Food) {
		return new Action(Action.Type.EAT,Direction.RIGHT);
	}if(directionToAct!=null) {
		return new Action(Action.Type.MOVE,directionToAct);
	}
		return null;
		
	}
	

	
	
}
	
	
	


