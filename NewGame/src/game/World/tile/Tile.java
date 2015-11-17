package game.World.tile;
import game.DataBuffer;
import game.PathNode;
import game.World.World;
import game.World.WorldObject;
import game.World.creature.Creature;
import game.World.tile.tileObject.TileObject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Interfaces.Drawable;

public abstract class Tile extends WorldObject implements Drawable  {
	
	public DataBuffer	<BufferedImage> 	tileImage;
	public DataBuffer	<PathNode> 			pathNode;
	public DataBuffer	<Integer> 			drag,width,height;
	public DataBuffer	<Boolean> 			passable;
	public DataBuffer	<Creature> 			creature; 
	public String 							tileType;
	protected LinkedList<TileObject> 		objects;
	
	public int 				type;
	public static final int GRASS = 0;
	public static final int WATER = 1;

	public void setCreature(Creature c){
		this.creature.set(c);
	}
	
	public Creature getCreature(){
		return creature.get();
	}
	
	public Tile(int x, int y,World world,int type) {
		super(world, x, y);
		this.drag 		= new DataBuffer<Integer>	   (1,bufferList);
		infoBox.addItem("drag", drag);
		this.width 		= new DataBuffer<Integer>	   (world.tileWidth,bufferList);
		
		this.height 	= new DataBuffer<Integer>	   (world.tileHeight,bufferList);
		
		this.pathNode 	= new DataBuffer<PathNode>	   (new PathNode(x,y),bufferList);
		
		this.creature   = new DataBuffer<Creature> 	   (null,bufferList);
		
		this.passable   = new DataBuffer<Boolean>      (true,bufferList);
		infoBox.addItem("Passable", passable);
		this.world = world;
		this.world.UI.screen.screenDrawer.layers[0].add(this);
		this.world.worldObjects.add(this);
		this.type = type;
		objects = new LinkedList<TileObject>();
	}
	
	public void addTileObject(TileObject t){
		if(!t.passable){
			passable.set(false);
			objects.add(t);
		}
	}

	public BufferedImage getImage(){
		return tileImage.get();
	}
	
	public void colourChange(){
		 BufferedImage temp = new BufferedImage(50,50, BufferedImage.TYPE_INT_ARGB);
		 tileImage.set(temp);
		 Graphics2D g2d = temp.createGraphics();
		 g2d.setColor(new Color((int)(Math.random()*254), (int)(Math.random()*254), (int)(Math.random()*254)));
		 g2d.fillRect(0,0,100,100);
	}
	
	public void select() {
//		System.out.println(tileType+" tile selected");
//		world.tryPath(this);
//		System.out.println("doing trypath");
		if(creature.get()!=null){
			creature.get().drawline = true;
			creature.get().debug = true;
		}
	}
}
