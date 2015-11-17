package game.World.creature.clyde.animation;

import game.ImageLoader;

import java.awt.image.BufferedImage;

public class ClydeFrames {

	
	private static BufferedImage[][] images = new BufferedImage[8][];
	private static boolean[] imagesLoaded = new boolean[]
	{
		false,false,false,false,false,false,false,false
	};
	
	public static BufferedImage[] getleftWalk(){
		if(!imagesLoaded[0]){
			images[0] = new BufferedImage[3];
			images[0][0] = ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeLwalk1.png"); 
			images[0][1]= ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeLeft.png"); 
			images[0][2]= ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeLwalk2.png");  
			imagesLoaded[0] = true;
		}
		return images[0];
	}
	
	public static BufferedImage[] getRightWalk(){
		if(!imagesLoaded[1]){
			images[1] = new BufferedImage[3];
			images[1][0] = ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeRwalk1.png"); 
			images[1][1]= ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeRight.png"); 
			images[1][2]= ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeRwalk2.png");  
			imagesLoaded[1] = true;
		}
		return images[1];
	}
	public static BufferedImage[] getUpWalk(){
		if(!imagesLoaded[2]){
			images[2] = new BufferedImage[3];
			images[2][0] = ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeUwalk1.png"); 
			images[2][1]= ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeUp.png"); 
			images[2][2]= ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeUwalk2.png");  
			imagesLoaded[2] = true;
		}
		return images[2];
	}
	
	public static BufferedImage[] getDownWalk(){
		if(!imagesLoaded[3]){
			images[3] = new BufferedImage[3];
			images[3][0] = ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeWalk1.png"); 
			images[3][1]= ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeFront.png"); 
			images[3][2]= ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeWalk2.png");  
			imagesLoaded[3] = true;
		}
		return images[3];
	}
	
	public static BufferedImage[] getUp(){
		if(!imagesLoaded[4]){
			images[4] = new BufferedImage[1];
			images[4][0]= ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeUp.png"); 
			imagesLoaded[4] = true;
		}
		return images[4];
	}
	
	public static BufferedImage[] getDown(){
		if(!imagesLoaded[5]){
			images[5] = new BufferedImage[1]; 
			images[5][0] = ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeFront.png"); 
			imagesLoaded[5] = true;
		}
		return images[5];
	}
	
	public static BufferedImage[] getLeft(){
		if(!imagesLoaded[6]){
			images[6] = new BufferedImage[1];
			images[6][0]= ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeLeft.png");
			imagesLoaded[6] = true;
		}
		return images[6];
	}
	
	public static BufferedImage[] getRight(){
		if(!imagesLoaded[7]){
			images[7] = new BufferedImage[1];
			images[7][0] = ImageLoader.loadImage(ClydeFrames.class, "/images/Clyde/clydeRight.png");
			imagesLoaded[7] = true;
		}
		return images[7];
	}
}
