package game.World.creature.clyde;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import UI.InfoBox;
import game.DataBuffer;
import game.World.Animation;
import game.World.World;
import game.World.tile.Tile;
import game.World.creature.Creature;
import game.World.creature.clyde.actions.DecideAction;
import game.World.creature.clyde.animation.ClydeFrames;
import game.World.creature.events.KillClyde;

public class Clyde extends game.World.creature.Creature{
	
	public static int totalClydesKilled = 0;
	int HPmax = 1000;
	boolean isBeingKilled = false;
	
	DataBuffer<Integer> energy;
	
	public Clyde(World world, int xpos, int ypos) {
		super(world, xpos, ypos);
		PermittedTileTypes.add(Tile.WATER);
		PermittedTileTypes.add(Tile.GRASS);
		creatureAnimation = new Animation(ClydeFrames.getDownWalk(), 200);
		changeCurrentAction(new DecideAction(null, this)); //should decide action even have a return action?
		this.hp = new DataBuffer<Integer>(HPmax,bufferList);
		this.energy = new DataBuffer<Integer>(100,bufferList);
		this.energy = new DataBuffer<Integer>(100,bufferList);
		infoBox.addItem("Health",hp);
		selectable = true;
	
	}

	@Override
	public void kill() {
		if(!isBeingKilled){
			world.addEvent(new KillClyde(this));
			isBeingKilled = true;
		}
		
	}
	
	public void update() {

		super.update();
		//String classString 		= currentAction.getClass().getName();
		//String[] bits 			= classString.split(".");
		//if(bits.length >0){
		//	String lastOne 		= bits[bits.length-1];
			//System.out.println(classString);
	//	}
		
		
		//hitNeighbour();
		if(hp.get()<=0){
			kill();
		}
		if(hunger.get()<=0){
			hp.set(hp.get()-1);
		}
		else if(hunger.get()>0){
			if(hp.get()<100){
				hp.set(hp.get()+1);
			}
		}
		//hp.set(hp.get()-1);
		if(selected){
		//	System.out.println(getCurentAction().getClass().getName());
		}
		
	}
	
	public void finalize(){
		//totalClydesKilled++;
		//System.out.println("clydes killed "+totalClydesKilled );
	}
	
	@Override
	public void draw(Graphics2D G2D){
		super.draw(G2D);
		G2D.setColor(Color.BLACK);
		G2D.fillRect
		(
			 xpos.get()*world.tileWidth+xoffset.get().intValue(), 
			 ypos.get()*world.tileHeight+yoffset.get().intValue(),
			 world.tileWidth,
			 4
		);
		G2D.setColor(Color.GREEN);
		G2D.fillRect
		(
			 xpos.get()*world.tileWidth+xoffset.get().intValue(), 
			 ypos.get()*world.tileHeight+yoffset.get().intValue(),
			 hp.get() /(HPmax/world.tileWidth),
			 4
		);
		Graphics G = (Graphics)G2D;
	}
	
	public void hitNeighbour(){
		Creature c;
		c = world.tiles[xpos.get()][ypos.get()].creature.get();
		if(c!=null){
			c.hp.set(c.hp.get()-10);
		}
		
	}
	

}
