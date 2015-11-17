package game.World;

import game.DataBuffer;

import java.util.ArrayList;
import java.util.LinkedList;

import Interfaces.Event;
import UI.InfoBox;

public abstract class WorldObject {
	
	public ArrayList<DataBuffer> bufferList;
	public World world;
	public DataBuffer<Integer> xpos,ypos;
	public InfoBox infoBox;

	
	public WorldObject(World w,int xpos, int ypos){
		
		this.bufferList = new ArrayList <DataBuffer>();
		
		this.xpos = new DataBuffer<Integer>(xpos,bufferList);
		this.ypos = new DataBuffer<Integer>(ypos,bufferList);
		
		this.world = w;
		infoBox = new InfoBox(this);
	}

	public void swapBuffers(){
		for(DataBuffer d : bufferList){
			d.transfer();
		}	
	}
	public abstract void update();
	
	
}
