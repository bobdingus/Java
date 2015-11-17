package Interfaces;
/**
 * A simple interface to allow creation of different types of events to be
 * put on the same event queue
 * @author Ian
 **************************************************************************
 *						CHANGE HISTORY
 *************************************************************************	
 *	WHO 			WHEN		WHAT		
 *	Ian McNeilly	03Nov14		Created	 
 */
public interface Event {
	
	public  void handle();
	
}
