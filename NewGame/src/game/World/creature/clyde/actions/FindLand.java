package game.World.creature.clyde.actions;

import game.World.World;
import game.World.creature.Creature;
import game.World.creature.actions.Action;
import game.World.creature.actions.FindPath;

public class FindLand extends MoveRandomly{
	
	int checkRadius = 10;
	int checkCount = 0;

	public FindLand(Action returnAction, Creature creature) {
		super(returnAction, creature);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void pickTile(){
		xp = creature.xpos.get();
		yp = creature.ypos.get();
		
		if(checkCount > 10){
			checkRadius++;
			checkCount = 0;
			if(checkRadius >World.TILES_ACROSS){
				
			}
		}
		
		int xmod = (int)(Math.random()*checkRadius);
		int ymod = (int) (Math.random()*checkRadius);
		
		double xflip = Math.random();
		double yflip = Math.random();
		
		if(xflip >=0.5d)xp+=xmod;
		else			xp-=xmod;
		if(yflip >=0.5d)yp+=ymod;
		else			yp-=ymod;
		
		if(!creature.world.validTile(xp, yp)){
			checkCount++;
			pickTile();
		}
		else if(creature.world.tiles[xp][yp].tileType.equals("Water") && (checkRadius < World.TILES_ACROSS)  ){	
			checkCount++;
			pickTile();
		}
		
	}
	
	public void decideAction(){
		this.complete = true;
		creature.changeCurrentAction(new FindPath(this.returnAction, creature,xp, yp));
		creature.dx = xp;
		creature.dy = yp;
	}
	

}
