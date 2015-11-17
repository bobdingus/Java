package game;



import game.World.tile.Tile;

import java.awt.Point;
import java.util.LinkedList;

public class PathFinder {
	
	LinkedList<PathNode>path;
	protected LinkedList<PathNode>openList;
	
	protected boolean pathDone = false;
	public PathNode startNode;
	public PathNode currentNode;
	public PathNode endNode;
	protected Tile[][]tiles;
	protected int tileWidth;
	protected int tileLength;
	
	public PathFinder(PathNode a,PathNode b,Tile[][]tiles){
		this.tiles = tiles;
		tileWidth = tiles.length;
		tileLength = tiles[1].length;
		//System.out.println(tileWidth + " "+ tileLength);
		path 	   	   = new LinkedList<PathNode>();
		openList 	   = new LinkedList<PathNode>();
		startNode 	   = a;
		endNode 	   = b;
		openList.add(startNode);
		startNode.isOpen = true;
		startNode.G = 0;
		startNode.setH(endNode.gridX, endNode.gridY);
		startNode.setF();	
		
		clearPathInfo();
		
	}
	
	public void clearPathInfo(){
		for(int x = 0 ; x < tiles.length; x++){
			for(int y = 0; y < tiles[1].length;y++){
				tiles[x][y].pathNode.get().clearPathInfo();
			}
		}
	}
	
	public PathNode findPath(){
		currentNode = startNode;
		while(!pathDone){
			openList.remove(currentNode);
			currentNode.isOpen = false;
			currentNode.isClosed = true;
			if(currentNode == endNode){
				pathDone = true;
			}
			openAllNeighbours(); 
			currentNode = lowestOpenF();
			if(currentNode == null){
				return null;
			}
		}
		currentNode = endNode;
		return currentNode;
	}
	
	public LinkedList<Point> returnPath(){
		PathNode myNode = findPath();
		if(myNode == null){
			return null;
		}
		LinkedList<Point>pointList = new LinkedList<Point>();
		while(myNode != startNode){
			pointList.push(new Point(myNode.gridX,myNode.gridY));
			myNode = myNode.parent;
		}
		return pointList;
	}
	 public PathNode lowestOpenF(){
		 PathNode lowestOpenF = null;
		 int Fscore = Integer.MAX_VALUE;
		 for(PathNode i : openList ){
			 if(i.F < Fscore){
				 Fscore = i.F;
				 lowestOpenF = i;
			 }
		 }
		return lowestOpenF;
	 }
	 
	 public int openAllNeighbours(){
		 int answer = 0;
		 answer += openNeighbour(-1, 0) ? 1: 0;
		 answer += openNeighbour(-1,-1) ? 1: 0;
		 answer += openNeighbour(-1, 1) ? 1: 0;
		 answer += openNeighbour( 0,-1) ? 1: 0;
		 answer += openNeighbour( 0, 1) ? 1: 0;
		 answer += openNeighbour( 1,-1) ? 1: 0;
		 answer += openNeighbour( 1, 0) ? 1: 0;
		 answer += openNeighbour( 1, 1) ? 1: 0;
		 return answer;
	 }
	 public boolean validPathNode(PathNode PathNode, int x, int y){
		 int gx = PathNode.gridX;
		 int gy =  PathNode.gridY;
		 boolean result = true;
		 if(gx + x <0 			||
		    gx + x >= tileWidth			||
		    gy + y < 0 			|| 
		    gy + y >= tileLength){
			 result = false;
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
						 PathNode.setG();
						 PathNode.setF();
					 }
				 }
				 
			 }
		 }
		 return answer;
	 }
}
