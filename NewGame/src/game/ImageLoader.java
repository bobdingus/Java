package game;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Handles the loading of buffered images. 
 * Prints out an error to the console if there is an error loading images.
 * @author Ian McNeilly
 *************************************************************************
 *						CHANGE HISTORY
 *************************************************************************	
 *	WHO 			WHEN		WHAT		
 *	Ian McNeilly	03Nov14		Created	 						
 */
public class ImageLoader {
	
	public static BufferedImage loadImage(Object caller,String resourceLocation){
		BufferedImage image = null;
		try {                
			image = ImageIO.read(caller.getClass().getResource(resourceLocation));
	    } 
		catch (IOException ex) {
	            System.out.println("READ OF " + resourceLocation + " FAILED" );
	            System.exit(8);
		}
		return image;
	}
}

