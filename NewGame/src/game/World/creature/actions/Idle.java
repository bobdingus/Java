package game.World.creature.actions;

import game.World.Animation;
import game.World.creature.Creature;
import game.World.creature.clyde.animation.ClydeFrames;

public class Idle extends Action{

	public Idle(Action returnAction, Creature creature) {
		super(returnAction, creature);
		creature.creatureAnimation = new Animation( ClydeFrames.getDown(), 100);
		this.returnAction = this;
	}

	@Override
	public void perform() {
		complete = true;
		returnAction(true);
	}

}
