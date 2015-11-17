package UI;

import game.DataBuffer;
import game.World.World;
import game.World.WorldObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Interfaces.Drawable;

public class InfoBox implements Drawable {
	
	public boolean draw = false;
	
	public ArrayList<String> infoItems;
	final WorldObject object;
	final int fontSize;
	int width;
	int height;
	
	public InfoBox(WorldObject wo){
		infoItems = new ArrayList<String>();
		object = wo;
		fontSize = object.world.UI.SCREEN_HEIGHT/50;
		width = 0;
		height = 0;
		object.world.UI.screen.screenDrawer.layers[3].add(this);
	}
	
	public void addItem(String name, DataBuffer buffer){
		String s = (name + " : " + buffer.get().toString());
		if((s.length()*fontSize/2) > width){
			width = (s.length()*fontSize)/2;
		}
		height+=fontSize;
		infoItems.add(s);
	}
	
	public void draw(Graphics2D G2D){
		if(draw){
			int x = (object.xpos.get()*object.world.tileWidth)+object.world.tileWidth;
			int y = object.ypos.get()*object.world.tileHeight;
			G2D.setColor(Color.GRAY);
			G2D.fillRect(x, y, width, height);
			G2D.setColor(Color.BLACK);
			G2D.drawRect(x, y, width, height);
			G2D.setColor(Color.WHITE);
			int i = 1;
			Graphics G = (Graphics)G2D;
			G.setFont(new Font("Calibri", Font.PLAIN, fontSize)); 
			for(String s : infoItems){
				G2D.drawString(s, x, y+(i*fontSize));
				i++;
			}
		}
		
	}
	
//	public class InfoItem{
//		
//		String Data
//		
//		public InfoItem(String name, DataBuffer buffer){
//			this.name = name;
//			this.value = buffer;
//		}
//		
//	}


}
