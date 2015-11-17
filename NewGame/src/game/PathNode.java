package game;

import java.awt.Color;

import javax.swing.JComponent;

public class PathNode{

	public final int gridX;
	public final int gridY;
	public boolean wall;
	
	public PathNode parent;
	
	public boolean isOpen;
	public boolean isClosed;
	public int G;
	int H;
	int F;
	
	final int NON_DIAGONAL_MOVEMENT_COST = 10;
	final int DIAGONAL_MOVEMENT_COST     = 14;
	
	public PathNode(int x,int y,boolean wall){
		this.gridX = x;
		this.gridY = y;
		isOpen = false;
		isClosed = false;
		
		setWall(wall);
		
	}
	
	public PathNode(int x,int y){
		this.gridX = x;
		this.gridY = y;
		isOpen = false;
		isClosed = false;
		
		setWall(false);
		
	}
	
	public void setWall(boolean isWall){
		wall = isWall;
	}
	
	public void clearPathInfo(){
		isOpen = false;
		isClosed = false;
		parent = null;	
		
		G = 0;
		H = 0;
		F = 0;
	}
	
	public void updateParent(PathNode parentPathNode){
		this.parent = parentPathNode;
		if(isDiagonal(parent.gridX,parent.gridY,gridX,gridY)){
			G = parent.G + DIAGONAL_MOVEMENT_COST;
		}
		else
		{
			G = parent.G + NON_DIAGONAL_MOVEMENT_COST;
		}
		setF();
	}
	
	public void setF(){
		F = G + H;
	}
	
	public void setH(int bx,int by){
		int manHattanDistance = 0;
		int ax = gridX;
		int ay = gridY;
		if(ax < bx){
			manHattanDistance += bx-ax;
		}
		else{
			manHattanDistance += ax-bx;
		}
		if(ay < by){
			manHattanDistance += by-ay;
		}
		else{
			manHattanDistance += ay-by;
		}
		H = manHattanDistance * NON_DIAGONAL_MOVEMENT_COST;;
	}
	
	public void setG(){
		if(parent==null){
			G = 0;
			return;
		}
		if(isDiagonal(parent.gridX,parent.gridY,gridX,gridY)){
			G = parent.G + DIAGONAL_MOVEMENT_COST;
		}
		else
		{
			G = parent.G + NON_DIAGONAL_MOVEMENT_COST;
		}
	}
	
	public void setG(int modifier ){
		if(parent==null){
			G = 0;
			return;
		}
		if(isDiagonal(parent.gridX,parent.gridY,gridX,gridY)){
			G = parent.G + DIAGONAL_MOVEMENT_COST * modifier;
		}
		else
		{
			G = parent.G + NON_DIAGONAL_MOVEMENT_COST * modifier;
		}
	}
	
	public boolean isDiagonal(int x,int y,int x2, int y2){
		if(x!=x2&&y!=y2)return true;
		else return false;
	}


}
