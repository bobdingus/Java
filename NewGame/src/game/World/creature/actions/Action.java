package game.World.creature.actions;

import game.World.creature.Creature;

public abstract class Action {
	
	public Action returnAction;
	public Creature creature;
	
	private boolean atomic;
	public boolean complete;
	
	public boolean subActionRC = true; 
	
	
	
	public Action(Action returnAction, Creature creature){
		this.returnAction = returnAction;
		this.creature = creature;
		this.atomic = false;
		this.complete = false;
	}
	
	public boolean isAtomic(){
		return atomic;
	}
	
	public abstract void perform();
	
	public void returnAction(boolean rc){
		returnAction.subActionRC = rc;
		creature.changeCurrentAction(returnAction);
	}

}
