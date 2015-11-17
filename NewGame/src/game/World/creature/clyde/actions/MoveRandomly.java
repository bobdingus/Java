package game.World.creature.clyde.actions;

import game.World.creature.Creature;
import game.World.creature.actions.Action;
import game.World.creature.actions.FindPath;

public class MoveRandomly extends Action {
	
	int xp = 0;
	int yp = 0;

	public MoveRandomly(Action returnAction,Creature creature) {
		super(returnAction,creature);		
	}

	@Override
	public void perform() {
		if(chance(100)){
			pickTile();
			while(true){
				 if(creature.world.validTile(xp, yp)){
					 if((xp == creature.xpos.get() && yp == creature.ypos.get())||!creature.world.tiles[xp][yp].passable.get()){
						 pickTile();
					 }
					 else{
						 break;
						 
					 }
				 }
				 else{
					 pickTile();
				 }
			}
			decideAction();
		}	
	}
	
	public void decideAction(){
		if(creature.hunger.get()<30){
			complete = true;
			creature.changeCurrentAction(new Forage(new FindPath(this, creature,xp, yp),creature));
		}
		else{
			complete = true;
			creature.changeCurrentAction(new FindPath(this, creature,xp, yp));
		}
		creature.dx = xp;
		creature.dy = yp;
	}
	
	public void pickTile(){
		xp = creature.xpos.get();
		yp = creature.ypos.get();
		
		int xmod = (int)(Math.random()*10);
		int ymod = (int) (Math.random()*10);
		
		double xflip = Math.random();
		double yflip = Math.random();
		
		if(xflip >=0.5d)xp+=xmod;
		else			xp-=xmod;
		if(yflip >=0.5d)yp+=ymod;
		else			yp-=ymod;
	}
	
	public boolean chance(int percent){
		int c = (int)(Math.random()*100);
		if(c>percent){
			return false;
		}
		else return true;
	}

}