package game.World.creature.actions;

import game.World.Animation;
import game.World.creature.Creature;
import game.World.creature.clyde.animation.ClydeFrames;
import game.World.tile.Tile;




public class MoveToTile extends Action {

	int frameCount;
	double frameMax = 8;
	double xInc;
	int xmode;
	int ymode;
	final int PLUS = 0;
	final int MINUS = 1;
	final int SAME = 2;
	Tile t;
	int frameInterval = 5;
	int tries = 0;
	
	public MoveToTile(Action returnAction,Creature creature,Tile t){
		super(returnAction, creature);
		frameMax = frameMax * creature.world.tiles[creature.xpos.get()][creature.ypos.get()].drag.get();
		frameCount = 0;
		this.t = t;
		int tx = t.xpos.get();
		int ty = t.ypos.get();
		if(creature.xpos.get() < tx) xmode = PLUS ; else
		if(creature.xpos.get() == tx) xmode = SAME; else
		if(creature.xpos.get() > tx) xmode = MINUS; 
		
		if(creature.ypos.get() < ty) ymode = PLUS ; else
		if(creature.ypos.get() == ty) ymode = SAME; else
		if(creature.ypos.get() > ty) ymode = MINUS; 
		
		if(xmode == PLUS && ymode  == SAME){
			creature.creatureAnimation = new Animation( ClydeFrames.getRightWalk(),frameInterval);
		}
		else if(xmode == PLUS && ymode  == PLUS){
			creature.creatureAnimation = new Animation( ClydeFrames.getRightWalk(), frameInterval);
		}
		else if(xmode == PLUS && ymode  == MINUS){
			creature.creatureAnimation = new Animation( ClydeFrames.getUpWalk(), frameInterval);
		}
		else if(xmode == SAME && ymode  == PLUS){
			creature.creatureAnimation = new Animation( ClydeFrames.getDownWalk(), frameInterval);
		}
		else if(xmode == SAME && ymode  == MINUS){
			creature.creatureAnimation = new Animation( ClydeFrames.getUpWalk(), frameInterval);
		}
		else if(xmode == MINUS && ymode == SAME){
			creature.creatureAnimation = new Animation( ClydeFrames.getleftWalk(), frameInterval);
		}
		else if(xmode == MINUS && ymode == PLUS){
			creature.creatureAnimation = new Animation( ClydeFrames.getleftWalk(), frameInterval);
		}
		else if(xmode == MINUS && ymode == MINUS){
			creature.creatureAnimation = new Animation( ClydeFrames.getUpWalk(), frameInterval);
		}
		else{
			 System.out.println("--------------------------------------");
		}
		
		
//		if(xmode !=SAME && ymode !=SAME){
//			xInc = ((double)creature.world.tileWidth*1.4d) / frameMax;
//		}
//		else{
//			xInc = creature.world.tileWidth / frameMax;
//		}
		
		xInc = creature.world.tileWidth / frameMax;
		if(t.getCreature() ==null&&t.creature.data2==null){
			t.setCreature(creature);
		}
		else{
			returnAction(false);
		}
		this.complete = false;
	}

	@Override
	public void perform() {
		if(t.getCreature()==creature){
			frameCount++;
			if(frameCount < frameMax){
				if(xmode == PLUS){
					creature.xoffset.set(creature.xoffset.get()+xInc);
				}
				else
				if(xmode == MINUS){
					creature.xoffset.set(creature.xoffset.get()-xInc);
				}
				else
				if(xmode == SAME){
					
				}
				if(ymode == PLUS){
					creature.yoffset.set(creature.yoffset.get()+xInc);
				}
				else
				if(ymode == MINUS){
					creature.yoffset.set(creature.yoffset.get()-xInc);
				}
				else
				if(ymode == SAME){}
			}
			else{
				creature.xoffset.set(0d);
				creature.yoffset.set(0d);
				
				Creature c = creature.world.tiles[creature.xpos.get()][creature.ypos.get()].creature.get();
			
				if(c!=creature){
					System.out.println("cant make null on a tile you dont own");
					//System.out.println(c.toString());
					//System.out.println(creature.toString());
					System.exit(43);
				}
				else{
					creature.world.tiles[creature.xpos.get()][creature.ypos.get()].creature.data2=null;
				}
			
				creature.xpos.set(t.xpos.get());
				creature.ypos.set(t.ypos.get());
			
			
				
				if(creature.world.tiles[t.xpos.get()][t.ypos.get()].creature.data2!=null && creature.world.tiles[t.xpos.get()][t.ypos.get()].creature.data2!=creature ){
					complete = true;
					returnAction(false);
					return;
				} 
				else{
					creature.world.tiles[t.xpos.get()][t.ypos.get()].creature.set(creature);
				}
				
				if(xmode == PLUS && ymode  == SAME){
					creature.creatureAnimation = new Animation( ClydeFrames.getRight(),frameInterval);
				}
				else if(xmode == PLUS && ymode  == PLUS){
					creature.creatureAnimation = new Animation( ClydeFrames.getRight(), frameInterval);
				}
				else if(xmode == PLUS && ymode  == MINUS){
					creature.creatureAnimation = new Animation( ClydeFrames.getUp(), frameInterval);
				}
				else if(xmode == SAME && ymode  == PLUS){
					creature.creatureAnimation = new Animation( ClydeFrames.getDown(), frameInterval);
				}
				else if(xmode == SAME && ymode  == MINUS){
					creature.creatureAnimation = new Animation( ClydeFrames.getUp(), frameInterval);
				}
				else if(xmode == MINUS && ymode == SAME){
					creature.creatureAnimation = new Animation( ClydeFrames.getLeft(), frameInterval);
				}
				else if(xmode == MINUS && ymode == PLUS){
					creature.creatureAnimation = new Animation( ClydeFrames.getLeft(), frameInterval);
				}
				else if(xmode == MINUS && ymode == MINUS){
					creature.creatureAnimation = new Animation( ClydeFrames.getUp(), frameInterval);
				}
				else{
					 System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
				if(creature.hunger.get()>0){
					creature.hunger.set(creature.hunger.get()-1);
					//System.out.println("creature hunger is " + creature.hunger.get());
				} // this is LESS than ideal
				//System.out.println(creature.hunger.get());
				this.complete = true;
				returnAction(true);
			}
		}
		else{
			tries++;
			if(tries >=10){
				//creature.drawline = true;
				complete = true;
				//t.setCreature(null);
				returnAction(false);
				
			}	//creature.currentAction = returnAction;
		}
	}
	
	@Override
	public void returnAction(boolean rc){
		super.returnAction(rc);
		creature.creatureAnimation = new Animation( ClydeFrames.getDown(), 1);
		
	}

}
