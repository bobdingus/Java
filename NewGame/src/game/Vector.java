package game;
/**
 * Vector class provides basic vector arithmetic operations on two stored velocities
 * x and y. These velocities are what control the repositioning of VectorComponents on the screen.
 * @author Ian McNeilly
 * ***************************************************************************
 *						CHANGE HISTORY
 *************************************************************************	
 *	WHO 			WHEN		WHAT		
 *	Ian McNeilly	05Nov14		Created	
 */
public class Vector {
	
	public double xvel;
	public double yvel;
	
	public Vector(double xvel, double yvel){
		this.xvel = xvel;
		this.yvel = yvel;
	}
	public void combine(Vector v){
		xvel += v.xvel;
		yvel += v.yvel;
		
	}
	
	public void set(double angle,double speed){
		xvel = (Math.cos(Math.toRadians(angle))*speed);
		yvel = (Math.sin(Math.toRadians(angle))*speed);
		System.out.println("Vector set to " + angle + " at speed " + speed );
	}

}
