package UI;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	public Screen screen;
	
	public Window(int x, int y, WorldUI UI){
		this.setSize(x,y);
		this.screen = new Screen(x,y, UI);
		this.add(screen);
		setUndecorated(true);
		setVisible(true);
		setLocation(0, 0);
		
	}

}
