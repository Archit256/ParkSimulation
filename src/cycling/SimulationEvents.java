package cycling;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SimulationEvents {
	public static PriorityQueue<Events> eventQueue;
	
	SimulationEvents(){
		eventQueue = new PriorityQueue<Events>(new EventsComparison));
	}
	

			
}
