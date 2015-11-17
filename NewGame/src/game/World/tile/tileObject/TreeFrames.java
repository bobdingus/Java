package game.World.tile.tileObject;

import game.ImageLoader;
import game.World.creature.clyde.animation.ClydeFrames;

import java.awt.image.BufferedImage;

public class TreeFrames {

	
	private static BufferedImage[][] images = new BufferedImage[5][];
	private static boolean imagesLoaded[] = new boolean[]{false,false,false,false,false};	
	
	public static BufferedImage[] getImage0(){
		if(!imagesLoaded[0]){
			images[0] = new BufferedImage[4];
			images[0][0] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree3/tree3_00.png"); 
			images[0][1] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree3/tree3_01.png"); 
			images[0][2] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree3/tree3_02.png"); 
			images[0][3] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree3/tree3_03.png"); 

			imagesLoaded[0] = true;
		}
		return images[0];
	}
	
	public static BufferedImage[] getImage1(){
		if(!imagesLoaded[1]){
			images[1] = new BufferedImage[4];
			images[1][0] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree4/tree4_00.png"); 
			images[1][1] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree4/tree4_01.png"); 
			images[1][2] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree4/tree4_02.png"); 
			images[1][3] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree4/tree4_03.png"); 

			imagesLoaded[1] = true;
		}
		return images[1];
	}
	
	public static BufferedImage[] getImage2(){
		if(!imagesLoaded[2]){
			images[2] = new BufferedImage[4];
			images[2][0] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree2/tree2_00.png"); 
			images[2][1] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree2/tree2_01.png"); 
			images[2][2] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree2/tree2_02.png"); 
			images[2][3] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree2/tree2_03.png"); 

			imagesLoaded[2] = true;
		}
		return images[2];
	}
	
	public static BufferedImage[] getImage3(){
		if(!imagesLoaded[3]){
			images[3] = new BufferedImage[4];
			images[3][0] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree5/tree5_00.png"); 
			images[3][1] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree5/tree5_01.png"); 
			images[3][2] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree5/tree5_02.png"); 
			images[3][3] = ImageLoader.loadImage(TreeFrames.class, "/images/trees-greenland/tree5/tree5_03.png"); 

			imagesLoaded[3] = true;
		}
		return images[3];
	}

	public static BufferedImage[] getImage4(){
		if(!imagesLoaded[4]){
			images[4] = new BufferedImage[1];
			images[4][0] = ImageLoader.loadImage(TreeFrames.class, "/images//BleedTrees/_01/Sprite_01.png"); 
			imagesLoaded[4] = true;
		}
		return images[4];
	}
}
