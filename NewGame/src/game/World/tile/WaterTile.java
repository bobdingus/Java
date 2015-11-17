package game.World.tile;

import game.DataBuffer;
import game.ImageLoader;
import game.World.World;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class WaterTile extends Tile {
	
	static protected BufferedImage waterImage;
	static boolean imageLoaded = false;
	
	public WaterTile(int x, int y,World world){
		super(x,y ,world,WATER);
		pathNode.get().setWall(false);
		if(!imageLoaded){
			waterImage = ImageLoader.loadImage(this, "/images/water.png");
			imageLoaded = true;
		}
		this.tileImage 	= new DataBuffer<BufferedImage>(waterImage,bufferList);
		tileType = "Water";
		drag.set(4);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D G2D) {
		G2D.drawImage
		(	getImage(),
			 xpos.get()*world.tileWidth, 
			 ypos.get()*world.tileHeight,
			 world.tileWidth,
			 world.tileHeight,
			 null
		);
//		if(creature.get()!=null){
//
//			G2D.fillRect( 
//					 xpos.get()*world.tileWidth, 
//					 ypos.get()*world.tileHeight,
//					 world.tileWidth,
//					 world.tileHeight);
//		}

		
	}


}
