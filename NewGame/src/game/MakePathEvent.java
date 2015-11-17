package game;

import game.World.World;


import game.World.tile.Tile;
import Interfaces.Event;

public class MakePathEvent implements Event {

	World world;
	Tile tile;
	public MakePathEvent(World world, Tile tile){
		this.world = world;
		this.tile = tile;
		
	}
	
	public void handle() {
		
		if(world.startNode==null){
			 world.startNode = tile.pathNode.get();
		 }
		 else if(world.endNode==null){
			 world.endNode = tile.pathNode.get();
			 
			 PathFinder PF = new PathFinder(world.startNode, world.endNode, world.tiles);
			 PathNode currentNode = PF.findPath();
			 while(currentNode != world.startNode){
				 world.tiles[currentNode.gridX][currentNode.gridY].colourChange();
				 currentNode = currentNode.parent;
			 }
			 world.startNode = null;
			 world.endNode = null; 
		 }
		
	}

}
