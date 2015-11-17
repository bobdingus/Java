package UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	WorldUI worldUI;
	
	
	public Keyboard(WorldUI worldUI){
		this.worldUI = worldUI;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		 if (arg0.getKeyCode() == KeyEvent.VK_D) {
			 worldUI.world.randomClyde(1);
			 System.out.println("yehg");
		 }
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
