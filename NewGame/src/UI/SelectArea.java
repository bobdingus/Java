package UI;

import java.util.LinkedList;

import game.World.World;
import game.World.creature.Creature;
import game.World.creature.actions.FindPath;
import game.World.creature.actions.Idle;
import Interfaces.Event;





public class SelectArea implements Event {
	
	int startx;
	int starty;
	int endx;
	int endy;
	World world;
	LinkedList<Creature>playerSelected;
	
	public SelectArea(int startx, int endx,int starty, int endy,World world,LinkedList<Creature>playerSelected){
		
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
		this.world = world;
		this.playerSelected = playerSelected;
		
	}

	@Override
	public void handle(){
		boolean nothingSelected = true;
		for(int x = startx; x <= endx; x++){
			for(int y = starty; y <= endy; y++){
				Creature cr = world.tiles[x][y].getCreature();
				
				if(cr!=null){
					cr.select();
					nothingSelected = false;
				}
			}
		}
		if(nothingSelected){
			for(Creature c :playerSelected){
				
				c.getCurentAction().returnAction = (new FindPath(new Idle(null, c), c, endx, endy));
				
				//System.out.println("set a path for someone");
			}
		}
	}

}
