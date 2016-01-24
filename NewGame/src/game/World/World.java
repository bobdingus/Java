package game.World;
import game.Game;
import game.MakePathEvent;
import game.MasterObject;
import game.PathNode;
import game.PerlinMap;
import game.PerlinNoise;
import game.SelectTileEvent;
import game.TimeEvent;
import game.Timer;
import game.World.creature.Creature;
import game.World.creature.clyde.Clyde;
import game.World.tile.GrassTile;
import game.World.tile.Tile;
import game.World.tile.WaterTile;
import game.World.tile.tileObject.Tree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import Interfaces.Event;
import Interfaces.Updatable;
import UI.Mouse;
import UI.ScreenDrawer;
import UI.WorldUI;

public class World implements MasterObject {
	
	public Tile[][] tiles;
	public Game game;
	 public WorldUI UI;
	
	int gcCount = 0;
	
	public int tileWidth;
	public int tileHeight;
	
	public final int THIS = 0;
	public final int NEXT = 1;
	
	public static final int multiplier = 32;
	public static final int TILES_ACROSS = 1 * multiplier ; //15 -> 9 works for 1920 1080
	public static final int TILES_DOWN = 1 * multiplier ;
	static final int TOTAL_TILES = TILES_ACROSS * TILES_DOWN;
	
	public PathNode startNode = null;
	public PathNode endNode = null;

	public ArrayList<WorldObject> worldObjects; //Reference Container for ship
	
	private Timer			timer;  		//For triggering the event timing gui.SYstem of the game
	private Queue<Event>    eventQueue; 	//The main event queue for the game
	private Queue<Event> 	tempQueue; 		//a temporary store to avoid concurrent moficication exceptions.
	private TimeEvent 		timeEvent; 		//When executed, runs 1 frame of the game logic
	
	int count = 0;
	
	public ScreenDrawer screenDrawer;
	
	Mouse mouse;
	public PerlinMap perlinMap;
	
	
	public World(Game g){
		this.game = g;
		
		eventQueue 		= new LinkedList<Event>();
		tempQueue       = new LinkedList<Event>(); 
		timeEvent 		= new TimeEvent(this);
		timer 			= new Timer(60,tempQueue,timeEvent);
		
		worldObjects = new ArrayList<WorldObject>();
		UI = new WorldUI(this);
		
		tileWidth 	= UI.SCREEN_WIDTH / TILES_ACROSS;
		tileHeight 	= UI.SCREEN_HEIGHT / TILES_DOWN;
		tiles  		= new Tile[TILES_ACROSS][TILES_DOWN];
		perlinMap = new PerlinMap(TILES_ACROSS,TILES_DOWN);
		
		initTiles();
		randomClyde(30);	
	}
	
	public void addEvent(Event e){
		tempQueue.add(e);
	}
	
	private void transferEvents(){
		while(!tempQueue.isEmpty()){
			eventQueue.add(tempQueue.poll());
		}
	}
	
	public void randomClyde(int max){
		for(int x = 0; x < max; x++){
			while(true){
				 int xp =(int)(Math.random()*TILES_ACROSS);
				 int yp = (int) (Math.random()*TILES_DOWN);
				 if(tiles[xp][yp].getCreature()== null && tiles[xp][yp].passable.get()){
					 Clyde C = new Clyde(this,xp,yp );
					 //C.select();			 
					 tiles[xp][yp].swapBuffers();
					 break;
				 } 
			 }
		}
	}
	
	public void initTiles(){
		for(int x = 0; x < TILES_ACROSS; x++){
			for(int y = 0; y < TILES_DOWN; y++){
				double p = perlinMap.values[x][y];
				if( p> perlinMap.midVal*0.1){
					tiles[x][y] = new GrassTile(x,y,this);
					
					int a =  (int) (Math.random()*100);
					if(a >90){
						tiles[x][y].addTileObject(new Tree(tiles[x][y]));
					}
				}
				else{
					tiles[x][y] = new WaterTile(x,y,this);
					//System.out.println("WATER");
				}
				worldObjects.add(tiles[x][y]);
			}
		}
		//tiles[5][5].infoBox.draw = true;
	}

	public void run(){
		transferEvents();
		timer.tick();
		
		while (!eventQueue.isEmpty()){
			eventQueue.poll().handle();
			count++;
			if(count >100){
				System.out.println(eventQueue.size());
				count = 0;
			}
			
		}
		//game.window.repaint();
	}
	
	public void registerObject(){
		
	}
	
	public void runFrame(){	
//        long start;
//        long end;
//        long elapsed;
//        double seconds;
        
//        start = System.nanoTime();
		for (WorldObject i :worldObjects){
			i.update();
		}
//	    end = System.nanoTime();
//	    elapsed = end - start;
//	    seconds = (double)elapsed / 1000000000d;
//	    System.out.println("update      took "+ seconds + " seconds");
	      
//	    start = System.nanoTime();   
		for (WorldObject i :worldObjects){
			i.swapBuffers();
		}
//		end = System.nanoTime();
//		elapsed = end - start;
//	    seconds= (double)elapsed / 1000000000d;
//	    System.out.println("swap        took "+ seconds + " seconds");
//		gcCount++;
//		if(gcCount >100){
//			System.gc();gcCount = 0;
//		}
		//randomClyde(20);
		UI.window.repaint();
	}
	
	public void selectTile(Point d){
		int x = (int) d.getX();
		int y = (int) d.getY();
		x /= (tileWidth);
 		y /= (tileHeight);
		addEvent(new SelectTileEvent(tiles[x][y]));
	}
	
	public void tryPath(Tile t){
		addEvent(new MakePathEvent(this, t));
	}
	
	public boolean validTile(int x, int y){
		 boolean result = true;
		 if(x <0 			||
		    x >= TILES_ACROSS			||
		    y < 0 			|| 
		    y >= TILES_DOWN){
			 result = false;
		 }
		 return result;
	}

}
