package game.World.tile.tileObject;

import java.awt.Point;

import Interfaces.Drawable;
import Interfaces.Updatable;
import game.DataBuffer;
import game.World.Animation;
import game.World.WorldObject;
import game.World.tile.Tile;

public abstract class TileObject extends WorldObject implements Drawable{
	
	public Tile tile;
	
	public  DataBuffer<Integer> drawx,drawy;

	public boolean passable;
	
	public Animation animation;
	
	public TileObject(Tile t){
		super(t.world, t.xpos.get(), t.ypos.get());
		this.tile = t;
		
		passable = false;
		
	}

}
