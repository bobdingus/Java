package game.World.tile;

import game.DataBuffer;
import game.ImageLoader;
import game.World.World;
import game.World.creature.Creature;
import game.World.tile.tileObject.Grass;
import game.World.tile.tileObject.TileObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Interfaces.Updatable;

public class GrassTile extends Tile  {
	
	static public BufferedImage grassImage;
	static public BufferedImage dirtImage;
	static boolean imageLoaded = false;
	
	public Grass grass;
	
	int wear = 0;

	public GrassTile(int x, int y,World world){
		super(x,y ,world,GRASS);
		if(!imageLoaded){
			grassImage = ImageLoader.loadImage(this,"/images/GRS2ROC.png").getSubimage(280, 81, 40, 40);
			dirtImage  = ImageLoader.loadImage(this,"/images/GRS2ROC.png").getSubimage(120, 161, 40, 40);
			imageLoaded = true;
		}
		this.tileImage 	= new DataBuffer<BufferedImage>(dirtImage,bufferList);
		tileType = "Grass";
		drag.set(1);
		grass = new Grass(this);
		addTileObject(grass);
		
	}

	@Override
	public void update() {
			
	}
	


	
	public void draw(Graphics2D G2D) {
		G2D.drawImage
		(	getImage(),
			 xpos.get()*world.tileWidth, 
			 ypos.get()*world.tileHeight,
			 world.tileWidth,
			 world.tileHeight,
			 null
		);
//		if(creature.get()!=null ){
//
//			G2D.fillRect( 
//					 xpos.get()*world.tileWidth, 
//					 ypos.get()*world.tileHeight,
//					 world.tileWidth,
//					 world.tileHeight);
//		}

		
	}
}
