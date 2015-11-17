package game.World.creature.actions;

import java.awt.Point;
import java.util.LinkedList;

import game.PathFinder;
import game.PathNode;
import game.World.creature.Creature;
import game.World.tile.Tile;
import game.World.tile.tileObject.TileObject;


public class FindPath extends Action{
	
	boolean waitMode =false;
	int dx;
	int dy;
	int tries;
	
	Point nextPoint;
	
	private Action MTT;
	
	public class CreaturePath extends PathFinder{

		public CreaturePath(PathNode a, PathNode b, Tile[][] tiles) {
			super(a, b, tiles);
			
			// TODO Auto-generated constructor stub
		}
		
		public boolean validPathNode(PathNode PathNode, int x, int y){
			 int gx = PathNode.gridX;
			 int gy =  PathNode.gridY;
			 
			 boolean result = true;
			 if(gx + x <0 			||
			    gx + x >= tileWidth	||
			    gy + y < 0 			|| 
			    gy + y >= tileLength){
				 result = false;
				 return result;
			 }
			 result = false;
			for(int i : creature.PermittedTileTypes){
				if(tiles[gx+x][gy+y].type == i){
					result = true;
				}
			}
			if(tiles[gx+x][gy+y].getCreature() != null){
				result = false;
				//System.out.println("");
			}
			if(!tiles[gx+x][gy+y].passable.get()){
				result = false;
				return result;
			}
			return result;
			 
		 }
				
		public boolean openNeighbour(int x, int y){
			 boolean answer = false;
			 int nx = currentNode.gridX + x;
			 int ny = currentNode.gridY + y;
			 if(validPathNode(currentNode,x,y)){
				 PathNode PathNode = tiles[nx][ny].pathNode.get();
				 if(PathNode == endNode){PathNode.parent = currentNode; pathDone = true;}
				 if(!PathNode.isClosed&&!PathNode.wall){
					 answer = true;
					 if(!PathNode.isOpen){
						 openList.add(PathNode);
						 PathNode.isOpen = true;
						 PathNode.parent = currentNode;
						 PathNode.setG();
						 PathNode.setH(endNode.gridX, endNode.gridY);
						 PathNode.setF();
					 }
					 else
					 {
						 if(currentNode.G+1 <PathNode.G){
							 PathNode.parent = currentNode;
							 PathNode.setG(tiles[PathNode.gridX][PathNode.gridY].drag.get());
							 PathNode.setF();
						 }
					 }
					 
				 }
			 }
			 return answer;
		 }
	}
	
	LinkedList<Point> pathList;
	int pathPos;
	
	public FindPath(Action returnAction,Creature creature,int xpos, int ypos){
		super(returnAction,creature);
		pathPos = 0;
		tries = 0;
		dx = xpos;
		dy = ypos;
		pathList = new LinkedList<Point>();
		CreaturePath myPath = new CreaturePath
		(
			creature.world.tiles[creature.xpos.get()][creature.ypos.get()].pathNode.get(),
			creature.world.tiles[xpos][ypos].pathNode.get(), 
			creature.world.tiles
		);
		pathList = myPath.returnPath();
		if(pathList ==null){
			waitMode = true;
		}
		else waitMode = false;
	}
	
	public void tryPath(){
		CreaturePath myPath = new CreaturePath
				(
					creature.world.tiles[creature.xpos.get()][creature.ypos.get()].pathNode.get(),
					creature.world.tiles[dx][dy].pathNode.get(), 
					creature.world.tiles
				);
		pathList = myPath.returnPath();
		if(pathList ==null){
			waitMode = true;
			tries++;
			if(tries > 10){
				//creature.drawline = true;
				complete=true;
				returnAction(false);
				//System.out.println("a clyde is giving up a path");
				tries = 0;
			}
					
					
		}
		else{
			
			waitMode = false;
			move();
		}
	}
	
	public void move(){
		if(! subActionRC){
			pathList.push(nextPoint);
			waitMode = true;
			subActionRC = true;
			return;
		}
		if(pathList.size() >0){
			
			nextPoint = pathList.pop();
			Tile targetTile = creature.world.tiles[nextPoint.x][nextPoint.y];
			if(targetTile.getCreature()!=creature){
				
				complete = true;
				creature.changeCurrentAction(new MoveToTile(this, creature, targetTile));
				complete = false;
				
			}
			else{
				waitMode = true;
				pathList.push(nextPoint);
			}
			
		}
		else{
			complete = true;
			returnAction(true);
		}
	}


	@Override
	public void perform() {
		if(creature.debug){
			System.out.println("this creature is debugging");
			System.out.println("tries = " + tries);
		}
		if(waitMode){
			tryPath();
		}
		else{
			move();
		}
	}

}
