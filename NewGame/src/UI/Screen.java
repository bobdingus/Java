package UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;

import javax.swing.JButton;
import javax.swing.JPanel;

import Interfaces.Drawable;

public class Screen extends JPanel{
	
	public ScreenDrawer screenDrawer;
	private BottomPanel bottomPanel;
	WorldUI UI;
	
	public class  BottomPanel extends JPanel implements Drawable{
		
		public BottomPanel(int x, int y){
			BottomPanel.this.setVisible(true);
			JButton button1 = new JButton("Button1");
			BottomPanel.this.add(button1);
			BottomPanel.this.setLocation(0, ((y/7)*6)+5);
			
			button1.setVisible(true);
			button1.setLocation(0,0);
			button1.setSize(200, 50);
		}
		
		@Override
		public void draw(Graphics2D G2D) {
			super.paintComponent((Graphics)G2D);
			
		}};

	
	
	public Screen(int x, int y, WorldUI UI){
		screenDrawer = new ScreenDrawer();
		this.setSize(x, y);
		setLocation(0, 0);
		this.setVisible(true);
		this.bottomPanel = new BottomPanel(x,y);
		this.add(bottomPanel);
		this.setLayout(null);
		bottomPanel.setSize(x,y/7);
		bottomPanel.setVisible(true);
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(Color.DARK_GRAY);
		this.UI = UI;
		//screenDrawer.layers[3].add(bottomPanel);

	}
	

//	@Override
	public void paintComponent(Graphics G){
		if(screenDrawer !=null){
			screenDrawer.draw(G);
		}
		else{
			System.out.println("ScreenDrawer uninitialised, draw failed");
		}
		if(UI.drawSelectBox){
			int lx = UI.mxLClick;
			int ly = UI.myLClick;
			
			int lux  = (int)MouseInfo.getPointerInfo().getLocation().getX();
			int luy  = (int)MouseInfo.getPointerInfo().getLocation().getY();
			
			int startx;
			int endx;
			int starty;
			int endy;
			
			if(lx < lux){startx = lx; endx = lux;}else{startx = lux; endx = lx;}
			if(ly < luy ){starty = ly; endy = luy;}else{starty = luy; endy = ly;}
			
			endx = endx - startx;
			endy = endy - starty;
			Graphics2D G2D = ((Graphics2D)G);
			G2D.setStroke(new BasicStroke((int)(Math.random()*8)));
			G2D.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			G2D.setColor(Color.GREEN);
			G2D.drawRect(startx,starty,endx,endy);

		}
		
		
	}



}
