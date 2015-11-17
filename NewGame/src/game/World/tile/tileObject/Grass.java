package game.World.tile.tileObject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;




import Interfaces.Drawable;
import Interfaces.Updatable;
import game.DataBuffer;
import game.ImageLoader;
import game.World.Animation;
import game.World.tile.Tile;

public class Grass extends TileObject implements Drawable,Updatable {
	
	
	static private BufferedImage[] images;
	static boolean imagesLoaded = false;
	static private BufferedImage terrain;
	static final int gs = 480;
	static final int gy =352;
	
	public DataBuffer<Integer> length;
	public DataBuffer<Integer> wear;
	double growTime;
	double growthSpeed;

	public Grass(Tile t) {
		super(t);
		world.UI.screen.screenDrawer.layers[1].add(this);
		world.worldObjects.add(this);
		drawx = new DataBuffer<Integer>(xpos.get()*tile.width.get(),bufferList);
		drawy = new DataBuffer<Integer>(ypos.get()*tile.height.get(),bufferList);

		length = new DataBuffer<Integer>(0,bufferList);
		wear = new DataBuffer<Integer>(0,bufferList);
		
		growTime = 0;
		growthSpeed = Math.random()*10;
		passable = true;
		
		if(!imagesLoaded){
			images = new BufferedImage[3];
			terrain = ImageLoader.loadImage(this, "/images/terrain.png");
			images[0] = terrain.getSubimage(gs,gy,32,32); 
			images[1] = terrain.getSubimage(gs+32,gy,32,32);
			images[2] = terrain.getSubimage(gs+64,gy,32,32); 
			imagesLoaded = true;
			System.out.println("this shit is happeining");
		}
	}

	@Override
	public void update() {
		growTime+= growthSpeed;
		if(growTime >1000&&length.get() <2){
			length.set(length.get()+1);
			tile.drag.data2*=2;
			growTime = 0;
		}
		if(tile.creature.get() !=null){ //FIX THIS TO MAKE IT SO IF THERES A CREATURE ON THE TILE YOU DO THIS
			wear.set(wear.get()+100);
			if(wear.get() >1000&&length.get()>0){
				wear.set(0);
				length.set(length.get()-1);
				tile.drag.data2/=2;
			}
		}
		if((tile.drag.get()>4)||tile.drag.get()<1){
			//System.out.println(tile.drag.get());
		}
		
	}
	
	@Override
	public void draw(Graphics2D G2D) {
		G2D.drawImage
		(	images[length.get()],
			 drawx.get(), 
			 drawy.get(),
			 tile.width.get(),
			 tile.height.get(),
			 null
		);
		
	}

}
