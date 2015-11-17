package UI;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Interfaces.Event;
import game.SelectTileEvent;
import game.World.World;
import game.World.creature.Creature;
import game.World.creature.actions.FindPath;
import game.World.creature.actions.Idle;
import game.World.creature.actions.MoveToTile;
import game.World.creature.clyde.actions.DecideAction;
import game.World.creature.clyde.actions.MoveRandomly;

public class WorldUI {
	
	public World world;
	
	public Keyboard keyboard;
	public Mouse mouse;
	public Window window;
	public int SCREEN_WIDTH = 1024;
	public int SCREEN_HEIGHT = 1024;
	public Screen screen;
	
	public int mxLClick;
	public int myLClick;
	
	public int mxLUnclick;
	public int myLUnclick;
	
	public int mxRClick;
	public int myRClick;
	
	public int mxRUnclick;
	public int myRUnclick;
	
	public boolean drawSelectBox = false;

	public LinkedList<Creature>playerSelected;
	
	public WorldUI(World world){
		this.window= new Window(SCREEN_WIDTH,SCREEN_HEIGHT,this);
		this.world  = world;
		keyboard 	= new Keyboard(this);
		mouse 		= new Mouse(this);
		screen = window.screen;
		window.addKeyListener(keyboard);
		screen.addMouseListener(mouse);
		
		playerSelected = new LinkedList<Creature>();
	}
	
	public void reportClick(MouseEvent arg0,boolean thing){
		System.out.println("reportclick");
		getTile(arg0.getPoint());
		
		if(SwingUtilities.isLeftMouseButton(arg0)){
			mxLClick = arg0.getX();
			mxLClick = arg0.getY();
			world.addEvent(new Event(){
				@Override
				public void handle() {
					Creature cr = world.tiles[mouse.clickX][mouse.clickY].getCreature();
					if(cr!=null){
						cr.select();
						//System.out.println("clicked a creature");
					}
					else{
						for(Creature c :playerSelected){
						
							c.getCurentAction().returnAction = (new FindPath(new Idle(null, c), c, mouse.clickX, mouse.clickY));
							
							//System.out.println("set a path for someone");
						}
					}
					
				}
			});
		}
		else if(SwingUtilities.isRightMouseButton(arg0)){
			mxRClick = arg0.getX();
			mxRClick = arg0.getY();
			world.addEvent(new Event(){
				@Override
				public void handle() {
					Creature cr = world.tiles[mouse.clickX][mouse.clickY].getCreature();
					if(cr!=null){
						cr.selected = false;
						cr.getCurentAction().returnAction = (new DecideAction(null,cr));
						playerSelected.remove(cr);
						System.out.println("removing this guy");
					}
					else{
						for(Creature c :playerSelected){
						
							c.selected = false;
							c.getCurentAction().returnAction = (new DecideAction(null,c));
							//System.out.println("set a path for someone");
						}
						playerSelected = new LinkedList<Creature>();
					}
					
				}
			});
		}
		
		
		
	}
	
	public void reportClick(MouseEvent arg0){
		if(SwingUtilities.isLeftMouseButton(arg0)){
			mxLClick = arg0.getX();
			myLClick = arg0.getY();
			
			drawSelectBox = true;
			
		}
		
	}
	public void reportUnClick(MouseEvent arg0){
		
		
		if(SwingUtilities.isLeftMouseButton(arg0)){
			mxLUnclick = arg0.getX();
			myLUnclick = arg0.getY();
			
			int lx = mxLClick/world.tileWidth;
			int ly = myLClick/world.tileHeight;
			
			int lux  = mxLUnclick/world.tileWidth;
			int luy  = myLUnclick/world.tileHeight;
			
			int startx;
			int endx;
			int starty;
			int endy;
			
			if(lx < lux){startx = lx; endx = lux;}else{startx = lux; endx = lx;}
			if(ly < luy ){starty = ly; endy = luy;}else{starty = luy; endy = ly;}
			System.out.println(startx + " " + starty +" "+ endx + " " + endy);
			world.addEvent(new SelectArea(startx, endx, starty, endy, world, playerSelected));
			drawSelectBox = false;
			
		}
		else if(SwingUtilities.isRightMouseButton(arg0)){	
			mxRUnclick = arg0.getX();
			myRUnclick = arg0.getY();
			for(Creature c :playerSelected){
				
				c.selected = false;
				c.getCurentAction().returnAction = (new DecideAction(null,c));
				//System.out.println("set a path for someone");
			}
			playerSelected = new LinkedList<Creature>();
		}
		
	}
	
	public void getTile(Point P){
		
		mouse.clickX = (int) P.getX();
		mouse.clickY = (int) P.getY();
		mouse.clickX /= (world.tileWidth);
		mouse.clickY /= (world.tileHeight);
		
	}
	
	
		
	
	

}
