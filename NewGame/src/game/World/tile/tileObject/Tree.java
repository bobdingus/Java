package game.World.tile.tileObject;

import java.awt.Graphics2D;

import Interfaces.Drawable;
import Interfaces.Updatable;
import game.DataBuffer;
import game.World.Animation;
import game.World.creature.Creature;
import game.World.tile.Tile;

public class Tree extends TileObject{

	double size = 1f;
	
	public Tree(Tile t) {
		super(t);
		tile.world.UI.screen.screenDrawer.layers[3].add(this);
		tile.world.worldObjects.add(this);
		drawx = new DataBuffer<Integer>(xpos.get()*tile.width.get(),bufferList);
		drawy = new DataBuffer<Integer>(ypos.get()*tile.height.get(),bufferList);
		drawx.set(drawx.get()- (tile.width.get()/4)	);
		drawy.set((drawy.get()-tile.height.get())-(tile.height.get()/2));
		
		double treeSpeed = Math.random()*100;
		animation = new Animation(TreeFrames.getImage2(), 500 + (int)treeSpeed);
		passable = false;
	}

	@Override
	public void draw(Graphics2D G2D) {
		G2D.drawImage
		(	animation.getImage(),
			 drawx.get(), 
			 drawy.get(),
			 tile.width.get() *2,
			 tile.height.get()*2,
			 null
		);
	}
	@Override
	public void update() {
	
	}

}
