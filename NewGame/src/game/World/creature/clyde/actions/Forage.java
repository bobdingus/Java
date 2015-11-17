package game.World.creature.clyde.actions;

import game.World.creature.Creature;
import game.World.creature.actions.Action;
import game.World.tile.GrassTile;

public class Forage extends Action {
	
public Forage(Action returnAction, Creature creature) {
		super(returnAction, creature);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void perform() {
		boolean ateSomething = false;
		if(creature.world.tiles[creature.xpos.get()][creature.ypos.get()].tileType.equals("Grass")){
			GrassTile gt = (GrassTile) (creature.world.tiles[creature.xpos.get()][creature.ypos.get()]);
			//this is NOT an ideal method to access tile type
			if(gt.grass.length.get()>0){
				creature.hunger.set(creature.hunger.get()+10);
				//System.out.println("Creature Hunger is :--> " + creature.hunger.get());
				gt.grass.length.set(gt.grass.length.get()-1);
				ateSomething = true;
				//System.out.println("foraging");
				
			}
		}
		if(creature.hunger.get() > 80){
			returnAction(ateSomething);
		}
		else{
			complete = true;
			creature.changeCurrentAction(new FindLand(this, creature));
		}
		
	}
	
}
