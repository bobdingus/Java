package game;

import game.World.World;
import Interfaces.Event;
/**
 * The TimeEvent class will execute one game frame upon being handled
 * @author Ian McNeilly
 * * ***************************************************************************
 *						CHANGE HISTORY
 *************************************************************************	
 *	WHO 			WHEN		WHAT		
 *	Ian McNeilly	03Nov14		Created	
 */
public class TimeEvent implements Event 
{
	
	World g;

	public TimeEvent(World g){
		this.g = g;
	}
	
	public void handle() {
		g.runFrame();	
		//System.out.println("ran a frame");
	}

}

