package game.World.creature.events;

import game.World.World;
import game.World.creature.clyde.Clyde;
import Interfaces.Event;

public class KillClyde implements Event{

	private Clyde clyde;
	
	
	public KillClyde(Clyde clyde){
		this.clyde = clyde;
	}
	
	@Override
	public void handle() {
		for(int x =  0; x < World.TILES_ACROSS; x++){
			for(int y = 0; y < World.TILES_DOWN; y++){
				if(clyde.world.tiles[x][y].creature.get()== clyde){
					clyde.world.tiles[x][y].creature.set(null);
				}
			}
		}
		clyde.world.worldObjects.remove(clyde);
		clyde.world.UI.screen.screenDrawer.layers[2].remove(clyde);
		
	}

}
