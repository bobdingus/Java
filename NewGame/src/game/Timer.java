package game;

import java.util.Calendar;
import java.util.Queue;

import Interfaces.Event;

/**
 * The timer class takes three arguments for construction
 * 	- frequency: how often it is triggered
 *  - An event queue
 *  - An event 
 *  
 *  It serves to put the specified event onto the specified event queue 
 *  when the specified amount of time in milliseconds(frequency) has passed.
 *  In order to do this, the tick() method must be called regularly
 *  
 * @author Ian McNeilly
 * * ***************************************************************************
 *						CHANGE HISTORY
 *************************************************************************	
 *	WHO 			WHEN		WHAT		
 *	Ian McNeilly	03Nov14		Created	
 */
public  class Timer {
	long startTime;
	long endTime;
	long currentTime;
	long frequency;
	boolean signal;
	Queue<Event> myEventQueue;
	Event myEvent;
	
	public Timer(long frequency,Queue<Event> evq,Event ev){
		signal = false;
		myEvent = ev;
		myEventQueue = evq;
		this.frequency = frequency;
		startTime =  Calendar.getInstance().getTimeInMillis(); 
		endTime = startTime + (1000/frequency);
	}
	
	public boolean tick(){
		currentTime = Calendar.getInstance().getTimeInMillis();
		if (currentTime >= endTime){
			signal = true;
			myEventQueue.add(myEvent);
			reset();
		}
		else signal = false;
		return signal;
	}
	
	protected void reset(){
		startTime =  Calendar.getInstance().getTimeInMillis(); 
		endTime = startTime + (1000/frequency);
	}
}
