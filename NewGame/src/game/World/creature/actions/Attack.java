package game.World.creature.actions;

import game.World.creature.Creature;

public class Attack extends Action{
	
	Creature attackee;
	int damage;

	public Attack(Action returnAction, Creature creature, Creature attackee, int damage) {
		super(returnAction, creature);
		this.attackee = attackee;
		this.damage = damage;
	}

	@Override
	public void perform() {
		attackee.hp.set(attackee.hp.get()-damage);
		returnAction(true);
		
	}

}
