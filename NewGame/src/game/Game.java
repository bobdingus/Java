package game;
import game.World.World;
import UI.Window;


public class Game {
	
	
	
	public MasterObject activeMasterObject;
	
	
	public Game(){

		activeMasterObject = new World(this);
	}
	
	public void run(){
    
		while(true){
			activeMasterObject.run();
		}
		
		
	}

}
