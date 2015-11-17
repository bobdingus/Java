package game.World;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation {
	
	int frameCount;
	
	int frameInterval;
	
	BufferedImage[] animationFrame;
	int animationFrameCount;
	int animationFrameMax;
	
	public Animation(BufferedImage[] animationFrame,int frameInterval){
		this.animationFrame = animationFrame;
		this.animationFrameCount = 0;
		this.animationFrameMax = animationFrame.length;
		this.frameCount = 0;
		this.frameInterval = frameInterval;
		
	}
	
	public BufferedImage getImage(){
		
		frameCount++;
		if(frameCount >= frameInterval){
			animationFrameCount++;
			animationFrameCount %= animationFrameMax;
			frameCount = 0;
		}
		//BufferedImage temp =  animationFrame[animationFrameCount];
//		Graphics2D g2d = (Graphics2D)temp.getGraphics();

		return animationFrame[animationFrameCount];
		
	}

}
