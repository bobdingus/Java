package game.World.tile.tileObject;

import game.ImageLoader;

import java.awt.image.BufferedImage;

public class GrassFrames {
	
	private static BufferedImage[] images = new BufferedImage[3];
	private static boolean imagesLoaded = false;	
	static final int gs = 480;
	static final int gy =352;
	
	public static BufferedImage[] getImage(){
		if(!imagesLoaded){
			images[0] = ImageLoader.loadImage(GrassFrames.class, "/images/terrain.png").getSubimage(gs,gy,32,32); 
			images[1] =ImageLoader.loadImage(GrassFrames.class,  "/images/terrain.png").getSubimage(gs+32,gy,32,32); 
			images[2] = ImageLoader.loadImage(GrassFrames.class, "/images/terrain.png").getSubimage(gs+64,gy,32,32); 

			imagesLoaded = true;
		}
		
		BufferedImage[] ri = images;
		return ri;	
	}
}
