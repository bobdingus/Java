package UI;

import game.World.World;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

	WorldUI worldUI;
	
	public int clickX;
	public int clickY;
	
	public Mouse(WorldUI worldUI){
		this.worldUI = worldUI;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		//worldUI.world.selectTile(arg0.getPoint());
		worldUI.reportClick(arg0);
		System.out.println("pressed");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		worldUI.reportUnClick(arg0);
		System.out.println("released");
		
	}

}
