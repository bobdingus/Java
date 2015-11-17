package game.World.creature;

import game.DataBuffer;
import game.World.Animation;
import game.World.Animation;
import game.World.World;



import game.World.WorldObject;
import game.World.creature.actions.Action;
import game.World.creature.clyde.Clyde;
import game.World.tile.Tile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import Interfaces.Drawable;
import Interfaces.Updatable;

public abstract class Creature extends WorldObject implements Updatable,Drawable{
	

	public DataBuffer<Double> xoffset ,yoffset;
	
	public DataBuffer<Integer> hunger;
	
	public DataBuffer<Integer> hp;
	public ArrayList<Integer>PermittedTileTypes;
	private Action currentAction;
	public Animation creatureAnimation;
	
	public int dx = 0;
	public int dy = 0;
	public boolean drawline = false;
	public boolean debug = false;
	
	protected boolean selectable = false;
	public boolean selected = false;
	
	
	Point[] lastPos;
	int pointer;
	
	
	public Creature(World world,int xpos, int ypos){
		super(world,xpos,ypos);
		
		this.xoffset = new DataBuffer<Double>(0d,bufferList);
		this.yoffset = new DataBuffer<Double>(0d,bufferList);
		
		this.hunger = new DataBuffer<Integer>(100,bufferList);
		
		PermittedTileTypes = new ArrayList<Integer>();
		world.tiles[xpos][ypos].setCreature(this);
		world.worldObjects.add(this);
		world.UI.screen.screenDrawer.layers[2].add(this);
		
		lastPos = new Point[500];
		for(int i = 0; i < lastPos.length; i++){
			lastPos[i] = new Point((int)(Math.random()*1000),(int)(Math.random()*1000));
		}
		pointer = 0;
		
	}
	@Override
	public void update() {
		
		lastPos[pointer] = new Point(xpos.get(),ypos.get());
		pointer++;
		if(pointer >= lastPos.length){pointer = 0;}
		boolean stuck = true;
		for(Point P : lastPos){
			if(P.getX()!=xpos.get() ||P.getY()!= ypos.get()){
				stuck = false;
			}
		}
		if(stuck){
			System.out.println("STuck");
			String classString 		= currentAction.getClass().getName();
			//String[] bits 			= classString.split(".");
			//if(bits.length >0){
			//	String lastOne 		= bits[bits.length-1];
				System.out.println(classString);
			//}
		}
		
		if(debug){
			String[] bits = currentAction.toString().split(".");
			String lastOne = bits[bits.length-1];
			System.out.println(currentAction.toString());
		}
		currentAction.perform();
	}
	@Override
	public void draw(Graphics2D G2D) {
		G2D.drawImage
		(	creatureAnimation.getImage(),
			 xpos.get()*world.tileWidth+(xoffset.get().intValue()), 
			 ypos.get()*world.tileHeight+yoffset.get().intValue(),
			 world.tileWidth,
			 world.tileHeight,
			 null
		);
		if(world.tiles[xpos.get()][ypos.get()].type == Tile.WATER){
			G2D.setColor(new Color(100,100,255));
			for(int x = 0; x <30; x++){
				G2D.fillRect
				( 
						(xpos.get()*world.tileWidth)+xoffset.get().intValue()+(int)(Math.random()*world.tileWidth), 
						(ypos.get()*world.tileHeight)+yoffset.get().intValue()+(int)(Math.random()*(world.tileHeight/3)+((world.tileHeight/3)*2)), 
						2,  
						2
				);
			}
		}
		G2D.setColor(Color.WHITE);
//		if(drawline){
//			G2D.drawLine
//			(	
//				 xpos.get()*world.tileWidth, 
//				 ypos.get()*world.tileHeight,
//				 (dx*world.tileWidth)+world.tileWidth/2, 
//				 (dy*world.tileHeight)+world.tileHeight/2
//
//			);
//		}
		if(selected){
			//G2D = (Graphics2D)world.game.window.screen.getGraphics();
			G2D.setStroke(new BasicStroke(5));
			G2D.drawRect(xpos.get()*world.tileWidth+(xoffset.get().intValue()), ypos.get()*world.tileHeight+yoffset.get().intValue(), world.tileWidth, world.tileHeight);
		}
	
		
	}
	
	public Action getCurentAction(){
		return currentAction;
	}
	
	public void changeCurrentAction(Action A){
		if(currentAction == null){
			currentAction = A;
		}
		else if(currentAction.isAtomic()||currentAction.complete){
			currentAction = A;
		}
		else{
			try {
				throw new Exception("Attempt to change action before completion of non atomic action");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(199);
			}
		}
		
	}
	
	public void select(){
		this.selected = true;
		world.UI.playerSelected.add(this);

	}
	public void unSelect(){
		this.selected = true;
		world.UI.playerSelected.remove(this);
	}
	
	public abstract void kill();
	
	public void finalize(){
		
	}
	


}
