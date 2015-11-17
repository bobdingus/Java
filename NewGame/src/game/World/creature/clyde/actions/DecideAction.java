package game.World.creature.clyde.actions;
import game.World.creature.Creature;
import game.World.creature.actions.Action;
import game.World.creature.actions.Attack;


public class DecideAction extends Action{

	public DecideAction(Action returnAction, Creature creature) {
		super(returnAction, creature);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform() {

		if(!subActionRC){
			complete = true;
			creature.changeCurrentAction(new MoveRandomly(this, creature)); //FUCK SAKE FIX THESE DUPLICATES
		}	
		else{
			complete = true;
			creature.changeCurrentAction(new MoveRandomly(this, creature));
		}
	}
	
	public boolean creatureOnTile(int x, int y){
		boolean result = false;
		
		if(creature.world.validTile(x,y)){
			Creature tileCreature = creature.world.tiles[x][y].creature.get();
			if(tileCreature!=null&&tileCreature!=creature){
				result = true;
			}
		}
		return result;
	}
	
}