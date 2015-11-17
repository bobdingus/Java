package game;

import Interfaces.Event;
import game.World.tile.Tile;


public class SelectTileEvent implements Event{

	Tile tile;
	
	public SelectTileEvent(Tile tile){
		this.tile = tile;
	}
	
	@Override
	public void handle() {
		tile.select();
		
	}

}
