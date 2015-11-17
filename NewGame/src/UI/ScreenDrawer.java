package UI;

import game.World.World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

import Interfaces.Drawable;

public class ScreenDrawer {
	
	public ArrayList<Drawable>[] layers;
	
	{
		layers = new ArrayList[4];
		layers[0] = new ArrayList<Drawable>(); //tiles
		layers[1] = new ArrayList<Drawable>(); //grass
		layers[2] = new ArrayList<Drawable>(); //creature
		layers[3] = new ArrayList<Drawable>(); //infoBox
		
		System.out.println("***********************************");
		System.out.println(layers[0].size());
		System.out.println(layers[1].size());
		System.out.println(layers[2].size());
		System.out.println(layers[3].size());
		System.out.println("***********************************");
		
	}

	public void draw(Graphics G){

		Graphics2D g2d = (Graphics2D) G;
		g2d.setColor(Color.MAGENTA);
		//g2d.fillRect(0, 0, 65, 65);
		
		for(ArrayList<Drawable> x : layers){
			for(Drawable d : x){
				d.draw(g2d);
			}
		}
//		System.out.println("***********************************");
//		System.out.println(layers[0].size());
//		System.out.println(layers[1].size());
//		System.out.println(layers[2].size());
//		System.out.println(layers[3].size());
//		System.out.println("***********************************");
	}

}
